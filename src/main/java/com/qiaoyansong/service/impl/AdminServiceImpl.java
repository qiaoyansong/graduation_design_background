package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.background.UserType;
import com.qiaoyansong.entity.front.Admin;
import com.qiaoyansong.service.AdminService;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.util.JedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/12 18:41
 * description：
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private Jedis redis;
    @Override
    public ResponseEntity login(com.qiaoyansong.entity.front.User admin) {
        log.info("进入AdminServiceImpl.login");
        redis = JedisPoolUtil.getInstance().getResource();
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            log.info("检测redis连接" + redis.ping());
            String mailbox = admin.getMailbox();
            boolean isExists = redis.exists(mailbox);
            log.info("开始检测验证码是否失效");
            if(isExists){
                // 未失效
                log.info("验证码未失效");
                // 验证码设置生命周期为十秒
                redis.expire(mailbox,10);
                // 验证验证码
                log.info("开始验证验证码");
                String redisVerificationCode = redis.get(mailbox);
                if(redisVerificationCode.equals(admin.getVerificationCode())){
                    log.info("验证码验证成功");
                    log.info("管理员可以登陆");
                    User user = new User();
                    user.setUserName(admin.getUserName());
                    user.setPassword(admin.getPassword());
                    return userService.login(user);
                }else{
                    log.warn("验证码验证失败");
                    responseEntity.setCode(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getCode());
                    responseEntity.setBody(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getReason());
                }
            }else{
                // 失效了
                log.warn("验证码失效了");
                responseEntity.setBody(StatusCode.VERIFICATION_CODE_FAILURE.getReason());
                responseEntity.setCode(StatusCode.VERIFICATION_CODE_FAILURE.getCode());
            }
            return responseEntity;
        }finally {
            if(redis != null){
                redis.close();
            }
        }
    }

    @Override
    public ResponseEntity getAdminVerificationCode(Admin admin) {
        log.info("进入AdminServiceImpl.getAdminVerificationCode");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始检测用户是否存在");
        Integer checkUserName = this.userMapper.checkUserName(admin.getUserName());
        if (checkUserName == null) {
            log.warn("当前用户名不存在，退出");
            responseEntity.setCode(StatusCode.USERNAME_IS_NOT_EXISTS.getCode());
            responseEntity.setBody(StatusCode.USERNAME_IS_NOT_EXISTS.getReason());
        } else {
            log.info("当前用户名存在");
            User user = this.userMapper.getUserInfo(admin.getUserName());
            log.info("开始检测用户权限是否足够");
            if (user.getType() != UserType.GENERAL_USER) {
                log.info("用户权限足够");
                log.info("开始检测用户密码是否正确");
                if (user.getPassword().equals(admin.getPassword())) {
                    log.info("当前用户名密码正确");
                    log.info("开始检验邮箱是否正确");
                    if (user.getMailbox().equals(admin.getMailbox())) {
                        log.info("检验邮箱正确");
                        log.info("发送验证码");
                        return this.userService.getVerificationCode(admin.getMailbox(),"管理员登录验证码");
                    }else{
                        log.warn("邮箱错误");
                        responseEntity.setCode(StatusCode.MAILBOX_ERROR.getCode());
                        responseEntity.setBody(StatusCode.MAILBOX_ERROR.getReason());
                    }
                } else {
                    log.warn("当前用户名密码不正确，退出");
                    responseEntity.setCode(StatusCode.WRONG_PASSWORD.getCode());
                    responseEntity.setBody(StatusCode.WRONG_PASSWORD.getReason());
                }
            } else {
                log.warn("用户权限不够，退出");
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
            }
        }
        return responseEntity;
    }
}
