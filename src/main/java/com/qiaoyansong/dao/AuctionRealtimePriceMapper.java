package com.qiaoyansong.dao;

import com.qiaoyansong.entity.AuctionRealtimePrice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/1 16:37
 * description：
 */
@Mapper
public interface AuctionRealtimePriceMapper {
    /**
     * 根据拍卖商品ID 获取对应信息
     * @param id 拍卖商品ID
     *
     * @return
     */
    AuctionRealtimePrice getById(int id);
}
