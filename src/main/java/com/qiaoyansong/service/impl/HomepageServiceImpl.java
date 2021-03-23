package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.NewsMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.HomepageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/23 18:28
 * description：
 */
@Service
public class HomepageServiceImpl implements HomepageService {
    
    @Autowired
    private NewsMapper newsMapper;
    private static final Logger log = LoggerFactory.getLogger(HomepageServiceImpl.class);
    
    @Override
    public ResponseEntity getListOfHomepageGuoNei() {
        log.info("进入HomepageServiceImpl的getListOfHomepageGuoNei方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageGuoNei();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepageZhengFu() {
        log.info("进入HomepageServiceImpl的getListOfHomepageZhengFu方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageZhengFu();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepageXueXiao() {
        log.info("进入HomepageServiceImpl的getListOfHomepageXueXiao方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageXueXiao();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepageQiYe() {
        log.info("进入HomepageServiceImpl的getListOfHomepageQiYe方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageQiYe();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepageYiRen() {
        log.info("进入HomepageServiceImpl的getListOfHomepageYiRen方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageYiRen();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepageHaoRen() {
        log.info("进入HomepageServiceImpl的getListOfHomepageHaoRen方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageHaoRen();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepageHaoShi() {
        log.info("进入HomepageServiceImpl的getListOfHomepageHaoShi方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepageHaoShi();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity getListOfHomepagePingXuan() {
        log.info("进入HomepageServiceImpl的getListOfHomepagePingXuan方法");
        ResponseEntity responseEntity = new ResponseEntity();
        List<News> news = this.newsMapper.getListOfHomepagePingXuan();
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }
}
