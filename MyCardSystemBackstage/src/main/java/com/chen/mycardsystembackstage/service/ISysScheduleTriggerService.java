package com.chen.mycardsystembackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.mycardsystembackstage.common.responsecode.CodeDefault;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.utils.ResultBean;

import java.util.List;

/**
 * Project : MyCardSystem - ISysScheduleTriggerService
 * Powered by GeorgeChen On 2023-01-30 16:09:01
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public interface ISysScheduleTriggerService extends IService<SysScheduleTrigger> {
    //更新任务
    void refreshTrigger();

    /**
     * 新增定时任务
     * @param sysScheduleTrigger
     * @return
     */
    public ResultBean insertScheduleTrigger(SysScheduleTrigger sysScheduleTrigger);


    /**
     * 添加定时任务
     */
    public void schedulerAdd(SysScheduleTrigger sysScheduleTrigger);

    /**
     * 删除定时任务
     * @param sysScheduleTrigger
     */
    public void schedulerDelete(SysScheduleTrigger sysScheduleTrigger);

    /**
     * 删除定时任务
     * @param id
     * @return
     */
    ResultBean deleteScheduleTrigger(int id);

    ResultBean updateScheduleStatus(int id, int status);

    /**
     * 修改定时任务
     * @param sysScheduleTrigger
     * @return
     */
    ResultBean updateScheduleTrigger(SysScheduleTrigger sysScheduleTrigger);

    /**
     * 获取所有的定时任务
     * @return
     */
    List<SysScheduleTrigger> allSchedulerTrigger();

    /**
     * 分页获取定时任务
     * @param page
     * @param size
     * @return
     */
    List<SysScheduleTrigger> pageSchedulerTrigger(int page,int size);

}
