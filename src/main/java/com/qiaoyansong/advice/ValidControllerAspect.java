package com.qiaoyansong.advice;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/19 18:17
 * description：对于需要验证参数的controller的增强
 */
@Component
@Aspect
public class ValidControllerAspect {
    private static final Logger log = LoggerFactory.getLogger(ValidControllerAspect.class);

    @Pointcut("execution(public * com.qiaoyansong.controller.AdminController.upload*(..)) || " +
            "execution(public * com.qiaoyansong.controller.AdminController.get*(..)) || " +
            "execution(public * com.qiaoyansong.controller.AdminController.update*(..)) || " +
            "execution(public * com.qiaoyansong.controller.LoginController.login(..)) || " +
            "execution(public * com.qiaoyansong.controller.LoginController.getAdminVerificationCode(..)) || " +
            "execution(public * com.qiaoyansong.controller.LoginController.adminLogin(..)) || " +
            "execution(public * com.qiaoyansong.controller.RegisterController.register(..)) || " +
            "execution(public * com.qiaoyansong.controller.ModifyPasswordController.*(..)) || " +
            "execution(public * com.qiaoyansong.controller.UploadController.uploadNews(..)) || " +
            "execution(public * com.qiaoyansong.controller.NewsController.get*(..)) || " +
            "execution(public * com.qiaoyansong.controller.UploadController.uploadUserImg(..)) || " +
            "execution(public * com.qiaoyansong.controller.LocationController.uploadLocation(..)) || " +
            "execution(public * com.qiaoyansong.controller.LocationController.updateLocationById(..)) ||" +
            "execution(public * com.qiaoyansong.controller.SeekHelpController.seekHelp*(..)) || " +
            "execution(public * com.qiaoyansong.controller.AdminController.modifyUserInfo(..)) ||" +
            "execution(public * com.qiaoyansong.controller.NewsController.addArticleReview(..)) || " +
            "execution(public * com.qiaoyansong.controller.CommodityController.get*(..)) || " +
            "execution(public * com.qiaoyansong.controller.ActivityController.get*(..)) ||" +
            "execution(public * com.qiaoyansong.controller.UserController.*(..)) || " +
            "execution(public * com.qiaoyansong.controller.AuctionController.get*(..)) ||" +
            "execution(public * com.qiaoyansong.controller.SeekHelpListController.get*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public ResponseEntity validControllerAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        // 获取到bindingResult
        String methodName = proceedingJoinPoint.getSignature().toString();
        Object target = proceedingJoinPoint.getTarget();
        String className = target.getClass().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        BindingResult bindingResult = (BindingResult) args[1];
        log.info("进入" + className + "." + methodName);
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            log.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            try {
                return (ResponseEntity) proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
        responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        return responseEntity;
    }
}