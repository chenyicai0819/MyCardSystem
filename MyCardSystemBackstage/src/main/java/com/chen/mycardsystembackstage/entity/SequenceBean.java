package com.chen.mycardsystembackstage.entity;

import lombok.Data;

/**
 * @author George
 * @project Cyc-Sequence
 * @package com.chen.sequence.bean
 * @date 2022/10/11 10:02
 * @since 1.0
 */
@Data
public class SequenceBean {

    /**
     * 选择生成序列号的引擎
     * 目前支持：Redis
     */
    private String sequenceEngine;
    /**
     * 选择生成序列号的方法
     * 目前支持：TIME、ZERO、CUSTOM
     */
    private String sequenceType;

    /**
     * 序列号的名称
     */
    private String keyName;

    /**
     * 序列号的前缀(ID)
     */
    private String keyPrefix;

    /**
     * 过期时间（单位：秒）
     */
    private long delTime;

    /**
     * 序列的长度
     */
    private int seLength;

    /**
     * 长度不足时填充的值（默认为 0）
     */
    private int fillText;

    /**
     * 序列号开始的值（设置默认为 0）
     */
    private Long beginText;

    /**
     * 序列号结束的值（设置默认为 空）
     */
    private Long endText;

    /**
     * 序列号自增的大小（设置默认为 1）
     */
    private int intervalNum;

    /**
     * 用户自定义的序列号模板类型
     */
    private String template;
}
