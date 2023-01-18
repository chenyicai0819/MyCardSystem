package com.chen.mycardsystembackstage.generate;

import com.chen.mycardsystembackstage.entity.SequenceBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author George
 * @project Cyc-Sequence
 * @package com.chen.sequence.generate
 * @date 2022/10/17 19:58
 * @since 1.0
 * UUID 生成序列
 */
public class CycUuidGenerate {

    /**
     * 获取 UUID
     * @param sequenceBean 序列号实体类
     * @return 返回 UUID 的值
     */
    public String getUuid(SequenceBean sequenceBean) {
        if("DATE".equals(sequenceBean.getSequenceType())){
            return uuidDate();
        }else if ("CRE".equals(sequenceBean.getSequenceType())){
            return uuidCreId();
        }else {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    /**
     * 获取 UUID+时间
     *
     * @return 返回 UUID 的值
     */
    public static String uuidDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);
        return UUID.randomUUID().toString().replace("-", "") + dateString;
    }

    /**
     * 获取 时间戳+随机数+序列号
     *
     * @return 返回 UUID 的值
     */
    public static String uuidCreId() {
        byte[] lock = new byte[0];
        long l = 1000000;
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * l);
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);
        return UUID.randomUUID().toString().replace("-", "")+dateString + String.valueOf(r).substring(1);
    }
}
