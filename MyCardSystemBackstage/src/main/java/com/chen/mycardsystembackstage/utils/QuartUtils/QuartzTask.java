package com.chen.mycardsystembackstage.utils.QuartUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.service.ISysScheduleTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project : MyCardSystem - QuartzTask
 * Powered by GeorgeChen On 2023-01-30 16:53:58
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Component // @Component 将该类注册成为 Spring 容器中的一个 Bean
@Order(100) // @Order(100) 标明该启动任务的优先级，值越大，表示优先级越小
public class QuartzTask implements CommandLineRunner {

    @Autowired
    private ISysScheduleTriggerService sysScheduleTriggerService;

    /**
     * 从数据库查询出启动的定时任务，并将他们添加到定时任务启动中，这样项目一启动时，就会自动启动我们定义的定时任务了
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // LambdaQueryWrapper<SysScheduleTrigger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper.eq(SysScheduleTrigger::getStatus, 1);
        // List<SysScheduleTrigger> scheduleTriggerList = sysScheduleTriggerService.list(lambdaQueryWrapper);
        // if (null != scheduleTriggerList && !scheduleTriggerList.isEmpty()) {
        //     for (SysScheduleTrigger item: scheduleTriggerList) {
        //         // 首先删除定时任务
        //         sysScheduleTriggerService.schedulerDelete(item);
        //         // 再添加定时任务
        //         sysScheduleTriggerService.schedulerAdd(item);
        //     }
        // }

    }
}