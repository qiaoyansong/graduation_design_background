package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.Commodity;
import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/16 21:13
 * description：
 */
@Mapper
public interface CommodityMapper {
    /**
     * 上传商品
     * @param commodity 商品
     * @return
     */
    Integer uploadCommodity(Commodity commodity);

    /**
     * 根据查询条件查询商品
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<Commodity> getCommodity(PageHelper pageHelper);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

    /**
     * 根据ID修改具体商品
     * @param commodity 商品信息
     * @return
     */
    Integer updateCommodityByID(com.qiaoyansong.entity.front.Commodity commodity);

    /**
     * 根据ID删除具体商品
     * @param id 查询条件
     * @return
     */
    Integer deleteCommodityByID(String id);

    /**
     * 获取商品详情
     * @param id 商品ID
     * @return
     */
    Commodity getCommodityInfoById(String id);
}
