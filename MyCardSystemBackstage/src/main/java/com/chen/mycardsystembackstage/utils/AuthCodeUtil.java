package com.chen.mycardsystembackstage.utils;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Project : MyCardSystem - AuthCodeUtil
 * Powered by GeorgeChen On 2023-01-18 14:21:36
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 生成验证码工具类
 **/
@Component
public class AuthCodeUtil {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 生成验证码存储到redis
     * @param id 登录用户的id
     * @param time 过期时间(秒钟)
     * @return 随机数的验证码
     */
    public String create(String id,int time){
        // todo 生成四位数字的验证码，后续可更改为更复杂的
        String code = String.valueOf(RandomUtil.randomInt(1000,9999));

        //存储到redis
        ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
        vo.set("CODE:"+id,code);
        redisTemplate.expire("CODE:"+id,time, TimeUnit.SECONDS);
        System.out.println("保存"+id+"CODE成功");
        return code;
    }
}
