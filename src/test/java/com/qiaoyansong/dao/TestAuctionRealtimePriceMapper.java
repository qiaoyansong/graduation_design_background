package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.AuctionRealtimePrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/1 16:45
 * description：
 */
@SpringBootTest
public class TestAuctionRealtimePriceMapper {

    @Autowired
    private AuctionRealtimePriceMapper mapper;

    @Test
    public void testGetByID(){
        AuctionRealtimePrice result = mapper.getById(1);
        System.out.println(result);
    }
}
