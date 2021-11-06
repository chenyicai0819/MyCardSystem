package com.chen.mycardsystembackstage.cache;


import com.chen.mycardsystembackstage.utils.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author George
 * @project MySQLRedisDemo
 * @package com.chen.mysqlredisdemo.cache
 * @date 2021/9/28 18:36
 * @since 1.0
 */
public class MybatisRedisCache implements Cache {
    private Object key;
    private String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private RedisTemplate<Object, Object> redisTemplate;

    private final String COMMON_CACHE_KEY = "MYBATIS:";

    /**
     * 默认缓存对象的缓存时间为 1 分钟
     **/
    private static final long EXPRIRE_TIME_IN_MINUT = 30;

    public MybatisRedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("缓存对象id是不能为空的");
        }
        this.id = id;
    }

    private RedisTemplate<Object, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            //使用redisTemplate得到值操作对象
            ValueOperations operation = redisTemplate.opsForValue();
            //使用值操作对象operation设置缓存对象
            operation.set(getKey(key), value, EXPRIRE_TIME_IN_MINUT, TimeUnit.MINUTES);
//            System.out.println("缓存对象保存成功");
        } catch (Throwable t) {
            System.err.println("缓存对象保存失败" + t);
        }
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    @Override
    public Object getObject(Object key) {
        try {
            setKey(key);
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations operations = redisTemplate.opsForValue();
            Object result = operations.get(getKey(key));
//            System.out.println("获取缓存对象成功");
            return result;
        } catch (Throwable t) {
            System.err.println("缓存对象获取失败" + t);
            return null;
        }
    }

    @Override
    public Object removeObject(Object key) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.delete(getKey(key));
//            System.out.println("删除缓存对象成功！"+key);
        } catch (Throwable t) {
            System.err.println("删除缓存对象失败！" + t);
        }
        return null;
    }

    @Override
    public void clear() {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            Set<String> keys = redisTemplate.keys(getKeys());
            redisTemplate.delete(keys);
            System.out.println("出现新增、修改、删除操作，清空对应Mapper缓存======>" + keys.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照一定规则标识key
     */
    private String getKey(Object key) {
        String k=COMMON_CACHE_KEY + this.id + ":" + DigestUtils.md5DigestAsHex(String.valueOf(key).getBytes());
        return k;
    }

    /**
     * 所有key
     */
    private String getKeys() {
        return COMMON_CACHE_KEY + this.id + ":*";
    }

    @Override
    public int getSize() {
        Long size = getRedisTemplate().boundHashOps(getId()).size();
        return size == null ? 0 : size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}

