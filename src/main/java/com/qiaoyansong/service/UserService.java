package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.User;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/7 21:32
 * description：用户服务
 */
public interface UserService {

    /**
     * 注册用户
     * @param user 用户信息
     * @return 返回信息
     */
    ResponseEntity register(com.qiaoyansong.entity.front.User user);

    /**
     * 用户登录
     * @param user 用户信息
     * @return 返回信息
     */
    ResponseEntity login(User user);

    /**
     * 用户退出
     * @param userName 用户名
     * @return 返回信息
     */
    ResponseEntity logout(String userName);

    /**
     * 获取验证码
     * @param mailBox 邮箱名
     * @param emailTitle 邮件标题
     * @return 返回信息
     */
    ResponseEntity getVerificationCode(String mailBox, String emailTitle);
}
