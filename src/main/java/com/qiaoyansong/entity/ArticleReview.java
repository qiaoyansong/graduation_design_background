package com.qiaoyansong.entity;

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
    private Integer articleId;

    private Integer userId;

    private Date pubdate;

    private String content;

}