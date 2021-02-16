package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.News;
import org.apache.ibatis.annotations.Mapper;

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

}
