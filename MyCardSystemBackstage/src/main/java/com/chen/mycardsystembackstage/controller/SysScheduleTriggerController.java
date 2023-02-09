package com.chen.mycardsystembackstage.controller;


import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.service.ISysScheduleTriggerService;
import com.chen.mycardsystembackstage.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "定时任务操作接口",tags = "定时任务")
@RequestMapping("/schedule")
public class SysScheduleTriggerController {

    @Autowired
    private ISysScheduleTriggerService sysScheduleTriggerService;



    /**
     * 新增定时任务  com.chen.mycardsystembackstage.utils.QuartUtils.QuartzJob
     * @param sysScheduleTrigger
     * @return
     */

    @ApiOperation("新增定时任务")
    @PostMapping("/insertScheduleTrigger")
    public ResultBean insertScheduleTrigger(SysScheduleTrigger sysScheduleTrigger) {
        ResultBean resultBean = sysScheduleTriggerService.insertScheduleTrigger(sysScheduleTrigger);
        return resultBean;
    }


    /**
     * 更新定时任务
     * @param sysScheduleTrigger
     * @return
     */
    @ApiOperation("更新定时任务")
    @PostMapping("/updateScheduleTrigger")
    public ResultBean updateScheduleTrigger(SysScheduleTrigger sysScheduleTrigger) {
        ResultBean resultBean = sysScheduleTriggerService.updateScheduleTrigger(sysScheduleTrigger);
        return resultBean;
        // return ResultBean.success();
    }


    /**
     * 删除定时任务
     * @param id
     * @return
     */
    @ApiOperation("删除定时任务")
    @GetMapping("/deleteScheduleTrigger")
    public ResultBean deleteScheduleTrigger(int id) {
        // ResultBean resultBean = sysScheduleTriggerService.deleteScheduleTrigger(id);
        // return resultBean;
        return ResultBean.success();
    }

    /**
     * 获取所有的定时任务
     * @return
     */
    @GetMapping("/allSchedulerTrigger")
    @ApiOperation("获取所有的定时任务")
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
    @ApiOperation("分页获取定时任务")
    public List<SysScheduleTrigger> pageSchedulerTrigger(int page,int size){
        return sysScheduleTriggerService.pageSchedulerTrigger(page, size);
    }


}
