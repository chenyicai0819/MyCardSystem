package com.chen.mycardsystembackstage.utils.QuartUtils;

import cn.hutool.extra.mail.MailUtil;
import com.alibaba.fastjson.JSONObject;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.service.UserService;
import com.chen.mycardsystembackstage.service.impl.UserServiceImpl;
import com.chen.mycardsystembackstage.utils.GetIpUtil;
import com.chen.mycardsystembackstage.utils.YiYanUtils;
import com.chen.mycardsystembackstage.utils.context.SpringContextUtils;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Project : MyCardSystem - PutChats
 * Powered by GeorgeChen On 2023-01-19 14:43:57
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Component
@Slf4j
public class PutChats implements Job {

    public void test(){
        PutChatsLogsUtils putChatsLogsUtils = SpringContextUtils.getContext().getBean(PutChatsLogsUtils.class);
        SysScheduleTrigger trigger = putChatsLogsUtils.getTrigge("test");
        log.info("测试的定时任务");
        putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),1, "后台通知消息","测试的定时任务");
    }

    /**
     * 提醒管理员注意账号安全，修改密码
     */
    public void updataPass(){
        PutChatsLogsUtils putChatsLogsUtils = SpringContextUtils.getContext().getBean(PutChatsLogsUtils.class);
        SysScheduleTrigger trigger = putChatsLogsUtils.getTrigge("updataPass");
        UserService userService = SpringContextUtils.getContext().getBean(UserService.class);

        List<User> users = userService.getAllUser();
        for (int i = 0; i < users.size(); i++) {
            String email = users.get(i).getEmail();

            String message = "MyCardSystem管理员"
                    +users.get(i).getUserName() +
                    "您好！\r\n请定时登录您的账户修改密码，以保障您的账户安全以及系统的数据安全！\r\n"+
                    "该信息为MyCardSystem自动发出，请勿回复！";
            String put = MailUtil.send(email,"MyCardSystem-请关注您的账户安全",message,false);
            if (put.length()>0){
                log.info("用户"+users.get(i).getUserId()+":"+users.get(i).getUserName()+"安全信息已发送至"+users.get(i).getEmail());
                putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),1, String.valueOf(users.get(i).getUserId()),message);
            }else {
                log.error("用户"+users.get(i).getUserId()+":"+users.get(i).getUserName()+"安全信息发送失败，请关注！");
                putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),0, String.valueOf(users.get(i).getUserId()),message);
            }
        }
    }

    /**
     * 给一个特定管理员发送消息
     */
    public void chatsForOne(){
        PutChatsLogsUtils putChatsLogsUtils = SpringContextUtils.getContext().getBean(PutChatsLogsUtils.class);
        SysScheduleTrigger trigger = putChatsLogsUtils.getTrigge("chatsForOne");
        UserService userService = SpringContextUtils.getContext().getBean(UserService.class);

        User user = userService.selUser("4");
        String email = user.getEmail();
        String message = "MyCardSystem管理员"
                +user.getUserName() +
                "您好！\r\n请定时登录您的账户修改密码，以保障您的账户安全以及系统的数据安全！\r\n"+
                "该信息为MyCardSystem自动发出，请勿回复！";
        String put = MailUtil.send(email,"MyCardSystem-请关注您的账户安全",message,false);
        if (put.length()>0){
            log.info("用户"+user.getUserId()+":"+user.getUserName()+"安全信息已发送至"+user.getEmail());
            putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),1, String.valueOf(user.getUserId()),message);
        }else {
            log.error("用户"+user.getUserId()+":"+user.getUserName()+"安全信息发送失败，请关注！");
            putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),0, String.valueOf(user.getUserId()),message);
        }
    }

    /**
     * 启动时显示Swagger的地址
     */
    public void showSwaggerHost(){
        PutChatsLogsUtils putChatsLogsUtils = SpringContextUtils.getContext().getBean(PutChatsLogsUtils.class);
        GetIpUtil getIpUtil =SpringContextUtils.getContext().getBean(GetIpUtil.class);
        SysScheduleTrigger trigger = putChatsLogsUtils.getTrigge("showSwaggerHost");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = Objects.equals(getIpUtil.getIpAddr(request), "0:0:0:0:0:0:0:1") ?"localhost":getIpUtil.getIpAddr(request);
        String message = "Swagger Host:"+ip+":8089/swagger-ui/index.html";
        log.warn(message);
        putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),1, "后台通知消息",message);
    }

    /**
     * 每天发送一则文字
     */
    public void putChatForDay(){
        PutChatsLogsUtils putChatsLogsUtils = SpringContextUtils.getContext().getBean(PutChatsLogsUtils.class);
        SysScheduleTrigger trigger = putChatsLogsUtils.getTrigge("putChatForDay");
        UserService userService = SpringContextUtils.getContext().getBean(UserService.class);
        YiYanUtils yiYanUtils =SpringContextUtils.getContext().getBean(YiYanUtils.class);

        List<User> users = new ArrayList<>();
        users.add(userService.selUser("1"));
        users.add(userService.selUser("4"));
        String history = String.valueOf(yiYanUtils.getHistory());
        JSONObject o = yiYanUtils.sendRequestWithHttpClient();
        for (int i = 0; i < users.size(); i++) {
            String email = users.get(i).getEmail();
            String message = o.getString("hitokoto")+"   ————"+o.getString("from")+"("+o.getString("from_who")+")"
                    +"\r\n\r\n"+history;
            String put = MailUtil.send(email,"MyCardSystem-每日一言",message,false);
            if (put.length()>0){
                log.info("用户"+users.get(i).getUserId()+":"+users.get(i).getUserName()+"每日一言"+users.get(i).getEmail());
                putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),1, String.valueOf(users.get(i).getUserId()),message);
            }else {
                log.error("用户"+users.get(i).getUserId()+":"+users.get(i).getUserName()+"每日一言发送失败，请关注！");
                putChatsLogsUtils.addTasksLogsUtils(trigger.getId(),trigger.getJobName(),0, String.valueOf(users.get(i).getUserId()),message);
            }
        }

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
