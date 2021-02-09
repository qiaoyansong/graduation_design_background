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
}
