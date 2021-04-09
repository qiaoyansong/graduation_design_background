package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.CommodityMapper;
import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

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

    @Override
    public ResponseEntity commodityList(PageHelper<SearchCondition> pageHelper) {
        log.info("进入CommodityServiceImpl的commodityList方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.commodityMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Commodity> news = this.commodityMapper.getCommodity(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getCommodityInfoById(String id) {
        log.info("进入CommodityServiceImpl的getCommodityInfoById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Commodity commodity = this.commodityMapper.getCommodityInfoById(id);
        responseEntity.setBody(commodity);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity exchangeCommodity(DeliverCommodity deliverCommodity) {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("进入CommodityServiceImpl的exchangeCommodity方法");
        log.info("开始检查对应用户是否有所需点数");
        Integer curPoint = this.userMapper.getPointByUserId(deliverCommodity.getUserId());
        Commodity commodity = this.commodityMapper.getCommodityInfoId(deliverCommodity.getCommodityId());
        if (curPoint < commodity.getPoint()) {
            log.warn("用户点数不足，无法兑换此商品");
            responseEntity.setBody(StatusCode.INSUFFICIENT_POINTS.getReason());
            responseEntity.setCode(StatusCode.INSUFFICIENT_POINTS.getCode());
        } else {
            log.info("用户点数足够，开始兑换商品");
            log.info("开始查看该商品剩余库存");
            if (commodity.getQuantity() >= 1) {
                log.info("商品剩余库存足够");
                log.info("开始减去商品对应库存");
                if (this.commodityMapper.subQuantityByCommodityId(deliverCommodity.getCommodityId()) == 1) {
                    log.info("减去商品对应库存成功");
                    log.info("开始减去用户对应积分");
                    if (this.userMapper.subPointByUserId(deliverCommodity.getUserId(), commodity.getPoint()) == 1) {
                        log.info("减去用户对应积分成功");
                        log.info("开始往兑换商品发货表中插入数据");
                        if (this.commodityMapper.exchangeCommodity(deliverCommodity) == 1) {
                            log.info("兑换商品发货表中插入数据成功，事务提交");
                            responseEntity.setBody(StatusCode.SUCCESS.getReason());
                            responseEntity.setCode(StatusCode.SUCCESS.getCode());
                        } else {
                            log.info("兑换商品发货表中插入数据失败，事务整体回滚");
                            try {
                                throw new RuntimeException("修改用户积分信息失败");
                            } catch (Exception ex) {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                dataSourceTransactionManager.rollback(transactionStatus);
                                responseEntity.setBody(ex.getMessage());
                                responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                                return responseEntity;
                            }
                        }
                    } else {
                        log.warn("减去用户对应积分失败，事务整体回滚");
                        try {
                            throw new RuntimeException("修改用户积分信息失败");
                        } catch (Exception ex) {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            dataSourceTransactionManager.rollback(transactionStatus);
                            responseEntity.setBody(ex.getMessage());
                            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                            return responseEntity;
                        }
                    }
                } else {
                    log.warn("减去商品对应库存失败");
                    responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                    responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                }
            } else {
                log.info("商品剩余库存不足");
                responseEntity.setBody(StatusCode.OUT_OF_STOCK.getReason());
                responseEntity.setCode(StatusCode.OUT_OF_STOCK.getCode());
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getExchangeCommodityListByUserId(PageHelper<SearchCondition> pageHelper) {
        log.info("进入CommodityServiceImpl的getExchangeCommodityListByUserId方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.commodityMapper.getExchangeCommodityTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<DeliverCommodity> news = this.commodityMapper.getExchangeCommodityListByUserId(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }
}
