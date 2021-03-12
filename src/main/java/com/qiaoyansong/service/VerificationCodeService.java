package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.front.Admin;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:34
 * description：
 */
public interface VerificationCodeService {

    /**
     * 管理员登录获取验证码
     * @param admin 管理员信息
     * @return 返回信息
     */
    ResponseEntity getAdminVerificationCode(Admin admin);

    /**
     * 获取验证码
     * @param mailBox 邮箱名
     * @param emailTitle 邮件标题
     * @return 返回信息
     */
    ResponseEntity getVerificationCode(String mailBox, String emailTitle);
}
