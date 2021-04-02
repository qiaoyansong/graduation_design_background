package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ArticleReview;
import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 21:33
 * description：
 */
@RestController
@RequestMapping(path = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @RequestMapping(path = "/getNews")
    public ResponseEntity getNews(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入NewsController.getNews");
        return this.newsService.userSelectNews(pageHelper);
    }

    @RequestMapping(path = "/newsList")
    public ResponseEntity getNewsList(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult){
        log.info("进入NewsController.newsList");
        return this.newsService.newsList(pageHelper);
    }

    @RequestMapping(path = "/getNewInfoById/{id}", method = RequestMethod.GET)
    public ResponseEntity selectNewInfoById(@Valid @NotNull(message = "不能为空") @PathVariable("id") String id) {
        log.info("进入NewsController.selectNewInfoById");
        return this.newsService.getNewInfoById(id);
    }

    @RequestMapping(path = "/addArticleReview", method = RequestMethod.POST)
    public ResponseEntity addArticleReview(@Valid @RequestBody ArticleReview articleReview, BindingResult bindingResult) {
        log.info("进入NewsController.addArticleReview");
        return this.newsService.addArticleReview(articleReview);
    }

    @RequestMapping(path = "/getArticleReviews", method = RequestMethod.POST)
    public ResponseEntity getArticleReviews(@Valid @RequestBody PageHelper<SearchCondition> pageHelper, BindingResult bindingResult) {
        log.info("进入NewsController.getArticleReviews");
        return this.newsService.getArticleReviews(pageHelper);
    }

    @RequestMapping(path = "/getLastedNews", method = RequestMethod.GET)
    public ResponseEntity lastedNewsList() {
        log.info("进入NewsController.getLastedReviews");
        return this.newsService.getLastedNews();
    }

    @RequestMapping(path = "/getHotNews", method = RequestMethod.GET)
    public ResponseEntity hotNewsList() {
        log.info("进入NewsController.getHotNews");
        return this.newsService.getHotNews();
    }
}
