package com.chen.mycardsystembackstage.utils.QuartUtils;

import cn.hutool.core.date.DateUtil;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.entity.SysScheduleTriggerLog;
import com.chen.mycardsystembackstage.mapper.SysScheduleTriggerLogMapper;
import com.chen.mycardsystembackstage.mapper.SysScheduleTriggerMapper;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Project : MyCardSystem - PutChatsLogsUtils
 * Powered by GeorgeChen On 2023-02-01 15:10:19
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 *
 * 执行定时任务时添加日志
 **/
@Component
public class PutChatsLogsUtils {

    @Autowired
    private SysScheduleTriggerMapper sysScheduleTriggerMapper;
    @Autowired
    private SysScheduleTriggerLogMapper sysScheduleTriggerLogMapper;

    /**
     * 添加定时任务发送的日志
     * @param jobId 任务id
     * @param jobName 任务名称
     * @param status 发送状态
     * @param recipient 接收者
     * @param text 发送内容
     */
    public void addTasksLogsUtils(int jobId,String jobName,int status,String recipient,String text){
        SysScheduleTriggerLog log = new SysScheduleTriggerLog();
        log.setJobId(jobId);
        log.setJobName(jobName);
        log.setStatus(status);
        log.setRecipient(recipient);
        log.setText(text);

        String time = DateUtil.today()+" "+DateUtil.formatTime(DateUtil.date());
        log.setTime(Timestamp.valueOf(time));

        sysScheduleTriggerLogMapper.insert(log);
    }

    /**
     * 通过方法名获取数据库里的id等信息'
     * @param method 方法名
     * @return 定时任务信息
     */
    public SysScheduleTrigger getTrigge(String method){
        SysScheduleTrigger sysScheduleTrigger=sysScheduleTriggerMapper.getAllByMethod(method);
        // System.out.println(sysScheduleTrigger.getId());
        return sysScheduleTrigger;
    }
}
