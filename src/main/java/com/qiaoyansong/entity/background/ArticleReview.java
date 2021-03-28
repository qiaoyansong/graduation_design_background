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
public class ArticleReview {

    private Integer id;

    @NotNull(message = "文章ID不能为空")
    private String articleId;

    @NotNull(message = "用户ID不能为空")
    private String userId;

    private Date pubdate;

    @Pattern(regexp = "^.{1,100}$", message = "评论不符合格式")
    private String content;

    @Override
    public String toString() {
        return "ArticleReview{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", pubdate=" + pubdate +
                ", content='" + content + '\'' +
                '}';
    }
}