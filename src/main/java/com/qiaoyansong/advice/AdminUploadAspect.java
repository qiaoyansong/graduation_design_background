package com.qiaoyansong.advice;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.background.UserType;
import com.qiaoyansong.service.impl.UserServiceImpl;
import com.qiaoyansong.util.RequestContextHolderUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/17 17:11
 * description：将管理员上传功能的公共部分抽离出来
 */
@Aspect
@Component
public class AdminUploadAspect {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Pointcut("execution(public * com.qiaoyansong.service.impl.NewsServiceImpl.admin*(..)) " +
            "|| execution(public * com.qiaoyansong.service.impl.ActivityServiceImpl.admin*(..)) " +
            "|| execution(public * com.qiaoyansong.service.impl.AuctionServiceImpl.admin*(..)) " +
            "|| execution(public * com.qiaoyansong.service.impl.CommodityServiceImpl.admin*(..))" +
            "|| execution(public * com.qiaoyansong.service.impl.UserServiceImpl.adminDeleteUserByID(..))" +
            "|| execution(public * com.qiaoyansong.service.impl.UserServiceImpl.adminSelectUsers(..))" +
            "|| execution(public * com.qiaoyansong.controller.AdminController.agreeUserNews(..))" +
            "|| execution(public * com.qiaoyansong.controller.AdminController.refuseUserNews(..))" +
            "|| execution(public * com.qiaoyansong.controller.SeekHelpController.agreeSeekHelp(..))" +
            "|| execution(public * com.qiaoyansong.controller.SeekHelpController.refuseSeekHelp(..))" +
            "|| execution(public * com.qiaoyansong.service.impl.UserServiceImpl.modifyUserInfo(..))" +
            "|| execution(public * com.qiaoyansong.service.impl.ActivityServiceImpl.getParticipantByActivityId(..))" +
            "|| execution(public * com.qiaoyansong.service.impl.ActivityServiceImpl.updateActivityProcess(..))")
    public void pointCut(){

    }

    @Around("pointCut()")
    public ResponseEntity adminUploadAspect(ProceedingJoinPoint proceedingJoinPoint){
        // 方法名
        String methodName = proceedingJoinPoint.getSignature().toString();
        log.info("进入" + methodName);
        log.info("开始验证当前session是否有用户登陆信息");
        ResponseEntity responseEntity = new ResponseEntity();
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            log.warn("当前session没有登陆过，直接退出");
            responseEntity.setBody(StatusCode.USER_IS_NOT_LOGGED_IN.getReason());
            responseEntity.setCode(StatusCode.USER_IS_NOT_LOGGED_IN.getCode());
        } else {
            log.info("当前session已经登录");
            log.info("判断是否有相关权限");
            User user = this.userMapper.getUserInfo(userName);
            if (user.getType() == UserType.GENERAL_USER) {
                log.warn("权限不足，直接退出");
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
            } else {
                try {
                    return (ResponseEntity) proceedingJoinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return responseEntity;
    }
}
