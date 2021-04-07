package com.qiaoyansong.advice;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/5 19:33
 * description：
 */

@ControllerAdvice
public class BusinessExceptionHandler {

    /**
     * 拦截捕捉自定义异常 ConstraintViolationException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Map map = new HashMap();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        map.put("status", StatusCode.VALID_EXCEPTION.getCode());
        map.put("msg", msgList);
        return map;
    }

    /**
     * 处理其它异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity ExceptionHandler(Exception ex) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        responseEntity.setBody(ex.toString());
        return responseEntity;
    }
}
