package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.News;
import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/16 16:42
 * description：
 */
@Mapper
public interface NewsMapper {
    /**
     * 管理员上传文章
     * @param news 文章
     * @return
     */
    Integer uploadNews(News news);

    /**
     * 用户上传文章
     * @param news 文章
     * @return
     */
    Integer userUploadNews(News news);

    /**
     * 检查文章标题是否存在
     * @param title 文章标题
     * @return
     */
    Integer checkNewsTitleIsExists(String title);

    /**
     * 根据查询条件查询资讯
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<News> getNews(PageHelper<SearchCondition> pageHelper);

    /**
     * 根据查询条件查询用户投稿
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<News> getUserNews(PageHelper<SearchCondition> pageHelper);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

    /**
     * 根据查询条件查询用户投稿数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getUserTotalSize(SearchCondition condition);

    /**
     * 根据ID修改具体资讯
     * @param news 资讯信息
     * @return
     */
    Integer updateNewsByID(com.qiaoyansong.entity.front.News news);

    /**
     * 根据ID删除具体资讯
     * @param id 查询条件
     * @return
     */
    Integer deleteNewsByID(String id);

    /**
     * 获取资讯详情
     * @param id 查询条件
     * @return
     */
    News getNewInfoById(String id);

    /**
     * 同意用户投稿
     * @param id 资讯ID
     * @return
     */
    Integer agreeUserNews(String id);

    /**
     * 拒绝用户投稿
     * @param id 资讯ID
     * @return
     */
    Integer refuseUserNews(String id);

    /**
     * 获取首页的国内资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageGuoNei();

    /**
     * 获取首页的政府资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageZhengFu();

    /**
     * 获取首页的学校资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageXueXiao();

    /**
     * 获取首页的企业资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageQiYe();

    /**
     * 获取首页的益人资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageYiRen();

    /**
     * 获取首页的好人资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageHaoRen();

    /**
     * 获取首页的好事资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepageHaoShi();

    /**
     * 获取首页的评选资讯展示
     * @return 资讯信息
     */
    List<News> getListOfHomepagePingXuan();
}
