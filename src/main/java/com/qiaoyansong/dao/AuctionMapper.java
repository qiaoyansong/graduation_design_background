package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.Auction;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/16 16:42
 * description：
 */
@Mapper
public interface AuctionMapper {
    /**
     * 上传拍卖
     * @param auction 拍卖信息
     * @return
     */
    Integer uploadAuction(Auction auction);

}
