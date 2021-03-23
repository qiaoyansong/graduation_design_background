package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.service.HomepageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/23 18:19
 * description：
 */
@RestController
@RequestMapping(path = "/home")
public class HomepageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomepageController.class);

    @Autowired
    private HomepageService homepageService;


    @RequestMapping("/list/guonei")
    public ResponseEntity getListOfHomepageGuoNei(){
        LOGGER.info("进入HomepageController.getListOfHomepageGuoNei");
        return this.homepageService.getListOfHomepageGuoNei();
    }

    @RequestMapping("/list/zhengfu")
    public ResponseEntity getListOfHomepageZhengFu(){
        LOGGER.info("进入HomepageController.getListOfHomepageZhengFu");
        return this.homepageService.getListOfHomepageZhengFu();
    }

    @RequestMapping("/list/xuexiao")
    public ResponseEntity getListOfHomepageXueXiao(){
        LOGGER.info("进入HomepageController.getListOfHomepageXueXiao");
        return this.homepageService.getListOfHomepageXueXiao();
    }

    @RequestMapping("/list/qiye")
    public ResponseEntity getListOfHomepageQiYe(){
        LOGGER.info("进入HomepageController.getListOfHomepageQiYe");
        return this.homepageService.getListOfHomepageQiYe();
    }

    @RequestMapping("/list/yiren")
    public ResponseEntity getListOfHomepageYiRen(){
        LOGGER.info("进入HomepageController.getListOfHomepageYiRen");
        return this.homepageService.getListOfHomepageYiRen();
    }

    @RequestMapping("/list/haoren")
    public ResponseEntity getListOfHomepageHaoRen(){
        LOGGER.info("进入HomepageController.getListOfHomepageHaoRen");
        return this.homepageService.getListOfHomepageHaoRen();
    }

    @RequestMapping("/list/haoshi")
    public ResponseEntity getListOfHomepageHaoShi(){
        LOGGER.info("进入HomepageController.getListOfHomepageHaoShi");
        return this.homepageService.getListOfHomepageHaoShi();
    }

    @RequestMapping("/list/pingxuan")
    public ResponseEntity getListOfHomepagePingXuan(){
        LOGGER.info("进入HomepageController.getListOfHomepagePingXuan");
        return this.homepageService.getListOfHomepagePingXuan();
    }
}
