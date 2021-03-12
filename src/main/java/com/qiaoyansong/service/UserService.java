package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;

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
     * 管理员登录
     * @param admin 管理员信息
     * @return 返回信息
     */
    ResponseEntity adminLogin(com.qiaoyansong.entity.front.User admin);

    /**
     * 用户退出
     * @param userName 用户名
     * @return 返回信息
     */
    ResponseEntity logout(String userName);

    /**
     * 管理员获取用户信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity adminSelectUsers(PageHelper<SearchCondition> pageHelper);

    /**
     * 管理员删除用户
     * @param id 用户ID
     * @return 返回信息
     */
    ResponseEntity adminDeleteUserByID(String id);
}
