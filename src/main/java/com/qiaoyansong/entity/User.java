package com.qiaoyansong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Integer id;

    private Byte type;

    private String userName;

    private String password;

    private String mailbox;

    private Float credit;

    private String headPortrait;

    private String sessionId;

    private Integer point;

}