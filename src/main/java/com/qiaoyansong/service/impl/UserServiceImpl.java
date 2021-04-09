package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.entity.front.ModifyUserInfo;
import com.qiaoyansong.entity.front.UserUploadImg;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.OnLineUserUtil;
import com.qiaoyansong.util.RequestContextHolderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Override
    public ResponseEntity register(com.qiaoyansong.entity.front.User user) {
        log.info("进入UserServiceImpl的register");
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
                register.setTelephone(user.getTelephone());
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
        log.info("进入UserServiceImpl的login");
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
                    User user1 = this.userMapper.getUserInfo(userName);
                    user1.setPassword("");
                    user1.setSessionId("");
                    onLineUserUtil.addSessionInfoFromRedisToDB(userName, userMapper);
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                    responseEntity.setBody(user1);
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
    public ResponseEntity adminLogin(com.qiaoyansong.entity.front.User admin) {
        log.info("进入UserServiceImpl的adminLogin方法");
        redis = JedisPoolUtil.getInstance().getResource();
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            log.info("检测redis连接" + redis.ping());
            String mailbox = admin.getMailbox();
            boolean isExists = redis.exists(mailbox);
            log.info("开始检测验证码是否失效");
            if (isExists) {
                // 未失效
                log.info("验证码未失效");
                // 验证码设置生命周期为十秒
                redis.expire(mailbox, 10);
                // 验证验证码
                log.info("开始验证验证码");
                String redisVerificationCode = redis.get(mailbox);
                if (redisVerificationCode.equals(admin.getVerificationCode())) {
                    log.info("验证码验证成功");
                    log.info("管理员可以登陆");
                    User user = new User();
                    user.setUserName(admin.getUserName());
                    user.setPassword(admin.getPassword());
                    return this.login(user);
                } else {
                    log.warn("验证码验证失败");
                    responseEntity.setCode(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getCode());
                    responseEntity.setBody(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getReason());
                }
            } else {
                // 失效了
                log.warn("验证码失效了");
                responseEntity.setBody(StatusCode.VERIFICATION_CODE_FAILURE.getReason());
                responseEntity.setCode(StatusCode.VERIFICATION_CODE_FAILURE.getCode());
            }
            return responseEntity;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    @Override
    public ResponseEntity logout(String userName) {
        log.info("进入UserServiceImpl的logout");
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
                        // 当前Session过期
                        session.invalidate();
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
    public ResponseEntity adminSelectUsers(PageHelper<SearchCondition> pageHelper) {
        log.info("进入UserServiceImpl的adminSelectUsers方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.userMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<User> news = this.userMapper.getUsers(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity adminDeleteUserByID(String id) {
        log.info("进入UserServiceImpl的adminDeleteUserByID方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始删除用户");
        if (this.userMapper.deleteUserByID(id) != 1) {
            log.warn("删除用户失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("删除活动成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity userUploadImg(UserUploadImg userUploadImg) {
        log.info("进入UserServiceImpl的userUploadImg方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Integer integer = this.userMapper.userUploadImg(userUploadImg);
        if(integer == 1){
            log.info("修改用户头像成功");
        }else{
            log.info("修改用户头像失败");
        }
        User user1 = this.userMapper.getUserInfoById(userUploadImg.getId());
        user1.setPassword("");
        user1.setSessionId("");
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(user1);
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadLocation(UserLocations userLocations) {
        log.info("进入UserServiceImpl的uploadLocation方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Integer integer = this.userMapper.uploadLocation(userLocations);
        if(integer == 1){
            log.info("上传用户收货地址成功");
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }else{
            log.info("上传用户收货地址失败");
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateLocationById(UserLocations userLocations) {
        log.info("进入UserServiceImpl的updateLocationById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Integer integer = this.userMapper.updateLocationById(userLocations);
        if(integer == 1){
            log.info("修改用户收货地址成功");
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }else{
            log.info("修改用户收货地址失败");
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getLocations(String id) {
        log.info("进入UserServiceImpl的getLocations方法");
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        List<UserLocations> locations = this.userMapper.getLocations(id);
        responseEntity.setBody(locations);
        return responseEntity;
    }

    @Override
    public ResponseEntity deleteLocationsById(String id) {
        log.info("进入UserServiceImpl的deleteLocationsById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Integer integer = this.userMapper.deleteLocationById(id);
        if(integer == 1){
            log.info("删除用户收货地址成功");
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }else{
            log.info("删除用户收货地址失败");
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity modifyUserInfo(ModifyUserInfo modifyUserInfo) {
        log.info("进入UserServiceImpl的modifyUserInfo方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Integer integer = this.userMapper.modifyUserInfo(modifyUserInfo);
        if(integer == 1){
            log.info("修改用户信息成功");
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }else{
            log.info("修改用户信息失败");
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getAddressListByUserId(PageHelper<SearchCondition> pageHelper) {
        log.info("进入UserServiceImpl的getAddressListByUserId方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.userMapper.getAddressTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<UserLocations> userLocations = this.userMapper.getAddressListByUserId(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(userLocations);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

}
