package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.ActivityMapper;
import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Autowired
    private UserMapper userMapper;

    private final byte ACTIVITY_COMPLETE = 100;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;
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

    @Override
    public ResponseEntity activityList(PageHelper<SearchCondition> pageHelper) {
        log.info("进入ActivityServiceImpl的activityList方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.activityMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<Activity> news = this.activityMapper.getActivity(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getActivityInfoById(String id) {
        log.info("进入ActivityServiceImpl的getActivityInfoById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        Activity activity = this.activityMapper.getActivityInfoById(id);
        responseEntity.setBody(activity);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        return responseEntity;
    }

    @Override
    public ResponseEntity signUp(UserActivity userActivity) {
        log.info("进入ActivityServiceImpl的signUp方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始添加用户-活动信息");
        if (this.activityMapper.signUp(userActivity) != 1) {
            log.warn("添加用户-活动信息失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("添加用户-活动信息成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getActivityProcessByUserId(PageHelper<SearchCondition> pageHelper) {
        log.info("进入ActivityServiceImpl的getActivityProcessByUserId方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.activityMapper.getSignUpActivityTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<UserActivity> news = this.activityMapper.getSignUpActivity(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getParticipantByActivityId(PageHelper<SearchCondition> pageHelper) {
        log.info("进入ActivityServiceImpl的adminSelectActivity方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.activityMapper.getParticipantTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<UserActivity> news = this.activityMapper.getParticipant(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity updateActivityProcess(UserActivity userActivity) {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        log.info("进入ActivityServiceImpl的updateActivityProcess方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始修改用户-活动信息");
        if (this.activityMapper.updateActivityProcess(userActivity) != 1) {
            log.warn("修改用户-活动信息失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            if (userActivity.getProgress() == ACTIVITY_COMPLETE) {
                log.info("开始修改用户的积分信息");
                Integer point = this.activityMapper.getPointByActivityId(userActivity.getActivityId());
                Integer integer = this.userMapper.updateUserPointByUserId(userActivity.getUserId(), point+"");
                if (integer == 1) {
                    log.info("修改用户积分信息成功，事务提交");
                    responseEntity.setBody(StatusCode.SUCCESS.getReason());
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                } else {
                    log.warn("修改用户积分信息失败");
                    try {
                        throw new RuntimeException("修改用户积分信息失败");
                    }catch (Exception ex){
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        dataSourceTransactionManager.rollback(transactionStatus);
                        responseEntity.setBody(ex.getMessage());
                        responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                        return responseEntity;
                    }
                }
            } else {
                log.warn("修改用户-活动信息成功");
                responseEntity.setBody(StatusCode.SUCCESS.getReason());
                responseEntity.setCode(StatusCode.SUCCESS.getCode());
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getUserActivityInfo(UserActivity userActivity) {
        log.info("进入ActivityServiceImpl的getUserActivityInfo方法");
        ResponseEntity responseEntity = new ResponseEntity();
        UserActivity news = this.activityMapper.getUserActivityInfo(userActivity);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }
}
