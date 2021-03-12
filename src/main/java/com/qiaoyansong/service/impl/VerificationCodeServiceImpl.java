package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.background.UserType;
import com.qiaoyansong.entity.front.Admin;
import com.qiaoyansong.service.VerificationCodeService;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.SendMailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:39
 * description：
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);
    private static Jedis redis;
    private static final int VERIFICATION_CODE_LENGTH = 6;
    private final SendMailUtil sendMailUtil = SendMailUtil.getInstance();

    @Override
    public ResponseEntity getAdminVerificationCode(Admin admin) {
        log.info("进入VerificationCodeServiceImpl.getAdminVerificationCode");
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
                        return this.getVerificationCode(admin.getMailbox(), "管理员登录验证码");
                    } else {
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

    @Override
    public ResponseEntity getVerificationCode(String mailBox, String emailTitle) {
        log.info("进入VerificationCodeServiceImpl的getVerificationCode");
        try {
            redis = JedisPoolUtil.getInstance().getResource();
            log.info("检测redis连接" + redis.ping());
            log.info("开始验证验证码是否失效");
            boolean isExists = redis.exists(mailBox);
            ResponseEntity<String> response = new ResponseEntity<>();
            if (isExists) {
                // 验证码还存在
                log.info(StatusCode.VERIFICATION_CODE_NOT_EXPIRED.getReason());
                // 再次延长验证码时间
                redis.expire(mailBox, 60);
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
