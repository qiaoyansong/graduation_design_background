package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.NewsMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:52
 * description：
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Override
    public ResponseEntity adminUploadNews(News news) {
        log.info("进入NewsServiceImpl的adminUploadNews方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始检查文章是否重名");
        Integer flag = this.newsMapper.checkNewsTitleIsExists(news.getTitle());
        if (flag == null) {
            log.info("开始上传文章");
            if (this.newsMapper.uploadNews(news) != 1) {
                log.warn("上传文章失败");
                responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
            } else {
                log.info("上传文章成功");
                responseEntity.setBody(StatusCode.SUCCESS.getReason());
                responseEntity.setCode(StatusCode.SUCCESS.getCode());
            }
        } else {
            log.warn("文章标题存在");
            responseEntity.setBody(StatusCode.NEWS_TITLE_IS_EXISTS.getReason());
            responseEntity.setCode(StatusCode.NEWS_TITLE_IS_EXISTS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadNews(News news) {
        log.info("进入NewsServiceImpl的uploadNews方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始检查文章是否重名");
        Integer flag = this.newsMapper.checkNewsTitleIsExists(news.getTitle());
        if (flag == null) {
            log.info("开始上传文章");
            if (this.newsMapper.userUploadNews(news) != 1) {
                log.warn("上传文章失败");
                responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
            } else {
                log.info("上传文章成功");
                responseEntity.setBody(StatusCode.SUCCESS.getReason());
                responseEntity.setCode(StatusCode.SUCCESS.getCode());
            }
        } else {
            log.warn("文章标题存在");
            responseEntity.setBody(StatusCode.NEWS_TITLE_IS_EXISTS.getReason());
            responseEntity.setCode(StatusCode.NEWS_TITLE_IS_EXISTS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminUpdateNews(com.qiaoyansong.entity.front.News news) {
        log.info("进入NewsServiceImpl的adminUpdateNews方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始修改资讯");
        if (this.newsMapper.updateNewsByID(news) != 1) {
            log.warn("修改资讯失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("修改资讯成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminDeleteNewsByID(String id) {
        log.info("进入NewsServiceImpl的adminDeleteNewsByID方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始删除资讯");
        if (this.newsMapper.deleteNewsByID(id) != 1) {
            log.warn("删除资讯失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("删除资讯成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminSelectNews(PageHelper<SearchCondition> pageHelper) {
        log.info("进入NewsServiceImpl的adminSelectNews方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.newsMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<News> news = this.newsMapper.getNews(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity adminSelectUserNews(PageHelper<SearchCondition> pageHelper) {
        log.info("进入NewsServiceImpl的adminSelectUserNews方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.newsMapper.getUserTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<News> news = this.newsMapper.getUserNews(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity userSelectNews(PageHelper<SearchCondition> pageHelper) {
        log.info("进入NewsServiceImpl的userSelectNews方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.newsMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<News> news = this.newsMapper.getNews(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getNewInfoById(String id) {
        log.info("进入NewsServiceImpl的getNewInfoById方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        News news = this.newsMapper.getNewInfoById(id);
        responseEntity.setBody(news);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        return responseEntity;
    }

    @Override
    public ResponseEntity agreeUserNews(String id) {
        log.info("进入NewsServiceImpl的agreeUserNews方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始同意资讯投稿");
        if (this.newsMapper.agreeUserNews(id) != 1) {
            log.warn("同意失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("同意成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity refuseUserNews(String id) {
        log.info("进入NewsServiceImpl的refuseUserNews方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始拒绝此资讯的投稿");
        if (this.newsMapper.refuseUserNews(id) != 1) {
            log.warn("拒绝失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("拒绝成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }
}
