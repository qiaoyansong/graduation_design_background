package com.qiaoyansong.entity.front;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Commodity {

    @NotNull(message = "商品ID不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "商品ID不正确")
    private String id;

    @NotNull(message = "商品标题不能为空")
    @Pattern(regexp = "^.{1,50}$", message = "商品标题不符合格式")
    private String title;

    @NotNull(message = "数量不能为空")
    private Byte quantity;

    @NotNull(message = "点数不能为空")
    private Byte point;

    @Pattern(regexp = "^.{0,100}$", message = "商品摘要不符合格式")
    private String summary;

}