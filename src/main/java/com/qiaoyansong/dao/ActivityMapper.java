package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.*;
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

    /**
     * 获取活动详情
     * @param id 活动ID
     * @return
     */
    Activity getActivityInfoById(String id);

    /**
     * 添加用户活动信息
     * @param userActivity 用户-活动信息
     * @return
     */
    Integer signUp(UserActivity userActivity);

    /**
     * 修改用户-活动信息
     * @param userActivity 用户-活动信息
     * @return
     */
    Integer updateActivityProcess(UserActivity userActivity);

    /**
     * 根据查询条件查询活动参与者总条数
     * @param condition 查询条件
     * @return
     */
    Integer getParticipantTotalSize(SearchCondition condition);

    /**
     * 根据查询条件查询当前用户所参加活动的总条数
     * @param condition 查询条件
     * @return
     */
    Integer getSignUpActivityTotalSize(SearchCondition condition);

    /**
     * 根据查询条件当前用户参加的所有活动
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<UserActivity> getSignUpActivity(PageHelper pageHelper);

    /**
     * 根据查询条件查询参加该活动的所有用户
     * @param pageHelper 查询 + 分页条件
     * @return
     */
    List<UserActivity> getParticipant(PageHelper pageHelper);

    /**
     * 获取用户活动信息
     * @param userActivity 用户活动信息
     * @return
     */
    UserActivity getUserActivityInfo(UserActivity userActivity);

    /**
     * 获取参与该活动应该获取的点数
     * @param activityId 活动ID
     * @return
     */
    Integer getPointByActivityId(String activityId);
}
