package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.Auction;
import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.SearchCondition;
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
     * @param news 拍卖信息
     * @return
     */
//    Integer updateActivityByID(com.qiaoyansong.entity.front.News news);

    /**
     * 根据ID删除具体拍卖
     * @param id 查询条件
     * @return
     */
    Integer deleteAuctionByID(String id);

}
