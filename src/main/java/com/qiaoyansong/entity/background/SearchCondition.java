package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/18 19:18
 * description：数据查询条件
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {

    @NotNull(message = "排序方式不能为null")
    @Pattern(regexp = "asc|desc", message = "排序方式不符合格式要求")
    private String orderBy;

    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{0,50}$", message = "标题格式不正确")
    private String searchValue;

    private String userId;

    @Override
    public String toString() {
        return "SearchCondition{" +
                "orderBy='" + orderBy + '\'' +
                ", searchValue='" + searchValue + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
