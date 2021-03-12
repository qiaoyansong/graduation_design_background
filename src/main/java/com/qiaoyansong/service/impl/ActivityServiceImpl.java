package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.ActivityMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/12 19:45
 * description：
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    private static final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Override
    public ResponseEntity adminUploadActivity(Activity activity) {
        log.info("进入ActivityServiceImpl的adminUploadActivity方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始上传活动");
        if (this.activityMapper.uploadActivity(activity) != 1) {
            log.warn("上传活动失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("上传活动成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminDeleteActivityByID(String id) {
        log.info("进入ActivityServiceImpl的adminDeleteActivityByID方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始删除活动");
        if (this.activityMapper.deleteActivityByID(id) != 1) {
            log.warn("删除活动失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("删除活动成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminUpdateActivity(com.qiaoyansong.entity.front.Activity activity) {
        log.info("进入ActivityServiceImpl的updateActivity方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始修改活动");
        if (this.activityMapper.updateActivityByID(activity) != 1) {
            log.warn("修改活动失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("修改活动成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity adminSelectActivity(PageHelper<SearchCondition> pageHelper) {
        log.info("进入ActivityServiceImpl的adminSelectActivity方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.activityMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Activity> news = this.activityMapper.getActivity(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }
}