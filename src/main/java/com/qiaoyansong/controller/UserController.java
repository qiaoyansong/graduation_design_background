package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.entity.background.UserActivity;
import com.qiaoyansong.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/4/5 17:03
 * description：
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ActivityService activityService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(path = "/signUp", method = RequestMethod.POST)
    public ResponseEntity signUp(@Valid @RequestBody UserActivity userActivity, BindingResult bindingResult) {
        log.info("进入UserController.signUp");
        return this.activityService.signUp(userActivity);
    }

    @RequestMapping(path = "/getActivityProcessByUserId", method = RequestMethod.POST)
    public ResponseEntity getActivityProcessByUserId(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        log.info("进入UserController.getActivityProcessByUserId");
        return this.activityService.getActivityProcessByUserId(pageHelper);
    }

    @RequestMapping(path = "/getParticipantByActivityId", method = RequestMethod.POST)
    public ResponseEntity getParticipantByActivityId(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        log.info("进入UserController.getParticipantByActivityId");
        return this.activityService.getParticipantByActivityId(pageHelper);
    }

    @RequestMapping(path = "/updateActivityProcess", method = RequestMethod.POST)
    public ResponseEntity updateActivityProcess(@Valid @RequestBody UserActivity userActivity, BindingResult bindingResult) {
        log.info("进入UserController.updateActivityProcess");
        return this.activityService.updateActivityProcess(userActivity);
    }

    @RequestMapping(path = "/getUserActivityInfo", method = RequestMethod.POST)
    public ResponseEntity getUserActivityInfo(@Valid @RequestBody UserActivity userActivity, BindingResult bindingResult) {
        log.info("进入UserController.getUserActivityInfo");
        return this.activityService.getUserActivityInfo(userActivity);
    }
}
