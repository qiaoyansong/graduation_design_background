package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.front.Admin;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/12 18:40
 * description：管理员
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param admin 管理员信息
     * @return 返回信息
     */
    ResponseEntity login(Admin admin);

    /**
     * 管理员登录获取验证码
     * @param admin 管理员信息
     * @return 返回信息
     */
    ResponseEntity getAdminVerificationCode(Admin admin);
}
