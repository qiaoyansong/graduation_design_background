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
public class Commodity {
    private Integer id;

    private String title;

    private Date pubdate;

    private String img;

    private Byte quantity;

    private Byte point;

    private String summary;

    private String content;

}