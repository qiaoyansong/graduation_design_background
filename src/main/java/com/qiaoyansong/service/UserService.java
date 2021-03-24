package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.entity.front.ModifyUserInfo;
import com.qiaoyansong.entity.front.UserUploadImg;

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

    /**
     * 用户上传头像
     * @param userUploadImg 用户ID
     * @return 返回信息
     */
    ResponseEntity userUploadImg(UserUploadImg userUploadImg);

    /**
     * 用户上传收货地址
     * @param userLocations 用户收货地址信息
     * @return 返回信息
     */
    ResponseEntity uploadLocation(UserLocations userLocations);

    /**
     * 用户修改收货地址
     * @param userLocations 用户收货地址信息
     * @return 返回信息
     */
    ResponseEntity updateLocationById(UserLocations userLocations);

    /**
     * 获取用户收货地址
     * @param id 用户ID
     * @return 返回信息
     */
    ResponseEntity getLocations(String id);

    /**
     * 删除用户收货地址
     * @param id 收货地址ID
     * @return 返回信息
     */
    ResponseEntity deleteLocationsById(String id);

    /**
     * 修改用户信息
     * @param modifyUserInfo 用户信息
     * @return 返回信息
     */
    ResponseEntity modifyUserInfo(ModifyUserInfo modifyUserInfo);
}
