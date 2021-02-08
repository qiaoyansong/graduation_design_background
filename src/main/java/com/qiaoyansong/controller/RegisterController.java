package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private Jedis redis;
    private static final int VERIFICATION_CODE_LENGTH = 6;
    @Autowired
    private UserService userService;
    /**
     * 获取验证码
     * @param mailBox 邮箱
     */
    @RequestMapping(value = "/getVerificationCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getVerificationCode(@Valid @NotNull(message = "邮箱不能为空") String mailBox) {
        try {
            redis = JedisPoolUtil.getInstance().getResource();
            log.info("检测redis连接" + redis.ping());
            log.info("开始验证验证码是否失效");
            boolean isExists = redis.exists(mailBox);
            ResponseEntity<String> response = new ResponseEntity<>();
            if (isExists) {
                log.info(StatusCode.VERIFICATION_CODE_NOT_EXPIRED.getReason());
                response.setCode(StatusCode.VERIFICATION_CODE_NOT_EXPIRED.getCode());
                response.setBody("验证码已经成功发送至您的邮箱,并且仍在有效期范围之内。");
            } else {
                log.info("验证码已经失效，开始生成验证码");
                // 生成六位的验证码
                StringBuilder stringBuilder = new StringBuilder();
                int i;
                ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                for (i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
                    stringBuilder.append(threadLocalRandom.nextInt(0, 9));
                }
                String code = stringBuilder.toString();
                StatusCode statusCode = sendMailUtil.sendMail(mailBox, "大学生公益马上行申请验证码有效期一分钟", code);
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

    /**
     * 注册用户
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity register(@Valid @RequestBody com.qiaoyansong.entity.front.User user, BindingResult bindingResult){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity<>();
        if(allErrors.size() == 0){
            // 没有异常
            try {
                redis = JedisPoolUtil.getInstance().getResource();
                log.info("检测redis连接" + redis.ping());
                String mailbox = user.getMailbox();
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
                    if(redisVerificationCode.equals(user.getVerificationCode())){
                        log.info("验证码验证成功");
                        log.info("开始创建用户");
                        return userService.register(user);

                    }else{
                        log.info("验证码验证失败");
                        responseEntity.setCode(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getCode());
                        responseEntity.setBody(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getReason());
                        return responseEntity;
                    }
                }else{
                    // 失效了
                    log.info("验证码失效了");
                    responseEntity.setBody(StatusCode.VERIFICATION_CODE_FAILURE.getReason());
                    responseEntity.setCode(StatusCode.VERIFICATION_CODE_FAILURE.getCode());
                    return responseEntity;
                }
            }finally {
                redis.close();
            }
        }else{
            // 有异常
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        }
    }
}
