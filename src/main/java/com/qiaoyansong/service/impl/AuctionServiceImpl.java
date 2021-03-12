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
}
