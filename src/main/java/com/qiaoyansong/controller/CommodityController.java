package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/29 18:57
 * description：
 */
@RestController
@RequestMapping(path = "/commodity")
public class CommodityController {
    
    @Autowired
    private CommodityService commodityService;

    private static final Logger log = LoggerFactory.getLogger(CommodityController.class);


    @RequestMapping(path = "/commodityList")
    public ResponseEntity getCommoditiesList(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入CommodityController.getCommoditiesList");
        return this.commodityService.commodityList(pageHelper);
    }

    @RequestMapping(path = "/getCommodityInfoById/{id}", method = RequestMethod.GET)
    public ResponseEntity selectCommodityInfoById(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入CommodityController.selectCommodityInfoById");
        return this.commodityService.getCommodityInfoById(id);
    }

}
