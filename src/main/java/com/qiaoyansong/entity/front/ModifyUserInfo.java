package com.qiaoyansong.entity.front;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/24 20:23
 * description：
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyUserInfo {
    /**
     * User表唯一ID 自动递增
     */
    @NotNull(message = "用户ID不能为空")
    private String id;

    /**
     * 信用 数据库表默认值10.0
     */
    private Float credit;
}
