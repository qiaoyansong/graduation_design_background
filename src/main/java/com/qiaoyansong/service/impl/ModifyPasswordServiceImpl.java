package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.front.ModifyPassword;
import com.qiaoyansong.service.ModifyPasswordService;
import com.qiaoyansong.service.VerificationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/7 21:28
 * description：
 */
@Service
public class ModifyPasswordServiceImpl implements ModifyPasswordService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeService verificationCodeService;

    private static final Logger log = LoggerFactory.getLogger(ModifyPasswordServiceImpl.class);

    @Override
    public ResponseEntity modifyPassword(ModifyPassword modifyPassword) {
        log.info("进入ModifyPasswordServiceImpl的modifyPassword方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Integer integer = userMapper.modifyPassword(modifyPassword);
        if(integer == null){
            log.warn("数据库修改数据失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        }else{
            log.info("用户修改密码成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getVerificationCode(ModifyPassword modifyPassword) {
        log.info("进入ModifyPasswordServiceImpl的getVerificationCode方法");
        log.info("检查用户名是否存在");
        String userName = modifyPassword.getUserName();
        Integer integer = userMapper.checkUserName(userName);
        ResponseEntity responseEntity = new SearchResponseEntity();
        if(integer == null){
            log.warn("用户名不存在");
            responseEntity.setBody(StatusCode.USERNAME_IS_NOT_EXISTS.getReason());
            responseEntity.setCode(StatusCode.USERNAME_IS_NOT_EXISTS.getCode());
        }else{
            log.info("用户名存在");
            log.info("开始验证用户名是否跟对应邮箱绑定");
            User user = userMapper.getUserInfo(userName);
            if(!user.getMailbox().equals(modifyPassword.getMailbox())){
                log.warn("与该用户绑定邮箱不符");
                responseEntity.setBody(StatusCode.MAILBOX_ERROR.getReason());
                responseEntity.setCode(StatusCode.MAILBOX_ERROR.getCode());
            }else{
                log.info("开始发送验证码");
                return verificationCodeService.getVerificationCode(modifyPassword.getMailbox(), "找回密码验证码");
            }
        }
        return responseEntity;
    }

}
