package com.qiaoyansong.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/5 19:52
 * description：
 */

public class JedisPoolUtil {
    private static volatile JedisPool pool = null;
    private JedisPoolUtil() {

    }

    public static JedisPool getInstance() {
        if (pool == null) {
            synchronized (JedisPoolUtil.class) {
                if (pool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    // config的配置文件就不在配置了
                    pool = new JedisPool(config,"192.168.40.128",6379);
                }
            }
        }
        return pool;
    }
}
