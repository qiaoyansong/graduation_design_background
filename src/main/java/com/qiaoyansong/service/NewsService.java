package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:26
 * description：文章
 */
public interface NewsService {
    /**
     * 管理员上传文章
     * @param news 文章信息
     * @return 返回信息
     */
    ResponseEntity adminUploadNews(News news);

    /**
     * 普通用户上传文章
     * @param news 文章信息
     * @return 返回信息
     */
    ResponseEntity uploadNews(News news);


    /**
     * 管理员修改文章
     * @param news 文章信息
     * @return 返回信息
     */
    ResponseEntity adminUpdateNews(com.qiaoyansong.entity.front.News news);

    /**
     * 管理员删除文章
     * @param id 文章ID
     * @return 返回信息
     */
    ResponseEntity adminDeleteNewsByID(String id);

    /**
     * 管理员获取资讯信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity adminSelectNews(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取资讯列表
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity newsList(PageHelper<SearchCondition> pageHelper);

    /**
     * 管理员获取用户投稿
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity adminSelectUserNews(PageHelper<SearchCondition> pageHelper);

    /**
     * 用户获取资讯信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity userSelectNews(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取投稿信息
     * @param id 投稿ID
     * @return 返回信息
     */
    ResponseEntity getNewInfoById(String id);

    /**
     * 同意用户投稿
     * @param id 投稿ID
     * @return 返回信息
     */
    ResponseEntity agreeUserNews(String id);

    /**
     * 拒绝用户投稿
     * @param id 投稿ID
     * @return 返回信息
     */
    ResponseEntity refuseUserNews(String id);

    /**
     * 追加文章评论
     * @param articleReview 文章评论信息
     * @return 返回信息
     */
    ResponseEntity addArticleReview(ArticleReview articleReview);

    /**
     * 根据筛选条件查看文章评论
     * @param pageHelper 分页条件
     * @return 返回信息
     */
    ResponseEntity getArticleReviews(PageHelper<SearchCondition> pageHelper);

    /**
     * 根据筛选条件查看最新资讯
     * @return 返回信息
     */
    ResponseEntity getLastedNews();

    /**
     * 根据筛选条件查看最热资讯
     * @return 返回信息
     */
    ResponseEntity getHotNews();
}
