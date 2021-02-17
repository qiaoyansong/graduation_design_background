package com.qiaoyansong.controller;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/17 17:02
 * description：
 */
public class TestImgName {
    @Test
    public void test(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }
}
