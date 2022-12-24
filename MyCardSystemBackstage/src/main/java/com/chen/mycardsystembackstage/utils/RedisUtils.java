package com.chen.mycardsystembackstage.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author George
 * @project Cyc-Sequence
 * @package com.chen.sequence.utils
 * @date 2022/10/10 20:53
 * @since 1.0
 * Redis 工具类
 */
@Component
public class RedisUtils {

    public static JedisPool jedisPool;

    static {
        try {
            Properties properties = new Properties();
            // 读取 redis.properties 中的内容
            InputStream in = CommonUtils.readResourceFile("redis.properties");
            // 将所读取的内容加载到配置中
            properties.load(in);

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
            jedisPoolConfig.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
            jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(properties.getProperty("maxWaitMillis")));
            jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("testOnBorrow")));
            jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("testOnReturn")));
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Integer.parseInt(properties.getProperty("timeBetweenEvictionRunsMillis")));
            jedisPoolConfig.setMinEvictableIdleTimeMillis(Integer.parseInt(properties.getProperty("minEvictableIdleTimeMillis")));
            jedisPoolConfig.setNumTestsPerEvictionRun(Integer.parseInt(properties.getProperty("numTestsPerEvictionRun")));
            jedisPool = new JedisPool(jedisPoolConfig,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 调用 Redis 连接池
     * @return Redis 的链接
     */
    public static Jedis getConnectJedis(){
        return jedisPool.getResource();
    }
}
