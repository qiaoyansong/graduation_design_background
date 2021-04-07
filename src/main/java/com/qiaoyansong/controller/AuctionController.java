package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.service.AuctionService;
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
@RequestMapping(path = "/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    private static final Logger log = LoggerFactory.getLogger(AuctionController.class);

    @RequestMapping(path = "/auctionList")
    public ResponseEntity getAuctionList(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入auctionController.auctionList");
        return this.auctionService.auctionList(pageHelper);
    }

    @RequestMapping(path = "/getAuctionInfoById/{id}", method = RequestMethod.GET)
    public ResponseEntity selectAuctionInfoById(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入auctionController.selectAuctionInfoById");
        return this.auctionService.getAuctionInfoById(id);
    }

    @RequestMapping(path = "/getAuctionRealtimePrice/{id}", method = RequestMethod.GET)
    public ResponseEntity selectAuctionRealtimePrice(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入auctionController.selectAuctionRealtimePrice");
        return this.auctionService.getAuctionRealtimePrice(id);
    }

    @RequestMapping(path = "/getMaxAuctionRealtimePrice/{id}", method = RequestMethod.GET)
    public ResponseEntity selectMaxAuctionRealtimePrice(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入auctionController.selectMaxAuctionRealtimePrice");
        return this.auctionService.selectMaxAuctionRealtimePrice(id);
    }
}
