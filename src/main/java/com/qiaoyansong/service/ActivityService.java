package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:31
 * description：
 */
public interface ActivityService {
    /**
     * 管理员上传活动
     * @param activity 活动信息
     * @return 返回信息
     */
    ResponseEntity adminUploadActivity(Activity activity);

    /**
     * 管理员删除活动
     * @param id 活动ID
     * @return 返回信息
     */
    ResponseEntity adminDeleteActivityByID(String id);

    /**
     * 管理员修改活动信息
     * @param activity 活动信息
     * @return 返回信息
     */
    ResponseEntity adminUpdateActivity(com.qiaoyansong.entity.front.Activity activity);

    /**
     * 管理员获取活动信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity adminSelectActivity(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取活动列表
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity activityList(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取活动信息
     * @param id 活动ID
     * @return 返回信息
     */
    ResponseEntity getActivityInfoById(String id);

    /**
     * 用户报名参加活动
     * @param userActivity 活动信息
     * @return 返回信息
     */
    ResponseEntity signUp(UserActivity userActivity);

    /**
     * 根据用户ID获取参与的活动条目
     * @param pageHelper 分页条件
     * @return 返回信息
     */
    ResponseEntity getActivityProcessByUserId(PageHelper<SearchCondition> pageHelper);

    /**
     * 获取参与该活动的用户信息
     * @param pageHelper 分页条件
     * @return 返回信息
     */
    ResponseEntity getParticipantByActivityId(PageHelper<SearchCondition> pageHelper);

    /**
     * 修改用户活动进度信息
     * @param userActivity 用户活动进度
     * @return 返回信息
     */
    ResponseEntity updateActivityProcess(UserActivity userActivity);

    /**
     * 获取用户活动进度信息
     * @param userActivity 用户活动信息
     * @return 返回信息
     */
    ResponseEntity getUserActivityInfo(UserActivity userActivity);
}
