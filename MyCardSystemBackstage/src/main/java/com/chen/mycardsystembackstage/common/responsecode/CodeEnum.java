package com.chen.mycardsystembackstage.common.responsecode;

/**
 * @author ：mosence
 * @date ：2019/12/30
 */
public interface CodeEnum {
    /**
     * 消息代码
     * 注意不要设计出越界CODE(long类型)
     * [-9223372036854775808,9223372036854775807]
     * @return 消息代码
     */
    long getCode();

    /**
     * 默认消息内容
     * @return 默认消息内容
     */
    String getDefaultMessage();
}
