package com.chen.mycardsystembackstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Project : MyCardSystem - SysScheduleTriggerLog
 * Powered by GeorgeChen On 2023-02-01 15:11:18
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
public class SysScheduleTriggerLog {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private int jobId;
    private String jobName;
    private int status;
    private Timestamp time;
    private String recipient;
    private String text;
}
