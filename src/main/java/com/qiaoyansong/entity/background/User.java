package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    /**
     * User表唯一ID 自动递增
     */
    private Integer id;

    /**
     * 用户类型
     */
    private UserType type;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     *邮箱
     */
    private String mailbox;

    /**
     * 信用 数据库表默认值10.0
     */
    private Float credit;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 登录时候的sessionID
     */
    private String sessionId;

    /**
     * 点数 数据库默认值为0
     */
    private Integer point;

}