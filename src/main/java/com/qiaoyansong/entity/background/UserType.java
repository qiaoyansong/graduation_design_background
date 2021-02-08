package com.qiaoyansong.entity.background;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/7 20:26
 * description：用户枚举类型
 */
public enum UserType {
    /**
     * 普通用户
     */
    GENERAL_USER("普通用户","0"),
    /**
     * 维护公益资讯的超级管理员
     */
    ADMINISTRATOR_INFORMATION("维护公益资讯的超级管理员","1"),
    /**
     * 维护公益活动的超级管理员
     */
    ADMINISTRATOR_ACTIVITY("维护公益活动的超级管理员","2"),
    /**
     * 维护求助信息的超级管理员
     */
    ADMINISTRATOR_HELP_INFORMATION("维护求助信息的超级管理员","3"),
    /**
     * 维护拍卖信息的超级管理员
     */
    ADMINISTRATOR_AUCTION_INFORMATION("维护拍卖信息的超级管理员","4"),
    /**
     * 维护公益商品的超级管理员
     */
    ADMINISTRATOR_PUBLIC_GOODS("维护公益商品的超级管理员","5");
    private final String description;
    private final String type;

    /**
     * @param description 用户类型描述
     * @param type   用户类型
     */
    private UserType(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
