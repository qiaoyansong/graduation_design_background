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
public class Auction {
    private Integer id;

    private String title;

    private Date beginTime;

    private Date endTime;

    private Date pubdate;

    private Integer minPrice;

    private Integer maxPrice;

    private String img;

    private String summary;

    private String content;

}