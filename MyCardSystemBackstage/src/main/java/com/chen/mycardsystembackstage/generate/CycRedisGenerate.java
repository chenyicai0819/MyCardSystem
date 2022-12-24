package com.chen.mycardsystembackstage.generate;



import com.chen.mycardsystembackstage.entity.SequenceBean;
import com.chen.mycardsystembackstage.utils.RedisUtils;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author George
 * @project Cyc-Sequence
 * @package com.chen.sequence.generate
 * @date 2022/10/10 21:13
 * @since 1.0
 * 通过 Redis 生成全局序列号
 */
public class CycRedisGenerate {
    private static int COUNT_BITS = 32;

    /**
     * 初始时间戳： 2022-01-01 08:00:00
     */
    private static final long BEGIN_TIMESTAMP = 1640995200L;

    /**
     * 设置过期时间以及填充空位
     * @param jedis redis 链接池
     * @param keyPrefix 序列号的前缀
     * @param delTime 过期时间（单位：秒）
     * @param seLength 序列的长度
     * @param fillText 长度不足时填充的值
     * @param count 插入到 redis 中的值，也就是未填充的序号
     * @return 已经填充好的序列号
     */
    private static String setDelTimeAndFill(Jedis jedis, String keyPrefix, long delTime, int seLength, int fillText, Long count){
        if (delTime > -1){
            jedis.expire(keyPrefix, delTime);
        }
        // 开始计算当前序号的长度
        int nowLength = String.valueOf(count).length();
        // 得到需要填充的长度
        int fillLength = seLength - nowLength;

        // 开始填充
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fillLength; i++) {
            sb.append(fillText);
        }
        sb.append(count);
        jedis.close();
        return sb.toString();
    }

    /**
     * 根据当前时间戳生成对应的 ID
     * @param keyPrefix 序列号的前缀
     * @param delTime 过期时间（单位：秒）
     * @return 生成的 ID
     */
    public Long redisSequenceFromTime(String keyPrefix,long delTime){

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取当前时间秒数
        long nowSecond  = now.toEpochSecond(ZoneOffset.UTC);
        // 当前时间减去初始时间的值
        long time = nowSecond - BEGIN_TIMESTAMP;
        String format = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        // 链接 Redis
        Jedis jedis = RedisUtils.getConnectJedis();
        // Jedis append 命令将 key 中储存的数字加上指定的增量值。
        // 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 append 命令。
        Long count = jedis.append(keyPrefix, String.valueOf(time));
        //设置过期时间
        if (delTime > -1){
            jedis.expire(keyPrefix, delTime);
        }
        // 关闭连接
        jedis.close();
        return time << COUNT_BITS | count;
    }

    /**
     * 从 0 开始生成全局序列号
     * @param keyPrefix 序列号的前缀
     * @param delTime 过期时间（单位：秒）
     * @param seLength 序列的长度
     * @param fillText 长度不足时填充的值
     * @return 生成的 ID
     */
    public String redisSequenceFromZero(String keyPrefix, long delTime, int seLength, int fillText){
        // 链接 Redis
        Jedis jedis = RedisUtils.getConnectJedis();
        // Redis 中序号进行自增,返回自增后的值
        Long count = jedis.incr(keyPrefix);
        //设置过期时间以及填充空位
        return setDelTimeAndFill(jedis,keyPrefix,delTime,seLength,fillText,count);
    }

    /**
     * 自定义递增生成序列号
     * @param keyPrefix 序列号的前缀
     * @param delTime 过期时间（单位：秒）
     * @param seLength 序列的长度
     * @param fillText 长度不足时填充的值
     * @param beginText 序列号开始的值（设置默认为 0）
     * @param endText 序列号结束的值（设置默认为 空）
     * @param intervalNum 序列号自增的大小（设置默认为 1）
     * @return
     */
    public String redisSequenceFromCustom(String keyPrefix, long delTime, int seLength, int fillText, Long beginText, Long endText, int intervalNum){
        // 链接 Redis
        Jedis jedis = RedisUtils.getConnectJedis();
        // 如果当前 key 不存在时，创建一个 key 并赋值
        Long count = 0L;
        if (jedis.exists(keyPrefix)){
            // Redis 中序号进行自增,返回自增后的值
            if (intervalNum > 0){
                count = jedis.incrBy(keyPrefix, intervalNum);
            }else {
                count = jedis.incr(keyPrefix);
            }
            // 如果序号结束的值存在时，则运行这个语句
            if (endText>0){
                // 如果当前值等于最大值时，返回 1 ，重新开始编号
                if (count.equals(endText+1L)){
                    jedis.del(keyPrefix);
                    jedis.setnx(keyPrefix, String.valueOf(beginText));
                    count = Long.valueOf(beginText);
                }
            }
        }else {
            jedis.setnx(keyPrefix, String.valueOf(beginText));
            count = Long.valueOf(beginText);
        }

        //设置过期时间以及填充空位
        return setDelTimeAndFill(jedis,keyPrefix,delTime,seLength,fillText,count);
    }

    /**
     * 调用接口获取 Redis 生成的全局序列号
     * @param sequenceBean 序列号接口工具 Bean 实体类
     * @return 生成的序列号 ID
     */
    public String getRedisSequence(SequenceBean sequenceBean){
        if ("TIME".equals(sequenceBean.getSequenceType())){
            return String.valueOf(redisSequenceFromTime(sequenceBean.getKeyPrefix(), sequenceBean.getDelTime()));
        }else if ("ZERO".equals(sequenceBean.getSequenceType())){
            return redisSequenceFromZero(sequenceBean.getKeyPrefix(),sequenceBean.getDelTime(),sequenceBean.getSeLength(),sequenceBean.getFillText());
        }else if ("CUSTOM".equals(sequenceBean.getSequenceType())){
            return redisSequenceFromCustom(sequenceBean.getKeyPrefix(),sequenceBean.getDelTime(),sequenceBean.getSeLength(),sequenceBean.getFillText(),sequenceBean.getBeginText(),sequenceBean.getEndText(),sequenceBean.getIntervalNum());
        }
        return null;
    }

}
