package com.chen.mycardsystembackstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chen.mycardsystembackstage.common.responsecode.CodeDefault;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.mapper.SysScheduleTriggerMapper;
import com.chen.mycardsystembackstage.service.ISysScheduleTriggerService;
import com.chen.mycardsystembackstage.utils.QuartUtils.QuartzJobExecution;
import com.chen.mycardsystembackstage.utils.ResultBean;
import com.github.pagehelper.PageHelper;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
/**
 * Project : MyCardSystem - SysScheduleTriggerServiceImpl
 * Powered by GeorgeChen On 2023-01-30 16:18:58
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Service
public class SysScheduleTriggerServiceImpl extends ServiceImpl<SysScheduleTriggerMapper, SysScheduleTrigger> implements ISysScheduleTriggerService {

    private static final Logger logger = LoggerFactory.getLogger(SysScheduleTriggerServiceImpl.class);
    @Autowired
    private SysScheduleTriggerMapper sysScheduleTriggerMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 更新任务
     */
    @Override
    public void refreshTrigger() {
        // 查询任务列表
        List<SysScheduleTrigger> sysScheduleTriggerList = sysScheduleTriggerMapper.selectList(null);

        if (sysScheduleTriggerList != null) {
            // 存在任务
            for (SysScheduleTrigger item : sysScheduleTriggerList) {
                //1表示正常启用
                if (item.getStatus() == 1) {
                    //拿出一个任务，启动一个任务
                    try {
                        //1.定义JobDetail，将HelloJob类添加到JobDetail对象中（添加到Job清单）
                        //com.example.job.YRJob
                        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(item.getJobClass()))
                                //任务的名字和组的名字（坑）
                                //任务名字不能相同
                                .withIdentity(item.getJobName(), item.getJobGroup())
                                .build();

                        //2.定义Trigger触发器，使用简单触发器，设置name/group
                        org.quartz.Trigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity(item.getTriggerName(), item.getTriggerGroup())
                                .startNow()
                                .withSchedule(CronScheduleBuilder.cronSchedule(item.getCron()))
                                .build();

                        //3.创建scheduler调度器
                        scheduler.scheduleJob(jobDetail, trigger);
                        //启动调度器
                        scheduler.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 新增定时任务
     *
     * @param sysScheduleTrigger
     * @return
     */
    @Transactional
    @Override
    public ResultBean insertScheduleTrigger(SysScheduleTrigger sysScheduleTrigger) {
        // 首先查看定时任务是否重复
        LambdaQueryWrapper<SysScheduleTrigger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysScheduleTrigger::getJobName, sysScheduleTrigger.getJobName());
        List<SysScheduleTrigger> sysScheduleTriggers = sysScheduleTriggerMapper.selectList(lambdaQueryWrapper);
        if (sysScheduleTriggers == null || sysScheduleTriggers.isEmpty()) {
            // 校验cron表单时  cron 表达式的验证使用正则太麻烦，可以使用 Quartz 自带验证方法
            CronExpression.isValidExpression(sysScheduleTrigger.getCron());
            // 证明不存在该任务
            // sysScheduleTrigger.setAccount(SecurityUtils.getUserId());
            sysScheduleTrigger.setCreateTime(LocalDateTime.now());
            // 保存该任务
            int result = sysScheduleTriggerMapper.insert(sysScheduleTrigger);
            // 判断是否保存成功,切是否启动该任务
            if (result > 0 && sysScheduleTrigger.getStatus() == 1) {
                // 添加到定时任务  传递执行的任务类，参数，表达式
                schedulerAdd(sysScheduleTrigger);

                return ResultBean.success("定时任务新增成功");
            } else {
                return ResultBean.error("定时任务新增失败");
            }
        }

        return ResultBean.error("定时任务新增失败");
    }

    /**
     * 添加新的任务
     */
    @Override
    public void schedulerAdd(SysScheduleTrigger sysScheduleTrigger) {

        try {
            // 启动调度器
            scheduler.start();
            // 构建job信息

            //任务实例(JobDetail)
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobExecution.class) //加载任务类 完成与HelloJob的绑定 要求 HelloJob必须实现Job接口
                    .withIdentity(sysScheduleTrigger.getJobName(), sysScheduleTrigger.getJobGroup()) //参数1 任务的名称 参数二 任务组的名称
                    .usingJobData("messagel", "打印日志jobDetail")
                    .build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put("mengybQuratz", sysScheduleTrigger);

            // 任务组的名称
            //触发器(Triger)
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(sysScheduleTrigger.getTriggerName()) //参数1触发器的名称 参数2 触发器组的名称
                    .startNow()  //马上启动触发器
                    .withSchedule(CronScheduleBuilder.cronSchedule(sysScheduleTrigger.getCron()))
                    .usingJobData("message", "打印日志Trigger")
                    .build();
            //让调度器关联任务和触发器,保证按照触发器的条件执行任务
            scheduler.scheduleJob(jobDetail, trigger);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 删除定时任务
     *
     * @param sysScheduleTrigger
     */
    @Override
    public void schedulerDelete(SysScheduleTrigger sysScheduleTrigger) {
        JobKey jobKey = JobKey.jobKey(sysScheduleTrigger.getJobName(), sysScheduleTrigger.getJobGroup());
        try {
            boolean exists = scheduler.checkExists(jobKey);
            if (!exists) {
                return;
            }
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }

    @Transactional
    @Override
    public ResultBean deleteScheduleTrigger(int id) {
        // 先暂停任务  再删除
        SysScheduleTrigger scheduleTrigger = sysScheduleTriggerMapper.selectById(id);
        if (Objects.isNull(scheduleTrigger)) {
            // 该任务不存在
            return ResultBean.error("该任务不存在");
        }
        int result = sysScheduleTriggerMapper.deleteById(id);
        // 删除任务
        schedulerDelete(scheduleTrigger);
        return result > 0 ? ResultBean.success("删除成功") : ResultBean.error("定时任务删除失败");
    }


    @Transactional
    @Override
    public ResultBean updateScheduleStatus(int id, int status) {
        SysScheduleTrigger sysScheduleTrigger = sysScheduleTriggerMapper.selectById(id);
        LambdaUpdateWrapper<SysScheduleTrigger> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysScheduleTrigger::getStatus, status);
        updateWrapper.eq(SysScheduleTrigger::getId, id);
        SysScheduleTrigger scheduleTrigger = new SysScheduleTrigger();
        // 先暂停任务  再删除

        JobKey jobKey = JobKey.jobKey(sysScheduleTrigger.getJobName(), sysScheduleTrigger.getJobGroup());

        // 判断任务是否存在
        boolean exists = false;
        try {
            exists = scheduler.checkExists(jobKey);
            if (!exists) {
                throw new RuntimeException("该定时任务不存在");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if (status == 1) {
            // 恢复任务
            try {
                scheduler.resumeJob(jobKey);
            } catch (SchedulerException e) {
                throw new RuntimeException("恢复定时任务失败", e);
            }
            logger.info("开启任务:{}");
        } else {
            // 停止任务
            try {
                scheduler.pauseJob(jobKey);
            } catch (SchedulerException e) {
                throw new RuntimeException("暂停定时任务失败", e);
            }
            logger.info("停止任务:{}");
        }
        int result = sysScheduleTriggerMapper.update(scheduleTrigger, updateWrapper);
        if (status == 1) {
            return result > 0 ? ResultBean.success("任务恢复成功") : ResultBean.error("任务恢复失败");
        } else {
            return result > 0 ? ResultBean.success("任务暂停成功") : ResultBean.error("任务暂停失败");
        }

    }

    /**
     * 修改定时任务
     *
     * @param sysScheduleTrigger
     * @return
     */
    @Override
    public ResultBean updateScheduleTrigger(SysScheduleTrigger sysScheduleTrigger) {
        // 修改定时任务信息

        int result = sysScheduleTriggerMapper.updateById(sysScheduleTrigger);
        if (result > 0){
            return ResultBean.success();
        }else {
            return ResultBean.error();
        }
    }

    /**
     * 获取所有的定时任务
     * @return
     */
    @Override
    public List<SysScheduleTrigger> allSchedulerTrigger() {
        // QueryWrapper<SysScheduleTrigger> queryWrapper = new QueryWrapper();

        return sysScheduleTriggerMapper.selectList(null);
    }

    /**
     * 分页获取定时任务
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<SysScheduleTrigger> pageSchedulerTrigger(int page, int size) {
        PageHelper.startPage(page, size);
        return sysScheduleTriggerMapper.selectList(null);
    }

    /**
     * 通过反射获取Bean
     *
     * @param className
     * @return
     * @throws Exception
     */
    private static Job getClass(String className) throws Exception {
        Class<?> class1 = Class.forName(className);
        return (Job) class1.newInstance();
    }
}
