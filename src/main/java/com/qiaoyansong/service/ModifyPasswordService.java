package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.front.ModifyPassword;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/7 21:25
 * description：
 */
public interface ModifyPasswordService {
    /**
     * 修改密码
     * @param modifyPassword 名字、密码以及邮件信息
     * @return 返回信息
     */
    ResponseEntity modifyPassword(ModifyPassword modifyPassword);

    /**
     * 获取验证码
     * @param modifyPassword 名字以及邮件信息
     * @return 返回信息
     */
    ResponseEntity getVerificationCode(ModifyPassword modifyPassword);
}
