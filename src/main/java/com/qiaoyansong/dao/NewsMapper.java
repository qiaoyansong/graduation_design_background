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
     * 上传文章
     * @param news 文章
     * @return
     */
    Integer uploadNews(News news);

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
    List<News> getNews(PageHelper pageHelper);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

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
}
