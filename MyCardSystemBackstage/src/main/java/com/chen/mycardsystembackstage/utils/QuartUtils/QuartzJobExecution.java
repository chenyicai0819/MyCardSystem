package com.chen.mycardsystembackstage.utils.QuartUtils;

import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import org.quartz.JobExecutionContext;
/**
 * Project : MyCardSystem - QuartzJobExecution
 * Powered by GeorgeChen On 2023-01-30 16:38:25
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public class QuartzJobExecution extends QuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysScheduleTrigger sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
