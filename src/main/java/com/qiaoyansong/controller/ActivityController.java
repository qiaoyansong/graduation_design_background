package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/29 18:56
 * description：
 */
@RestController
@RequestMapping(path = "/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

    @RequestMapping(path = "/activityList")
    public ResponseEntity getActivitiesList(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入ActivityController.activityList");
        return this.activityService.activityList(pageHelper);
    }

    @RequestMapping(path = "/getActivityInfoById/{id}", method = RequestMethod.GET)
    public ResponseEntity selectActivityInfoById(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入ActivityController.selectActivityInfoById");
        return this.activityService.getActivityInfoById(id);
    }
}
