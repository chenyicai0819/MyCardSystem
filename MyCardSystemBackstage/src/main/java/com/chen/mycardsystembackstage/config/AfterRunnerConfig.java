package com.chen.mycardsystembackstage.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.service.ISysScheduleTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Project : MyCardSystem - AfterRunnerConfig
 * Powered by GeorgeChen On 2023-01-31 15:11:16
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 启动程序后执行的方法
 **/
@Component
@Slf4j
@Order(value = 101)
public class AfterRunnerConfig implements ApplicationRunner {
    @Autowired
    private ISysScheduleTriggerService sysScheduleTriggerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.warn("Swagger Host-->http://localhost:8089/swagger-ui/index.html");

        LambdaQueryWrapper<SysScheduleTrigger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysScheduleTrigger::getStatus, 1);
        List<SysScheduleTrigger> scheduleTriggerList = sysScheduleTriggerService.list(lambdaQueryWrapper);
        if (null != scheduleTriggerList && !scheduleTriggerList.isEmpty()) {
            for (SysScheduleTrigger item: scheduleTriggerList) {
                // 首先删除定时任务
                sysScheduleTriggerService.schedulerDelete(item);
                // 再添加定时任务
                sysScheduleTriggerService.schedulerAdd(item);
            }
        }
    }
}
