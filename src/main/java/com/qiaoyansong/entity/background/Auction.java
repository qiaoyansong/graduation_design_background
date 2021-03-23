package com.qiaoyansong.entity.background;

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
    private Integer id;

    @NotNull(message = "拍卖标题不能为空")
    @Pattern(regexp = "^.{1,50}$", message = "拍卖标题不符合格式")
    private String title;

    @NotNull(message = "开始时间不能为空")
    private Date beginTime;

    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    private Date pubdate;

    @NotNull(message = "最低价格不能为空")
    private Integer minPrice;

    @NotNull(message = "最高价格不能为空")
    private Integer maxPrice;

    @NotNull(message = "图片不能为空")
    private String img;

    @Pattern(regexp = "^.{0,100}$", message = "拍卖摘要不符合格式")
    private String summary;

    @NotNull(message = "拍卖内容不能为空")
    private String content;

}