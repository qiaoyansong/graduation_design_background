package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/16 16:08
 * description：管理员控制器
 */
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private NewsService newsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/uploadNews", method = RequestMethod.POST)
    public ResponseEntity uploadNews(@Valid @RequestBody News news, BindingResult bindingResult) {
        return this.newsService.adminUploadNews(news);
    }

    @RequestMapping(path = "/getNews", method = RequestMethod.POST)
    public ResponseEntity getNews(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.newsService.adminSelectNews(pageHelper);
    }

    @RequestMapping(path = "/updateNews", method = RequestMethod.POST)
    public ResponseEntity updateNewsById(@Valid @RequestBody com.qiaoyansong.entity.front.News news, BindingResult bindingResult) {
        return this.newsService.adminUpdateNews(news);
    }

    @RequestMapping(path = "/deleteNewsByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteNewsByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "文章ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteNewsByID");
        return this.newsService.adminDeleteNewsByID(id);
    }

    @RequestMapping(path = "/uploadActivity", method = RequestMethod.POST)
    public ResponseEntity uploadActivity(@Valid @RequestBody Activity activity, BindingResult bindingResult) {
        return this.activityService.adminUploadActivity(activity);
    }

    @RequestMapping(path = "/getActivity", method = RequestMethod.POST)
    public ResponseEntity getActivity(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.activityService.adminSelectActivity(pageHelper);
    }

    @RequestMapping(path = "/updateActivity", method = RequestMethod.POST)
    public ResponseEntity updateActivityById(@Valid @RequestBody com.qiaoyansong.entity.front.Activity activity, BindingResult bindingResult) {
        return this.activityService.adminUpdateActivity(activity);
    }

    @RequestMapping(path = "/deleteActivityByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteActivityByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "活动ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteActivityByID");
        return this.activityService.adminDeleteActivityByID(id);
    }

    @RequestMapping(path = "/uploadCommodity", method = RequestMethod.POST)
    public ResponseEntity uploadCommodity(@Valid @RequestBody Commodity commodity, BindingResult bindingResult) {
        return this.commodityService.adminUploadCommodity(commodity);
    }

    @RequestMapping(path = "/getCommodity", method = RequestMethod.POST)
    public ResponseEntity getCommodity(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.commodityService.adminSelectCommodity(pageHelper);
    }

    @RequestMapping(path = "/updateCommodity", method = RequestMethod.POST)
    public ResponseEntity updateCommodityById(@Valid @RequestBody com.qiaoyansong.entity.front.Commodity commodity , BindingResult bindingResult) {
        return this.commodityService.adminUpdateCommodity(commodity);
    }

    @RequestMapping(path = "/deleteCommodityByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteCommodityByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "商品ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteCommodityByID");
        return this.commodityService.adminDeleteCommodityByID(id);
    }

    @RequestMapping(path = "/uploadAuction", method = RequestMethod.POST)
    public ResponseEntity uploadAuction(@Valid @RequestBody Auction auction, BindingResult bindingResult) {
        return this.auctionService.adminUploadAuction(auction);
    }

    @RequestMapping(path = "/getAuction", method = RequestMethod.POST)
    public ResponseEntity getAuction(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.auctionService.adminSelectAuction(pageHelper);
    }

    @RequestMapping(path = "/updateAuction", method = RequestMethod.POST)
    public ResponseEntity updateAuctionById(@Valid @RequestBody com.qiaoyansong.entity.front.Auction auction , BindingResult bindingResult) {
        return this.auctionService.adminUpdateAuction(auction);
    }

    @RequestMapping(path = "/deleteAuctionByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteAuctionByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "拍卖ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteNewsByID");
        return this.auctionService.adminDeleteAuctionByID(id);
    }

    @RequestMapping(path = "/deleteUserByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteUserByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "用户ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteUserByID");
        return this.userService.adminDeleteUserByID(id);
    }

    @RequestMapping(path = "/getUsers", method = RequestMethod.POST)
    public ResponseEntity getUsers(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.userService.adminSelectUsers(pageHelper);
    }

}
