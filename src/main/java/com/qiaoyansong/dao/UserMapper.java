package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/1 16:36
 * description：
 */
@Mapper
public interface UserMapper {
    /**
     * 验证用户名是否存在
     * @param userName 用户名
     * @return
     */
    Integer checkUserName(String userName);

    /**
     * 验证邮箱是否存在
     * @param mailBox 邮箱
     * @return
     */
    Integer checkMailBox(String mailBox);

    /**
     * 注册用户
     * @param user 注册用户信息
     * @return
     */
    int register(User user);

    /**
     * 检查密码是否正确
     * @param userName 用户名
     * @return 密码
     */
    String getPassword(String userName);

    /**
     * 添加登录用户的SessionId信息
     * @param userName 用户名
     * @param sessionId sessionId
     * @return 返回值
     */
    int insertSessionInfo(@Param("userName") String userName, @Param("sessionId") String sessionId);
}
