package com.chen.mycardsystembackstage.utils.QuartUtils;

import cn.hutool.extra.mail.MailUtil;
import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Autowired
    private UserService userService;

    /**
     * 提醒管理员注意账号安全，修改密码
     */
    public void updataPass(){
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
            }else {
                log.error("用户"+users.get(i).getUserId()+":"+users.get(i).getUserName()+"安全信息发送失败，请关注！");
            }
        }
    }

    public void chatsForOne(){
        User user = userService.selUser("4");
        String email = user.getEmail();
        String message = "MyCardSystem管理员"
                +user.getUserName() +
                "您好！\r\n请定时登录您的账户修改密码，以保障您的账户安全以及系统的数据安全！\r\n"+
                "该信息为MyCardSystem自动发出，请勿回复！";
        String put = MailUtil.send(email,"MyCardSystem-请关注您的账户安全",message,false);
        if (put.length()>0){
            log.info("用户"+user.getUserId()+":"+user.getUserName()+"安全信息已发送至"+user.getEmail());
        }else {
            log.error("用户"+user.getUserId()+":"+user.getUserName()+"安全信息发送失败，请关注！");
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}