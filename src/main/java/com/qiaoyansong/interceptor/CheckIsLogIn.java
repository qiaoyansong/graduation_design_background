package com.qiaoyansong.interceptor;

import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.OnLineUserUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 20:38
 * description：检查是否已经登陆
 */
public class CheckIsLogIn implements HandlerInterceptor {
    private OnLineUserUtil onLineUserUtil = OnLineUserUtil.getInstance();
    private Jedis redis;
    private Logger logger = LoggerFactory.getLogger(CheckIsLogIn.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 60);
        String userName = (String)session.getAttribute("userName");
        logger.info("sessionId为" + session.getId());
        if(userName == null){
            // 当前会话还没有登陆过
            logger.info("当前会话没有登陆过");
            return true;
        }else{
            // 判断当前会话的sessionID与redis存储的是否一致
            try {
                redis = JedisPoolUtil.getInstance().getResource();
                logger.info("检测redis连通性" + redis.ping());
                String curSessionId = redis.get(userName);
                String sessionId = session.getId();
                if(!curSessionId.equals(sessionId)){
                    // curSessionId中记录的永远是最后登录的用户
                    // 如果发现当前不等，那么不需要再保留当前的session
                    session.invalidate();
                    logger.warn("需要退出");
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    JSONObject o = new JSONObject();
                    o.put("code", StatusCode.LOGIN_ELSEWHERE.getCode());
                    o.put("body",StatusCode.LOGIN_ELSEWHERE.getReason());
                    writer.write(o.toString());
                    return false;
                }else{
                    logger.info("延长session生命周期");
                    // 如果相等 就延长session生命周期
                    session.setMaxInactiveInterval(30 * 60);
                    return true;
                }
            }finally {
                if(redis != null){
                    redis.close();
                }
            }
        }
    }
}
