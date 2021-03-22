package com.qiaoyansong.entity.front;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/21 20:59
 * description：
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUploadImg {
    /**
     * ID
     */
    @NotNull(message = "用户名不能为空")
    private String id;

    /**
     * 头像
     */
    @NotNull(message = "用户名不能为空")
    private String headPortrait;

    @Override
    public String toString() {
        return "UserUploadImg{" +
                "id='" + id + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}

