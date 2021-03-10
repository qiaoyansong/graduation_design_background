package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.front.ModifyPassword;
import com.qiaoyansong.service.ModifyPasswordService;
import com.qiaoyansong.util.JedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/7 21:17
 * description：修改密码控制器
 */
@Controller
@RequestMapping("/modifypwd")
public class ModifyPasswordController {

    private static final Logger log = LoggerFactory.getLogger(ModifyPasswordController.class);
    private Jedis redis;

    @Autowired
    private ModifyPasswordService modifyPasswordService;

    @RequestMapping(value = "/getVerificationCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getVerificationCode(@Valid @RequestBody ModifyPassword modifyPassword, BindingResult bindingResult) {
        log.info("ModifyPasswordController.getVerificationCode");
        return this.modifyPasswordService.getVerificationCode(modifyPassword);
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity modifyPassword(@Valid @RequestBody ModifyPassword modifyPassword, BindingResult bindingResult) {
        log.info("ModifyPasswordController.modifyPassword");
        ResponseEntity responseEntity = new ResponseEntity<>();
        // 没有异常
        try {
            redis = JedisPoolUtil.getInstance().getResource();
            log.info("检测redis连接" + redis.ping());
            String mailbox = modifyPassword.getMailbox();
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
                if (redisVerificationCode.equals(modifyPassword.getVerificationCode())) {
                    log.info("验证码验证成功");
                    return this.modifyPasswordService.modifyPassword(modifyPassword);
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
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
        return responseEntity;
    }

}