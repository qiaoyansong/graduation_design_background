package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.CommodityMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.CommodityService;
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
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    private static final Logger log = LoggerFactory.getLogger(CommodityServiceImpl.class);


    @Override
    public ResponseEntity adminDeleteCommodityByID(String id) {
        log.info("进入CommodityServiceImpl的adminDeleteCommodityByID方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始删除商品");
        if (this.commodityMapper.deleteCommodityByID(id) != 1) {
            log.warn("删除商品失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("删除商品成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminUploadCommodity(Commodity commodity) {
        log.info("进入CommodityServiceImpl的adminUploadCommodity方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始上传商品");
        if (this.commodityMapper.uploadCommodity(commodity) != 1) {
            log.warn("上传商品失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("上传商品成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminUpdateCommodity(com.qiaoyansong.entity.front.Commodity commodity) {
        log.info("进入CommodityServiceImpl的adminUpdateCommodity方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始修改商品");
        if (this.commodityMapper.updateCommodityByID(commodity) != 1) {
            log.warn("修改商品失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("修改商品成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminSelectCommodity(PageHelper<SearchCondition> pageHelper) {
        log.info("进入CommodityServiceImpl的adminSelectCommodity方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.commodityMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Commodity> news = this.commodityMapper.getCommodity(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }
}
