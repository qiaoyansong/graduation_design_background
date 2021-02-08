package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.background.UserType;
import com.qiaoyansong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                log.info("邮箱已注册");
                responseEntity.setBody(StatusCode.MAILBOX_IS_EXISTS.getReason());
                responseEntity.setCode(StatusCode.MAILBOX_IS_EXISTS.getCode());
            }
        } else {
            // 用户名存在
            log.info("用户名已经注册");
            responseEntity.setBody(StatusCode.USERNAME_IS_EXISTS.getReason());
            responseEntity.setCode(StatusCode.USERNAME_IS_EXISTS.getCode());
        }
        return responseEntity;
    }
}
