package com.qiaoyansong.controller;

import com.qiaoyansong.entity.ResponseEntity;
import com.qiaoyansong.entity.StatusCode;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/5 18:49
 * description：注册控制器
 */
@CrossOrigin
@Controller
@Validated
@RequestMapping("/register")
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private final SendMailUtil sendMailUtil = SendMailUtil.getInstance();
    private final Jedis redis = JedisPoolUtil.getInstance().getResource();
    private static final int VERIFICATION_CODE_LENGTH = 6;

    @RequestMapping("/getVerificationCode")
    @ResponseBody
    public ResponseEntity<String> getVerificationCode(@Valid @NotNull(message = "邮箱不能为空") String mailBox){
        // 生成六位的验证码
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        log.info("开始生成验证码");
        for(i = 0; i < VERIFICATION_CODE_LENGTH; i++){
            stringBuilder.append(threadLocalRandom.nextInt(0,9));
        }
        StatusCode statusCode = sendMailUtil.sendMail(mailBox,"大学生公益马上行申请验证码",stringBuilder.toString());
        ResponseEntity<String> response = new ResponseEntity<>();
        if(statusCode == StatusCode.COMPLETE){
            response.setCode(statusCode.getCode());
            response.setBody("验证码发送成功");
            log.info("验证码发送成功");
            log.info("检测redis连接" + redis.ping());
        }else{
            log.info("邮件发送失败");
            response.setCode(statusCode.getCode());
            response.setBody(statusCode.getReason());
        }
        return response;
    }
}
