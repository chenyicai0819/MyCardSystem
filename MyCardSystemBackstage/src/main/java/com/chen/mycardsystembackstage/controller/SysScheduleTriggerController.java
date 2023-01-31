package com.chen.mycardsystembackstage.controller;


import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.service.ISysScheduleTriggerService;
import com.chen.mycardsystembackstage.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project : MyCardSystem - SysScheduleTriggerController
 * Powered by GeorgeChen On 2023-01-30 16:27:56
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@RestController
@RequestMapping("/schedule")
public class SysScheduleTriggerController {

    @Autowired
    private ISysScheduleTriggerService sysScheduleTriggerService;



    /**
     * 新增定时任务  com.chen.mycardsystembackstage.utils.QuartUtils.QuartzJob
     * @param sysScheduleTrigger
     * @return
     */

    @PostMapping("/insertScheduleTrigger")
    public ResultBean insertScheduleTrigger(SysScheduleTrigger sysScheduleTrigger) {
        ResultBean resultBean = sysScheduleTriggerService.insertScheduleTrigger(sysScheduleTrigger);
        return resultBean;
    }


    /**
     * 更新定时任务
     * @param sysScheduleTrigger
     * @param id
     * @return
     */
    @PostMapping("/updateScheduleTrigger/{id}")
    public ResultBean updateScheduleTrigger(SysScheduleTrigger sysScheduleTrigger, @PathVariable("id") int id) {
        ResultBean resultBean = sysScheduleTriggerService.updateScheduleTrigger(sysScheduleTrigger);
        return resultBean;
    }


    /**
     * 删除定时任务
     * @param id
     * @return
     */
    @GetMapping("/deleteScheduleTrigger/{id}")
    public ResultBean deleteScheduleTrigger(@PathVariable("id") int id) {
        ResultBean resultBean = sysScheduleTriggerService.deleteScheduleTrigger(id);
        return resultBean;
    }

    /**
     * 获取所有的定时任务
     * @return
     */
    @GetMapping("/allSchedulerTrigger")
    public List<SysScheduleTrigger> allSchedulerTrigger(){
        return sysScheduleTriggerService.allSchedulerTrigger();
    }

    /**
     * 分页获取定时任务
     * @param page 当前页码
     * @param size 每页数量
     * @return 定时任务集合
     */
    @GetMapping("/pageSchedulerTrigger")
    public List<SysScheduleTrigger> pageSchedulerTrigger(int page,int size){
        return sysScheduleTriggerService.pageSchedulerTrigger(page, size);
    }


}
