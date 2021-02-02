package com.qiaoyansong.util;

import org.junit.jupiter.api.Test;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/2 18:15
 * description：
 */
public class TestSendMailUtil {

    private SendMailUtil instance = SendMailUtil.getInstance();

    @Test
    public void testSendMailUtil(){
        System.out.println(instance.sendMail("123456", "hh", "hh"));
    }
}
