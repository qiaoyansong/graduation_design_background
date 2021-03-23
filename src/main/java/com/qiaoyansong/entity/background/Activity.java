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
public class Activity {
    private Integer id;

    @NotNull(message = "人数不能为空")
    private Byte peoples;

    @NotNull(message = "开始时间不能为空")
    private Date beginTime;

    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    private Date pubdate;

    @NotNull(message = "活动难度不能为空")
    private Byte difficulty;

    @NotNull(message = "活动标题不能为空")
    @Pattern(regexp = "^.{1,50}$", message = "活动标题不符合格式")
    private String title;

    @Pattern(regexp = "^.{0,100}$", message = "活动摘要不符合格式")
    private String summary;

    @NotNull(message = "活动内容不能为空")
    private String content;

}