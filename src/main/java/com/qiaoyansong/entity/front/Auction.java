package com.qiaoyansong.entity.front;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auction {

    @NotNull(message = "拍卖ID不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "拍卖ID不正确")
    private String id;

    @NotNull(message = "拍卖标题不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,50}$", message = "拍卖标题不符合格式")
    private String title;

    @NotNull(message = "开始时间不能为空")
    private Date beginTime;

    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    @NotNull(message = "最低价格不能为空")
    private Integer minPrice;

    @NotNull(message = "最高价格不能为空")
    private Integer maxPrice;

    @Pattern(regexp = "^.{0,100}$", message = "拍卖摘要不符合格式")
    private String summary;

}