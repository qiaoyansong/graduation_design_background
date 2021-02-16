package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.Activity;
import org.apache.ibatis.annotations.Mapper;

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
}
