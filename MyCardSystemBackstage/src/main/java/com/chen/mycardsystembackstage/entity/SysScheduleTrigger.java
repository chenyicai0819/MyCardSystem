package com.chen.mycardsystembackstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Project : MyCardSystem - SysScheduleTrigger
 * Powered by GeorgeChen On 2023-01-30 16:05:25
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysScheduleTrigger implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String jobName;

    private String jobGroup;

    private String jobClass;
    private String jobMethod;
    private String jobParams;

    private String jobDesc;

    private String cron;

    private String triggerName;

    private String triggerGroup;

    private String triggerDesc;
    private Integer concurrent;
    private Integer status;

    private Integer account;

    private LocalDateTime createTime;


}
