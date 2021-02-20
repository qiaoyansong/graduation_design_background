package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.Activity;
import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/16 21:13
 * description：
 */
@Mapper
public interface ActivityMapper {
    /**
     * 上传活动
     * @param activity 活动
     * @return
     */
    Integer uploadActivity(Activity activity);

    /**
     * 根据查询条件查询资讯
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<Activity> getActivity(PageHelper pageHelper);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

    /**
     * 根据ID修改具体活动
     * @param activity 活动信息
     * @return
     */
    Integer updateActivityByID(com.qiaoyansong.entity.front.Activity activity);

    /**
     * 根据ID删除具体活动
     * @param id 查询条件
     * @return
     */
    Integer deleteActivityByID(String id);
}
