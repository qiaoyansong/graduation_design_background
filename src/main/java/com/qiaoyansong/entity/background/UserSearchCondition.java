package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/20 18:19
 * description：
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchCondition {
    @NotNull(message = "排序方式不能为null")
    @Pattern(regexp = "asc|desc", message = "排序方式不符合格式要求")
    private String orderBy;

    @Pattern(regexp = "^$|^[a-zA-Z][a-zA-Z0-9_]{3,19}$", message = "用户名格式不正确")
    private String searchValue;

    @Override
    public String toString() {
        return "UserSearchCondition{" +
                "orderBy='" + orderBy + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
