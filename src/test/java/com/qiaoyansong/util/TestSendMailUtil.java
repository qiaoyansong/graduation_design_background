package com.qiaoyansong.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

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

    @Test
    public void testThreadDateTime(){
        LocalDateTime curTime = LocalDateTime.now();
        String outTradeNo = "";
        outTradeNo += String.valueOf(curTime.getYear());
        outTradeNo += String.valueOf(curTime.getMonthValue());
        outTradeNo += String.valueOf(curTime.getDayOfYear() + 1);
        outTradeNo += String.valueOf(curTime.getHour());
        outTradeNo += String.valueOf(curTime.getMinute());
        outTradeNo += String.valueOf(curTime.getSecond());
        outTradeNo += String.valueOf(curTime.get(ChronoField.MILLI_OF_SECOND));
        System.out.println(outTradeNo);
    }
}
