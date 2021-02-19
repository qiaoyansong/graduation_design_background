package com.qiaoyansong.entity.front;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/19 17:02
 * description：
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News {

    @NotNull(message = "文章ID不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "文章ID不正确")
    private String id;

    @NotNull(message = "文章标题不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,50}$", message = "文章标题格式不正确")
    private String title;

    @NotNull(message = "文章来源不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "文章来源格式不正确")
    private String source;

    @NotNull(message = "文章摘要不能为空")
    @Pattern(regexp = "^.{1,100}$", message = "文章摘要格式不正确")
    private String summary;

    @NotNull(message = "文章类型不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "文章类型不正确")
    private String type;
}
