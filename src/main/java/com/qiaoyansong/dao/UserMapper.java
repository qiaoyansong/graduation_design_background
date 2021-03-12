package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.front.ModifyPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 删除登录用户的SessionId信息
     * @param userName 用户名
     * @return 返回值
     */
    int removeSessionInfo(String userName);

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return 返回值 User
     */
    User getUserInfo(String userName);

    /**
     * 根据ID删除具体用户
     * @param id 用户ID
     * @return
     */
    Integer deleteUserByID(String id);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

    /**
     * 根据查询条件查询用户
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<User> getUsers(PageHelper<SearchCondition> pageHelper);

    /**
     * 修改用户密码
     * @param modifyPassword 用户信息
     * @return
     */
    Integer modifyPassword(ModifyPassword modifyPassword);
}
