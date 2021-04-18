package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.Auction;
import com.qiaoyansong.entity.background.AuctionRealtimePrice;
import com.qiaoyansong.entity.background.DeliverAuction;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.service.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/4/18 12:14
 * description：支付模块
 */
@Controller
@RequestMapping("/order")
@SessionAttributes(value = {"auctionId", "userLocationsId", "userId"})
public class OrderController {
    
    @Autowired
    private AuctionService auctionService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/index")
    public String test1(String auctionId, String userLocationsId, Model model){
        log.info("进入OrderController.index");
        System.out.println(Thread.currentThread().getName());
        ResponseEntity auctionInfoById = this.auctionService.getAuctionInfoById(auctionId);
        Auction auction = (Auction) auctionInfoById.getBody();
        model.addAttribute("auctionId", auctionId);
        model.addAttribute("userLocationsId", userLocationsId);
        // 获取当前商品的价格以及标题
        ResponseEntity responseEntity = this.auctionService.selectMaxAuctionRealtimePrice(auctionId);
        AuctionRealtimePrice auctionRealtimePrice = (AuctionRealtimePrice) responseEntity.getBody();
        model.addAttribute("userId", auctionRealtimePrice.getUser().getId());
        model.addAttribute("subject", auction.getTitle());
        model.addAttribute("amount", auctionRealtimePrice.getPrice());
        model.addAttribute("body", auction.getSummary());
        return "index";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String pay(){
        log.info("进入OrderController.pay");
        return "alipay.trade.page.pay";
    }

    @RequestMapping(value = "/deliverAuction")
    public String deliverAuction(HttpServletRequest request){
        log.info("进入OrderController.deliverAuction");
        String auctionId = (String) request.getSession().getAttribute("auctionId");
        String userLocationsId = (String) request.getSession().getAttribute("userLocationsId");
        String userId = (String) request.getSession().getAttribute("userId");
        DeliverAuction deliverAuction = new DeliverAuction();
        deliverAuction.setUserId(userId);
        deliverAuction.setUserLocationsId(userLocationsId);
        deliverAuction.setAuctionId(auctionId);
        // 添加订单信息
        this.auctionService.deliverAuction(deliverAuction);
        return "return_url";
    }
}
