package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public ResponseEntity uploadNews(@Valid @RequestBody News news, BindingResult bindingResult){
        LOGGER.info("进入AdminController.uploadNews");
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            LOGGER.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            return this.adminService.uploadNews(news);
        }
    }

    @RequestMapping(path = "/uploadActivity", method = RequestMethod.POST)
    public ResponseEntity uploadActivity(@Valid @RequestBody Activity activity, BindingResult bindingResult){
        LOGGER.info("进入AdminController.uploadActivity");
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            LOGGER.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            return this.adminService.uploadActivity(activity);
        }
    }

    @RequestMapping(path = "/uploadCommodity", method = RequestMethod.POST)
    public ResponseEntity uploadCommodity(@Valid @RequestBody Commodity commodity, BindingResult bindingResult){
        LOGGER.info("进入AdminController.uploadCommodity");
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            LOGGER.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            return this.adminService.uploadCommodity(commodity);
        }
    }

    @RequestMapping(path = "/uploadAuction", method = RequestMethod.POST)
    public ResponseEntity uploadAuction(@Valid @RequestBody Auction auction, BindingResult bindingResult){
        LOGGER.info("进入AdminController.uploadAuction");
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            LOGGER.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            return this.adminService.uploadAuction(auction);
        }
    }
}
