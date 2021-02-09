package com.qiaoyansong.interceptor;

import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.OnLineUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String userName = (String)request.getSession().getAttribute("userName");
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
                String sessionId = request.getSession().getId();
                logger.info(curSessionId);
                logger.warn(sessionId);
                if(!curSessionId.equals(sessionId)){
                    // curSessionId中记录的永远是最后登录的用户
                    // 如果发现当前不等，那么不需要再保留当前的session
                    request.getSession().invalidate();
                    logger.warn("需要退出");
                    return false;
                }else{
                    // 如果相等 就延长session生命周期
                    request.getSession().setMaxInactiveInterval(30 * 60);
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
