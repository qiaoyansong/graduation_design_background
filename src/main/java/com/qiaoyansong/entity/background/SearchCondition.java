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

    @Pattern(regexp = "^$|^.{0,50}$", message = "标题格式不正确")
    private String searchValue;

    private String userId;

    @Pattern(regexp = "^$|0|1", message = "标志位不符合格式要求")
    private String flag;

    @Pattern(regexp = "^$|views", message = "排序字段格式要求")
    private String orderByValue;

    private String articleId;

    private String activityId;

    @Override
    public String toString() {
        return "SearchCondition{" +
                "orderBy='" + orderBy + '\'' +
                ", searchValue='" + searchValue + '\'' +
                ", userId='" + userId + '\'' +
                ", flag='" + flag + '\'' +
                ", orderByValue='" + orderByValue + '\'' +
                ", articleId='" + articleId + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }
}
