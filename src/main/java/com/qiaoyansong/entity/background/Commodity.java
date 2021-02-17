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
public class Commodity {
    private Integer id;

    @NotNull(message = "商品标题不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,50}$", message = "商品标题不符合格式")
    private String title;

    private Date pubdate;

    @NotNull(message = "图片不能为空")
    private String img;

    @NotNull(message = "数量不能为空")
    private Byte quantity;

    @NotNull(message = "点数不能为空")
    private Byte point;

    @Pattern(regexp = "^.{0,100}$", message = "商品摘要不符合格式")
    private String summary;

    @NotNull(message = "商品内容不能为空")
    private String content;

}