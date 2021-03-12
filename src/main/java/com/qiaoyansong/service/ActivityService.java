package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.Activity;
import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;

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
}
