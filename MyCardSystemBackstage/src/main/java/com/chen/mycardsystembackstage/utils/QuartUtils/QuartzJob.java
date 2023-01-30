package com.chen.mycardsystembackstage.utils.QuartUtils;

import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Project : MyCardSystem - QuartzJob
 * Powered by GeorgeChen On 2023-01-30 16:37:02
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public abstract class QuartzJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        //获取JobDetail的内容
        JobKey jobKey = context.getJobDetail().getKey();
        //获取Trigger内容
        TriggerKey triggerKey = context.getTrigger().getKey();
        //从Trigger对象中获取jobDataMap数据
        JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
        // 根据反射去执行对应的任务
        SysScheduleTrigger sysJob = new SysScheduleTrigger();
        // 注意该处是之前将我们自己定义的任务信息表数据 追加到了jobDetail中  在创建JobDetail后追加 jobDetail.getJobDataMap().put("mengybQuratz", sysScheduleTrigger);
        Object mengybQuratz = context.getMergedJobDataMap().get("mengybQuratz");
        BeanUtils.copyProperties(mengybQuratz, sysJob);
        try
        {
            if (sysJob != null)
            {
                // 执行自己定义的类
                doExecute(context, sysJob);
            }
        }
        catch (Exception e)
        {
            logger.error("任务执行异常  - ：", e);
        }
    }



    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param sysJob 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysScheduleTrigger sysJob) throws Exception;
}
