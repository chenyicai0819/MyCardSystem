package com.chen.mycardsystembackstage.config;


import com.chen.mycardsystembackstage.utils.QuartUtils.PutChats;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Date;

/**
 * @author George
 * @project lifaApi
 * @package com.chen.lifaapi.utils
 * @date 2022/4/14 0:34
 * @since 1.0
 */
@Configuration
@Slf4j
public class QuartConfig {
    /*
定时任务配置包括  ：
    jobDetail 任务实例
    定时任务执行的具体内容等 -相关业务代码
    trigger 触发器
    设置相关的运行周期等    -绑定任务实例
    scheduler 调度器
    决定哪些定时任务会执行  -绑定触发器
*/
    // 配置Scheduler
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger firstTrigger,Trigger secondTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);

        // 注册触发器
        bean.setTriggers(firstTrigger,secondTrigger);

        return bean;
    }
    /**
     * 定时任务 1
     */

    // 配置定时任务1的任务实例
    @Bean(name = "firstJobDetail")
    public MethodInvokingJobDetailFactoryBean firstJobDetail(PutChats putChats) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(putChats);
        // 需要执行的方法
        jobDetail.setTargetMethod("updataPass");
        return jobDetail;
    }


    // 配置触发器1
    @Bean(name = "firstTrigger")
    public CronTriggerFactoryBean firstTrigger(JobDetail firstJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(firstJobDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date());
//        // 每5秒执行一次
//        trigger.setRepeatInterval(5000);
        //构建CronTrigger（触发器）实例,每天九点-十八点执行每隔20分钟执行一次
//         .withSchedule(CronScheduleBuilder.cronSchedule("0 0/20 9-18 * * ?"))
//         每天16：00执行
        trigger.setCronExpression("0 0 10 1 * ? *");
        return trigger;
    }


    @Bean(name = "secondJobDetail")
    public MethodInvokingJobDetailFactoryBean secondJobDetail(PutChats putChats) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(putChats);
        // 需要执行的方法
        jobDetail.setTargetMethod("chatsForOne");
        return jobDetail;
    }
    @Bean(name = "secondTrigger")
    public CronTriggerFactoryBean secondTrigger(JobDetail secondJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(secondJobDetail);

        trigger.setStartDelay(0);

        trigger.setStartTime(new Date());
        trigger.setCronExpression("0 0 10 1 * ? *");
        return trigger;
    }


}
