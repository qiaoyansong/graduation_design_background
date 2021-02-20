package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.AdminService;
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
    private AdminService adminService;

    @RequestMapping(path = "/uploadNews", method = RequestMethod.POST)
    public ResponseEntity uploadNews(@Valid @RequestBody News news, BindingResult bindingResult) {
        return this.adminService.uploadNews(news);
    }

    @RequestMapping(path = "/getNews", method = RequestMethod.POST)
    public ResponseEntity getNews(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.adminService.selectNews(pageHelper);
    }

    @RequestMapping(path = "/updateNews", method = RequestMethod.POST)
    public ResponseEntity updateNewsById(@Valid @RequestBody com.qiaoyansong.entity.front.News news, BindingResult bindingResult) {
        return this.adminService.updateNews(news);
    }

    @RequestMapping(path = "/deleteNewsByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteNewsByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "文章ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteNewsByID");
        return this.adminService.deleteNewsByID(id);
    }

    @RequestMapping(path = "/uploadActivity", method = RequestMethod.POST)
    public ResponseEntity uploadActivity(@Valid @RequestBody Activity activity, BindingResult bindingResult) {
        return this.adminService.uploadActivity(activity);
    }

    @RequestMapping(path = "/getActivity", method = RequestMethod.POST)
    public ResponseEntity getActivity(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.adminService.selectActivity(pageHelper);
    }

    @RequestMapping(path = "/updateActivity", method = RequestMethod.POST)
    public ResponseEntity updateActivityById(@Valid @RequestBody com.qiaoyansong.entity.front.Activity activity, BindingResult bindingResult) {
        return this.adminService.updateActivity(activity);
    }

    @RequestMapping(path = "/deleteActivityByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteActivityByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "活动ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteActivityByID");
        return this.adminService.deleteActivityByID(id);
    }

    @RequestMapping(path = "/uploadCommodity", method = RequestMethod.POST)
    public ResponseEntity uploadCommodity(@Valid @RequestBody Commodity commodity, BindingResult bindingResult) {
        return this.adminService.uploadCommodity(commodity);
    }

    @RequestMapping(path = "/getCommodity", method = RequestMethod.POST)
    public ResponseEntity getCommodity(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.adminService.selectCommodity(pageHelper);
    }

    @RequestMapping(path = "/updateCommodity", method = RequestMethod.POST)
    public ResponseEntity updateCommodityById(@Valid @RequestBody com.qiaoyansong.entity.front.Commodity commodity , BindingResult bindingResult) {
        return this.adminService.updateCommodity(commodity);
    }

    @RequestMapping(path = "/deleteCommodityByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteCommodityByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "商品ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteCommodityByID");
        return this.adminService.deleteCommodityByID(id);
    }

    @RequestMapping(path = "/uploadAuction", method = RequestMethod.POST)
    public ResponseEntity uploadAuction(@Valid @RequestBody Auction auction, BindingResult bindingResult) {
        return this.adminService.uploadAuction(auction);
    }

    @RequestMapping(path = "/getAuction", method = RequestMethod.POST)
    public ResponseEntity getAuction(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        return this.adminService.selectAuction(pageHelper);
    }

    @RequestMapping(path = "/updateAuction", method = RequestMethod.POST)
    public ResponseEntity updateAuctionById(@Valid @RequestBody com.qiaoyansong.entity.front.Auction auction , BindingResult bindingResult) {
        return this.adminService.updateAuction(auction);
    }

    @RequestMapping(path = "/deleteAuctionByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteAuctionByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "拍卖ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteNewsByID");
        return this.adminService.deleteAuctionByID(id);
    }

    @RequestMapping(path = "/deleteUserByID/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteUserByID(@Valid @Pattern(regexp = "^[0-9]*$", message = "用户ID不正确") @PathVariable("id") String id) {
        LOGGER.info("进入AdminController.deleteUserByID");
        return this.adminService.deleteUserByID(id);
    }

    @RequestMapping(path = "/getUsers", method = RequestMethod.POST)
    public ResponseEntity getUsers(@Valid @RequestBody PageHelper<UserSearchCondition> pageHelper, BindingResult bindingResult) {
        return this.adminService.selectUsers(pageHelper);
    }

}
