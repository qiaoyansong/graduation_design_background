package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.Commodity;
import org.apache.ibatis.annotations.Mapper;

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
}
