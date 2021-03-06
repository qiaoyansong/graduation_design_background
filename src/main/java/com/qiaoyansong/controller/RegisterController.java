package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.service.VerificationCodeService;
import com.qiaoyansong.util.JedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/5 18:49
 * description：注册控制器
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private Jedis redis;
    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;
    /**
     * 获取验证码
     * @param mailBox 邮箱
     */
    @RequestMapping(value = "/getVerificationCode", method = RequestMethod.POST)
    public ResponseEntity<String> getVerificationCode(@Valid @NotNull(message = "邮箱不能为空") String mailBox) {
        log.info("进入RegisterController.getVerificationCode");
        return this.verificationCodeService.getVerificationCode(mailBox, "大学生公益马上行申请验证码有效期一分钟");
    }

    /**
     * 注册用户
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody com.qiaoyansong.entity.front.User user, BindingResult bindingResult){
        ResponseEntity responseEntity = new ResponseEntity<>();
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
                        log.warn("验证码验证失败");
                        responseEntity.setCode(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getCode());
                        responseEntity.setBody(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getReason());
                        return responseEntity;
                    }
                }else{
                    // 失效了
                    log.warn("验证码失效了");
                    responseEntity.setBody(StatusCode.VERIFICATION_CODE_FAILURE.getReason());
                    responseEntity.setCode(StatusCode.VERIFICATION_CODE_FAILURE.getCode());
                    return responseEntity;
                }
            }finally {
                if(redis != null){
                    redis.close();
                }
            }
    }

}
