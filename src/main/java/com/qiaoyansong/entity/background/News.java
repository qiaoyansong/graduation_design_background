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
public class News {
    private Integer id;

    @NotNull(message = "文章标题不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,20}$", message = "文章标题格式不正确")
    private String title;
    @NotNull(message = "文章来源不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "文章来源格式不正确")
    private String source;

    private Date pubdate;

    @NotNull(message = "文章摘要不能为空")
    @Pattern(regexp = "^.{1,100}$", message = "文章摘要格式不正确")
    private String summary;

    @NotNull(message = "文章类型不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "文章类型不正确")
    private String type;

    private Long views;

    private Byte flag;

    @NotNull(message = "图片不能为空")
    private String img;

    @NotNull(message = "文章不能为空")
    private String article;


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", pubdate=" + pubdate +
                ", summary='" + summary + '\'' +
                ", type=" + type +
                ", views=" + views +
                ", flag=" + flag +
                ", img='" + img + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}