package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.entity.background.SeekHelp;
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
 * @date ：Created in 2021/3/24 17:33
 * description：
 */
@RestController
@RequestMapping(path = "/seekHelp")
public class SeekHelpController {

    @Autowired
    private SeekHelpService seekHelpService;

    private static final Logger log = LoggerFactory.getLogger(SeekHelpController.class);

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public ResponseEntity seekHelpSubmit(@Valid @RequestBody SeekHelp seekHelp, BindingResult bindingResult){
        log.info("进入SeekHelpController.seekHelpSubmit");
        return this.seekHelpService.submit(seekHelp);
    }

    @RequestMapping(path = "/agree/{id}", method = RequestMethod.GET)
    public ResponseEntity agreeSeekHelp(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        return this.seekHelpService.agree(id);
    }

    @RequestMapping(path = "/refuse/{id}", method = RequestMethod.GET)
    public ResponseEntity refuseSeekHelp(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        return this.seekHelpService.refuse(id);
    }

    @RequestMapping(path = "/getDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity getSeekHelpInfoById(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        return this.seekHelpService.getSeekHelpInfoById(id);
    }


    @RequestMapping(path = "/listall")
    @ResponseBody
    public ResponseEntity seekHelpList(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入NewsController.getNews");
        return this.seekHelpService.listAll(pageHelper);
    }
}
