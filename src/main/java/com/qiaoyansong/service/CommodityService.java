package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:27
 * description：商品
 */
public interface CommodityService {
    /**
     * 管理员删除商品
     * @param id 商品ID
     * @return 返回信息
     */
    ResponseEntity adminDeleteCommodityByID(String id);

    /**
     * 管理员上传商品信息
     * @param commodity 商品信息
     * @return 返回信息
     */
    ResponseEntity adminUploadCommodity(Commodity commodity );

    /**
     * 管理员修改商品信息
     * @param commodity 商品信息
     * @return 返回信息
     */
    ResponseEntity adminUpdateCommodity(com.qiaoyansong.entity.front.Commodity commodity);

    /**
     * 管理员获取商品信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity adminSelectCommodity(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取商品列表
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity commodityList(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取商品信息
     * @param id 商品ID
     * @return 返回信息
     */
    ResponseEntity getCommodityInfoById(String id);

    /**
     * 用户用于兑换商品
     * @param deliverCommodity 兑换商品信息
     * @return 返回信息
     */
    ResponseEntity exchangeCommodity(DeliverCommodity deliverCommodity);

    /**
     * 获取用户兑换商品列表
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity getExchangeCommodityListByUserId(PageHelper<SearchCondition> pageHelper);
}
