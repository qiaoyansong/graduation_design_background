package com.qiaoyansong.advice;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
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
 * @date ：Created in 2021/3/12 20:44
 * description：用户上传切面
 */
@Component
@Aspect
public class UserUploadAspect {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Pointcut("execution(public * com.qiaoyansong.service.impl.NewsServiceImpl.uploadNews(..))" +
            "|| execution(public * com.qiaoyansong.service.impl.NewsServiceImpl.userSelectNews(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public ResponseEntity userUploadAspect(ProceedingJoinPoint proceedingJoinPoint) {
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
            try {
                return (ResponseEntity) proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return responseEntity;
    }
}
