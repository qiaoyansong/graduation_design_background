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
public class News {
    private Integer id;

    private String title;

    private String source;

    private Date pubdate;

    private String summary;

    private Byte type;

    private Long views;

    private Byte flag;

    private String img;

    private String article;

}