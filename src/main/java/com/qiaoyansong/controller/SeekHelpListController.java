package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.service.SeekHelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/4/8 20:20
 * description：用户求助列表展示以及求助详情展示
 */

@RestController
@RequestMapping(path = "/seekHelpList")
public class SeekHelpListController {
    
    @Autowired
    private SeekHelpService seekHelpService;

    private static final Logger log = LoggerFactory.getLogger(SeekHelpListController.class);

    @RequestMapping(path = "/seekHelpList")
    public ResponseEntity getActivitiesList(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入SeekHelpListController.seekHelpList");
        return this.seekHelpService.seekHelpList(pageHelper);
    }

    @RequestMapping(path = "/getSeekHelpInfoById/{id}", method = RequestMethod.GET)
    public ResponseEntity selectActivityInfoById(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入SeekHelpListController.getSeekHelpInfoById");
        return this.seekHelpService.getSeekHelpInfoById(id);
    }
}
