package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.entity.front.AuctionRealtimePrice;
import com.qiaoyansong.entity.front.DeliverAuction;
import com.qiaoyansong.service.ActivityService;
import com.qiaoyansong.service.AuctionService;
import com.qiaoyansong.service.CommodityService;
import com.qiaoyansong.service.UserService;
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

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommodityService commodityService;

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

    @RequestMapping(path = "/offer", method = RequestMethod.POST)
    public ResponseEntity offer(@Valid @RequestBody AuctionRealtimePrice auctionRealtimePrice, BindingResult bindingResult) {
        log.info("进入UserController.offer");
        return this.auctionService.offer(auctionRealtimePrice);
    }

    @RequestMapping(path = "/getAuctionProcessByUserId", method = RequestMethod.POST)
    public ResponseEntity getAuctionProcessByUserId(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        log.info("进入UserController.getAuctionProcessByUserId");
        return this.auctionService.getAuctionProcessByUserId(pageHelper);
    }

    @RequestMapping(path = "/getAddressListByUserId")
    public ResponseEntity getAddressListByUserId(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入UserController.getAddressListByUserId");
        return this.userService.getAddressListByUserId(pageHelper);
    }

    @RequestMapping(path = "/exchangeCommodity")
    public ResponseEntity exchangeCommodity(@Valid @RequestBody DeliverCommodity deliverCommodity, BindingResult bindingResult){
        log.info("进入UserController.exchangeCommodity");
        return this.commodityService.exchangeCommodity(deliverCommodity);
    }

    @RequestMapping(path = "/getExchangeCommodityListByUserId")
    public ResponseEntity getExchangeCommodityListByUserId(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入UserController.getExchangeCommodityListByUserId");
        return this.commodityService.getExchangeCommodityListByUserId(pageHelper);
    }

    @RequestMapping(path = "/getIsPayment")
    public ResponseEntity getIsPayment(@Valid @RequestBody DeliverAuction deliverAuction, BindingResult bindingResult){
        log.info("进入UserController.getIsPayment");
        return this.auctionService.getIsPayment(deliverAuction);
    }

    @RequestMapping(path = "/getExchangeAuctionListByUserId")
    public ResponseEntity getExchangeAuctionListByUserId(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入UserController.getExchangeAuctionListByUserId");
        return this.auctionService.getExchangeAuctionListByUserId(pageHelper);
    }
}
