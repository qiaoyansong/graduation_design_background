package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleReview {

    private Integer id;

    private Integer articleId;

    private Integer userId;

    private Date pubdate;

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