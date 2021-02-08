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
public class Activity {
    private Integer id;

    private Byte peoples;

    private Date beginTime;

    private Date endTime;

    private Date pubdate;

    private Byte difficulty;

    private String title;

    private String summary;

    private String content;

}