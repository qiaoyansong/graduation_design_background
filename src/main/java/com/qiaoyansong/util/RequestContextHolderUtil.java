package com.qiaoyansong.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 19:05
 * description：随时获取request对象工具类
 */
public class RequestContextHolderUtil {
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    private RequestContextHolderUtil(){

    }
}
