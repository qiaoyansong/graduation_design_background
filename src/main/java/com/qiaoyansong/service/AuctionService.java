package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:30
 * description：拍卖
 */
public interface AuctionService {
    /**
     * 管理员上传拍卖商品信息
     * @param auction 卖商品信息
     * @return 返回信息
     */
    ResponseEntity adminUploadAuction(Auction auction);

    /**
     * 管理员删除拍卖
     * @param id 拍卖ID
     * @return 返回信息
     */
    ResponseEntity adminDeleteAuctionByID(String id);

    /**
     * 管理员修改拍卖信息
     * @param auction 拍卖信息
     * @return 返回信息
     */
    ResponseEntity adminUpdateAuction(com.qiaoyansong.entity.front.Auction auction);


    /**
     * 管理员获取拍卖信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity adminSelectAuction(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取拍卖列表
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity auctionList(PageHelper<SearchCondition> pageHelper);

    /**
     * 根据拍卖ID获取拍卖信息
     * @param id 拍卖ID
     * @return 返回信息
     */
    ResponseEntity getAuctionInfoById(String id);

    /**
     * 根据拍卖ID获取拍卖价格实时变化信息
     * @param id 拍卖ID
     * @return 返回信息
     */
    ResponseEntity getAuctionRealtimePrice(String id);

    /**
     * 用户出价
     * @param auctionRealtimePrice 价格变化信息
     * @return 返回信息
     */
    ResponseEntity offer(com.qiaoyansong.entity.front.AuctionRealtimePrice auctionRealtimePrice);

    /**
     * 根据用户ID获取所参与的活动信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity getAuctionProcessByUserId(PageHelper<SearchCondition> pageHelper);

    /**
     * 根据拍卖ID获取拍卖价格实时变化信息
     * @param id 拍卖ID
     * @return 返回信息
     */
    ResponseEntity selectMaxAuctionRealtimePrice(String id);
}
