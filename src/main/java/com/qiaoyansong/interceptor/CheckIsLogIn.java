package com.qiaoyansong.interceptor;

import com.qiaoyansong.entity.background.StatusCode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 20:38
 * description：检查是否登陆
 */
public class CheckIsLogIn implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(CheckIsLogIn.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入CheckIsLogIn的preHandle");
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("userName");
        logger.info("sessionId为" + session.getId());
        if(userName == null){
            // 当前会话还没有登陆过
            logger.info("当前会话没有登陆过");
            logger.warn("需要返回到登录界面");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject o = new JSONObject();
            o.put("code", StatusCode.USER_IS_NOT_LOGGED_IN.getCode());
            o.put("body",StatusCode.USER_IS_NOT_LOGGED_IN.getReason());
            writer.write(o.toString());
            return false;
        }else{
            logger.info("当前会话已登陆");
            return true;
        }
    }
}
