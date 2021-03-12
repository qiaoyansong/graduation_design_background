package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 21:33
 * description：
 */
@Controller
@RequestMapping(path = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @RequestMapping(path = "/getNews")
    @ResponseBody
    public ResponseEntity getNews(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入NewsController.getNews");
        return this.newsService.userSelectNews(pageHelper);
    }
}
