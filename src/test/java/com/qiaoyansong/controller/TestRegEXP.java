package com.qiaoyansong.controller;

import org.junit.jupiter.api.Test;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/7 20:51
 * description：测试正则表达式
 */
public class TestRegEXP {
    @Test
    public void testUserName(){
        String s = "aa";
        String reg = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$";
        System.out.println(s.matches(reg));
    }

    @Test
    public void testPassword(){
        String s = "qiao683586890";
        String reg = "[a-zA-Z]\\w{9,19}$";
        System.out.println(s.matches(reg));
    }

    @Test
    public void testVerificationCode(){
        String s = "123456";
        String reg = "\\d{6}$";
        System.out.println(s.matches(reg));
    }
}
