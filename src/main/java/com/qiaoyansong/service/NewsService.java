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
     * 用户获取资讯信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity userSelectNews(PageHelper<SearchCondition> pageHelper);
}
