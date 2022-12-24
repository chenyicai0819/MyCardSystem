package com.chen.mycardsystembackstage.aspects;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.chen.mycardsystembackstage.entity.Logs;
import com.chen.mycardsystembackstage.service.LogsService;
import com.chen.mycardsystembackstage.service.StartService;
import com.chen.mycardsystembackstage.utils.GetIpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Project : MyCardSystem - LogsAspect
 * Powered by GeorgeChen On 2022-12-15 23:00:16
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 日志 Aop 类
 **/

@Component
@Aspect
@Slf4j
public class LogsAspect {

    @Autowired
    private GetIpUtil getIpUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogsService logsService;
    @Autowired
    private StartService startService;

    /**
     *
     * 新增日志
     */
    @Pointcut("execution(* com.chen.mycardsystembackstage.controller.*.add*(..))")
    private void addLogsCut() {}

    /**
     *
     * 修改日志
     */
    @Pointcut("execution(* com.chen.mycardsystembackstage.controller.*.up*(..))")
    private void upLogsCut() {}

    /**
     *
     * 删除日志
     */
    @Pointcut("execution(* com.chen.mycardsystembackstage.controller.*.del*(..))")
    private void delLogsCut() {}

    /**
     *
     * 登录日志
     */
    @Pointcut("execution(* com.chen.mycardsystembackstage.controller.UserController.login(..))")
    private void loginLogsCut() {}


    @After("addLogsCut()")
    public void afterAdd(JoinPoint joinPoint) {
        addLogsUtils("新增",joinPoint);
    }

    @After("upLogsCut()")
    public void afterUp(JoinPoint joinPoint) {
        addLogsUtils("修改",joinPoint);
    }

    @After("delLogsCut()")
    public void afterDel(JoinPoint joinPoint) {
        addLogsUtils("删除",joinPoint);
    }

    @After("loginLogsCut()")
    public void afterLogin(JoinPoint joinPoint) {
        addLogsUtils("登录",joinPoint);
    }

    /**
     * 封装新增日志的方法
     * @param type 日志类型
     * @param joinPoint 切点
     */
    private void addLogsUtils(String type,JoinPoint joinPoint){
        // 接收到请求，RequestContextHolder来获取请求信息，Session信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取访问者的ip地址
        String ip = getIpUtil.getIpAddr(request);
        // 获取当前时间
        String time = DateUtil.today()+" "+DateUtil.formatTime(DateUtil.date());
        // 获取当前方法的路径
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 获取输入的参数
        String args = Arrays.toString(joinPoint.getArgs());
        // 获取请求的方法
        String path = request.getServletPath();

        Logs logs = new Logs();
        logs.setType(type);
        logs.setIp(ip);
        logs.setArgs(args);
        logs.setClassMethod(classMethod);
        logs.setPath(path);
        logs.setTime(Timestamp.valueOf(time));
        logs.setUser(startService.selStart(ip));
        logsService.addLogs(logs);
    }
}
