package com.qiaoyansong.entity.background;

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
public class SeekHelp {

    private String id;

    @NotNull(message = "用户ID不能为空")
    private String userId;

    @NotNull(message = "用户求助标题不能为空")
    @Pattern(regexp = "^.{1,50}$", message = "用户求助标题格式不正确")
    private String title;

    private Byte flag;

    @NotNull(message = "内容不能为空")
    private String content;

    @NotNull(message = "图片不能为空")
    private String img;

    @Override
    public String toString() {
        return "SeekHelp{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}