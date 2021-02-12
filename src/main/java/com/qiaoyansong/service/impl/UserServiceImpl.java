package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.background.UserType;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.OnLineUserUtil;
import com.qiaoyansong.util.RequestContextHolderUtil;
import com.qiaoyansong.util.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/7 21:33
 * description：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static OnLineUserUtil onLineUserUtil = OnLineUserUtil.getInstance();
    private static Jedis redis;
    private static final int VERIFICATION_CODE_LENGTH = 6;
    private final SendMailUtil sendMailUtil = SendMailUtil.getInstance();
    @Override
    public ResponseEntity register(com.qiaoyansong.entity.front.User user) {
        ResponseEntity responseEntity = new ResponseEntity();
        // 验证用户名是否注册
        log.info("开始验证用户名是否注册");
        Integer isUserNameExists = userMapper.checkUserName(user.getUserName());
        if (isUserNameExists == null) {
            // 用户名不存在
            log.info("用户名未注册");
            // 验证邮箱是否注册
            log.info("开始验证邮箱是否注册");
            Integer isMailBoxExists = userMapper.checkMailBox(user.getMailbox());
            if (isMailBoxExists == null) {
                // 邮箱不存在
                log.info("邮箱未注册");
                // 创建用户
                log.info("开始创建用户");
                User register = new User();
                register.setMailbox(user.getMailbox());
                register.setUserName(user.getUserName());
                register.setType(UserType.GENERAL_USER);
                register.setPassword(user.getPassword());
                int result = userMapper.register(register);
                if (result == 1) {
                    // 注册成功
                    log.info("创建用户成功");
                    responseEntity.setBody(StatusCode.SUCCESS.getReason());
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                } else {
                    // 数据库错误 注册失败
                    log.error("创建用户失败");
                    responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                    responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                }
            } else {
                // 邮箱已经存在
                log.warn("邮箱已注册");
                responseEntity.setBody(StatusCode.MAILBOX_IS_EXISTS.getReason());
                responseEntity.setCode(StatusCode.MAILBOX_IS_EXISTS.getCode());
            }
        } else {
            // 用户名存在
            log.warn("用户名已经注册") ;
            responseEntity.setBody(StatusCode.USERNAME_IS_EXISTS.getReason());
            responseEntity.setCode(StatusCode.USERNAME_IS_EXISTS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity login(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        log.info("开始检测用户名是否存在");
        Integer isUserNameExists = userMapper.checkUserName(userName);
        ResponseEntity responseEntity = new ResponseEntity();
        if(isUserNameExists != null){
            log.info("用户名存在");
            log.info("开始验证密码是否正确");
            String curPassword = userMapper.getPassword(userName);
            if(curPassword.equals(password)){
                log.info("密码正确");
                try {
                    redis = JedisPoolUtil.getInstance().getResource();
                    log.info("检验redis联通性" + redis.ping());
                    log.info("redis写入对应sessionID");
                    HttpSession session = RequestContextHolderUtil.getRequest().getSession();
                    log.info("SessionId为"+session.getId());
                    log.info("userName为" + userName);
                    session.setAttribute("userName", userName);
                    redis.set(userName,session.getId());
                    log.info("redis写入sessionID完成");
                    onLineUserUtil.addSessionInfoFromRedisToDB(userName, userMapper);
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                    responseEntity.setBody(StatusCode.SUCCESS.getReason());
                }finally {
                    if(redis != null){
                        redis.close();
                    }
                }
            }else{
                log.warn("密码错误");
                responseEntity.setCode(StatusCode.WRONG_PASSWORD.getCode());
                responseEntity.setBody(StatusCode.WRONG_PASSWORD.getReason());
            }
        }else{
            log.warn("用户名不存在");
            responseEntity.setCode(StatusCode.USERNAME_IS_NOT_EXISTS.getCode());
            responseEntity.setBody(StatusCode.USERNAME_IS_NOT_EXISTS.getReason());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity logout(String userName) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        log.info("尝试从session中获取userName属性");
        if ((String) session.getAttribute("userName") == null) {
            log.info("session中没有userName属性");
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
        } else {
            log.info("session中有userName属性");
            // 删除session中的属性
            session.removeAttribute("userName");
            // 判断redis中的session是否是当前登录的session
            log.info("判断redis中的session是否是当前登录的session");
            log.info("检测与redis连通性" + redis.ping());
            try {
                if (redis.exists(userName)) {
                    String curSessionId = redis.get(userName);
                    if (curSessionId.equals(session.getId())) {
                        log.info("当前sessionID与登录SessionID相同");
                        // 删除redis中的key
                        redis.del(userName);
                        log.info("删除redis中的SessionID");
                        // 置空MySQL中的SessionID
                        log.info("删除MySQL中的SessionID");
                        onLineUserUtil.removeSessionInfoFromDB(userName, userMapper);
                    }
                }
            }finally {
                if(redis != null){
                    redis.close();
                }
            }
        }
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(StatusCode.SUCCESS.getReason());
        return responseEntity;
    }

    @Override
    public ResponseEntity getVerificationCode(String mailBox, String emailTitle) {
        try {
            redis = JedisPoolUtil.getInstance().getResource();
            log.info("检测redis连接" + redis.ping());
            log.info("开始验证验证码是否失效");
            boolean isExists = redis.exists(mailBox);
            ResponseEntity<String> response = new ResponseEntity<>();
            if (isExists) {
                // 验证码还存在
                log.info(StatusCode.VERIFICATION_CODE_NOT_EXPIRED.getReason());
                response.setCode(StatusCode.VERIFICATION_CODE_NOT_EXPIRED.getCode());
                response.setBody("验证码已经成功发送至您的邮箱,并且仍在有效期范围之内。");
            } else {
                log.warn("验证码已经失效，开始生成验证码");
                // 生成六位的验证码
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                for (i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
                    stringBuilder.append(threadLocalRandom.nextInt(0, 9));
                }
                String code = stringBuilder.toString();
                StatusCode statusCode = sendMailUtil.sendMail(mailBox, emailTitle, code);
                if (statusCode == StatusCode.SEND_EMAIL_SUCCESS) {
                    // redis记录 有效期62s 应该比实际的一分钟长一点 防止网络问题
                    redis.setex(mailBox, 65, code);
                    response.setCode(StatusCode.CREATE_VERIFICATION_CODE_COMPLETE.getCode());
                    response.setBody(StatusCode.CREATE_VERIFICATION_CODE_COMPLETE.getReason());
                    log.info(StatusCode.CREATE_VERIFICATION_CODE_COMPLETE.getReason());
                } else {
                    log.error(StatusCode.SEND_EMAIL_FAILED.getReason());
                    response.setCode(statusCode.getCode());
                    response.setBody(statusCode.getReason());
                }
            }
            return response;
        } finally {
            redis.close();
        }
    }
}
