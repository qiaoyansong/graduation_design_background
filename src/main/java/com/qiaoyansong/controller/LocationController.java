package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.UserLocations;
import com.qiaoyansong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/22 18:04
 * description：
 */
@RequestMapping(path = "/location")
@RestController
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadLocation(@Valid @RequestBody UserLocations userLocations, BindingResult result){
        LOGGER.info("进入LocationController.uploadLocation");
        return this.userService.uploadLocation(userLocations);
    }

    @RequestMapping(path = "/lists/{id}", method = RequestMethod.GET)
    public ResponseEntity getLocations(@PathVariable("id") String id){
        LOGGER.info("进入LocationController.getLocations");
        return this.userService.getLocations(id);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteLocationsById(@PathVariable("id") String id){
        LOGGER.info("进入LocationController.deleteLocationsById");
        return this.userService.deleteLocationsById(id);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseEntity updateLocationById(@Valid @RequestBody UserLocations userLocations, BindingResult result){
        LOGGER.info("进入LocationController.updateLocationById");
        return this.userService.updateLocationById(userLocations);
    }
}
