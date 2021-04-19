package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.AuctionMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:50
 * description：
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionMapper auctionMapper;
    private static final Logger log = LoggerFactory.getLogger(AuctionServiceImpl.class);

    @Override
    public ResponseEntity adminUploadAuction(Auction auction) {
        log.info("进入AuctionServiceImpl的adminUploadAuction方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始上传公益拍卖");
        if (this.auctionMapper.uploadAuction(auction) != 1) {
            log.warn("上传公益拍卖失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("上传公益拍卖成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminDeleteAuctionByID(String id) {
        log.info("进入AuctionServiceImpl的adminDeleteAuctionByID方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始删除拍卖");
        if (this.auctionMapper.deleteAuctionByID(id) != 1) {
            log.warn("删除拍卖失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("删除拍卖成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminUpdateAuction(com.qiaoyansong.entity.front.Auction auction) {
        log.info("进入AuctionServiceImpl的adminUpdateAuction方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始修改拍卖信息");
        if (this.auctionMapper.updateAuctionByID(auction) != 1) {
            log.warn("修改拍卖失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("修改拍卖成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminSelectAuction(PageHelper<SearchCondition> pageHelper) {
        log.info("进入AuctionServiceImpl的adminSelectAuction方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.auctionMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Auction> news = this.auctionMapper.getAuction(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity auctionList(PageHelper<SearchCondition> pageHelper) {
        log.info("进入AuctionServiceImpl的auctionList方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.auctionMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Auction> news = this.auctionMapper.getAuction(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getAuctionInfoById(String id) {
        log.info("进入AuctionServiceImpl的getAuctionInfoById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Auction auction = this.auctionMapper.getAuctionInfoById(id);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(auction);
        return responseEntity;
    }

    @Override
    public ResponseEntity getAuctionRealtimePrice(String id) {
        log.info("进入AuctionServiceImpl的getAuctionRealtimePrice方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<AuctionRealtimePrice> news = this.auctionMapper.getAuctionRealtimePrice(id);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity offer(com.qiaoyansong.entity.front.AuctionRealtimePrice auctionRealtimePrice) {
        log.info("进入AuctionServiceImpl的getAuctionRealtimePrice方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("获取当前的最高价格");
        Integer maxPrice = this.auctionMapper.getMaxPriceByAuctionId(auctionRealtimePrice.getAuctionId());
        if(maxPrice != null){
            auctionRealtimePrice.setPrice(Integer.valueOf(auctionRealtimePrice.getPrice()) + maxPrice+"");
        }else{
            // 如果当前价格为空，获取最低价格
            Auction auction = this.auctionMapper.getAuctionInfoById(auctionRealtimePrice.getAuctionId());
            auctionRealtimePrice.setPrice(Integer.valueOf(auctionRealtimePrice.getPrice()) + auction.getMinPrice()+"");
        }
        log.info("开始新增价格实时变化信息");
        if (this.auctionMapper.offer(auctionRealtimePrice) != 1) {
            log.warn("新增价格实时变化信息失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("新增价格实时变化信息成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getAuctionProcessByUserId(PageHelper<SearchCondition> pageHelper) {
        log.info("进入AuctionServiceImpl的getAuctionProcessByUserId方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.auctionMapper.getSignUpAuctionTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Auction> auctions = this.auctionMapper.getSignUpAuction(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(auctions);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity selectMaxAuctionRealtimePrice(String id) {
        log.info("进入AuctionServiceImpl的selectMaxAuctionRealtimePrice方法");
        ResponseEntity responseEntity = new ResponseEntity();
        AuctionRealtimePrice auctionRealtimePrice = this.auctionMapper.selectMaxAuctionRealtimePrice(id);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(auctionRealtimePrice);
        return responseEntity;
    }

    @Override
    public ResponseEntity getIsPayment(com.qiaoyansong.entity.front.DeliverAuction deliverAuction) {
        log.info("进入AuctionServiceImpl的getIsPayment方法");
        ResponseEntity responseEntity = new ResponseEntity();
        DeliverAuction deliverAuction1 = this.auctionMapper.getIsPayment(deliverAuction);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(deliverAuction1);
        return responseEntity;
    }

    @Override
    public ResponseEntity deliverAuction(DeliverAuction deliverAuction) {
        log.info("进入AuctionServiceImpl的deliverAuction方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始添加拍卖商品发货表的信息");
        if (this.auctionMapper.deliverAuction(deliverAuction) != 1) {
            log.warn("添加拍卖商品发货表的信息失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("添加拍卖商品发货表的信息成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity deliverAuctionById(String deliverAuctionId) {
        log.info("进入AuctionServiceImpl的deliverAuctionById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始发货");
        if (this.auctionMapper.deliverAuctionById(deliverAuctionId) != 1) {
            log.warn("发货失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("发货成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminGetExchangeAuctionList(PageHelper<SearchCondition> pageHelper) {
        log.info("进入AuctionServiceImpl的adminGetExchangeAuctionList方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.auctionMapper.getExchangeAuctionTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<DeliverAuction> news = this.auctionMapper.getExchangeAuctionListByUserId(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getExchangeAuctionListByUserId(PageHelper<SearchCondition> pageHelper) {
        log.info("进入AuctionServiceImpl的getExchangeCommodityListByUserId方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.auctionMapper.getExchangeAuctionTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<DeliverAuction> news = this.auctionMapper.getExchangeAuctionListByUserId(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }
}
