package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 根据查询条件查询拍卖
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<Auction> getAuction(PageHelper pageHelper);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

    /**
     * 根据ID修改具体拍卖
     * @param auction 拍卖信息
     * @return
     */
    Integer updateAuctionByID(com.qiaoyansong.entity.front.Auction auction);

    /**
     * 根据ID删除具体拍卖
     * @param id 查询条件
     * @return
     */
    Integer deleteAuctionByID(String id);

    /**
     * 根据查询ID查询拍卖详情
     * @param id 拍卖ID
     * @return
     */
    Auction getAuctionInfoById(String id);

    /**
     * 根据ID获取拍卖价格实时变化表
     * @param id 拍卖ID
     * @return
     */
    List<AuctionRealtimePrice> getAuctionRealtimePrice(String id);

    /**
     * 根据拍卖ID获取当前最高价格
     * @param auctionId 拍卖ID
     * @return
     */
    Integer getMaxPriceByAuctionId(String auctionId);

    /**
     * 用户拍卖出价
     * @param auctionRealtimePrice 出价信息
     * @return
     */
    Integer offer(com.qiaoyansong.entity.front.AuctionRealtimePrice auctionRealtimePrice);

    /**
     * 根据查询条件查询当前用户所参加拍卖的总条数
     * @param condition 查询条件
     * @return
     */
    Integer getSignUpAuctionTotalSize(SearchCondition condition);

    /**
     * 根据查询条件当前用户参加的所有拍卖
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<Auction> getSignUpAuction(PageHelper pageHelper);

    /**
     * 根据ID获取最高的价格
     * @param id 拍卖ID
     * @return
     */
    AuctionRealtimePrice selectMaxAuctionRealtimePrice(String id);
}
