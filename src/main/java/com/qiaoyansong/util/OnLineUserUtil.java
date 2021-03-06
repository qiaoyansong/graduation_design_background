package com.qiaoyansong.util;

import com.qiaoyansong.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 18:54
 * description：获取在线用户的工具
 */
public class OnLineUserUtil {
    private static volatile OnLineUserUtil instance;
    private final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = ThreadPoolExecutorUtil.getScheduledExecutorService();
    private Jedis redis;
    private final Logger logger = LoggerFactory.getLogger(OnLineUserUtil.class);

    private OnLineUserUtil(){

    }

    public static OnLineUserUtil getInstance() {
        if (instance == null) {
            synchronized (OnLineUserUtil.class) {
                if (instance == null) {
                    instance = new OnLineUserUtil();
                    return instance;
                }
            }
        }
        return instance;
    }

    /**
     * 延迟10s从redis数据库中获取当前用户的sessionID信息 填充到数据库中
     */
    public void addSessionInfoFromRedisToDB(String userName, UserMapper userMapper) {
        try {
            redis = JedisPoolUtil.getInstance().getResource();
            logger.info("检验redis联通性" + redis.ping());
            SCHEDULED_EXECUTOR_SERVICE.schedule(() -> {
                logger.info("进入定时器任务");
                userMapper.insertSessionInfo(userName, redis.get(userName));
            }, 10, TimeUnit.SECONDS);
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 延迟10s从redis数据库中获取当前用户的sessionID信息 填充到数据库中
     */
    public void removeSessionInfoFromDB(String userName, UserMapper userMapper) {
        SCHEDULED_EXECUTOR_SERVICE.schedule(() -> {
            logger.info("进入定时器任务");
            userMapper.removeSessionInfo(userName);
        }, 10, TimeUnit.SECONDS);
    }
}
