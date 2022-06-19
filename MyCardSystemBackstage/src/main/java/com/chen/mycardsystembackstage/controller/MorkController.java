package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Mork;
import com.chen.mycardsystembackstage.service.MorkService;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2021/10/21 23:14
 * @since 1.0
 */
@RestController
@RequestMapping("/mork")
public class MorkController {

    @Resource
    private MorkService morkService;

    @GetMapping("/get")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.MorkMapper::get")
    public List<Mork> getMork(int page,int size){
        return morkService.getMork(page,size);
    }
    @GetMapping("/show")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.MorkMapper::show")
    public List<Mork> showMork(){
        return morkService.showMork();
    }

    @PostMapping("/add")
    public int addMork(Integer id,String name,String link,String type,String img,String text){
        Mork mork=new Mork();
        mork.setMorkName(name);
        mork.setMorkLink(link);
        mork.setMorkType(type);
        mork.setMorkImg(img);
        mork.setMorkText(text);
        return morkService.addMork(mork);
    }

    @PostMapping("/up")
    public int upMork(int id,String name,String link,String type,String img,String text){
        Mork mork=new Mork();
        mork.setMorkName(name);
        mork.setMorkLink(link);
        mork.setMorkType(type);
        mork.setMorkImg(img);
        mork.setMorkId(id);
        mork.setMorkText(text);
        return morkService.upMork(mork);
    }

    @PostMapping("/del")
    public int delMork(int id){
        return morkService.delMork(id);
    }
    @PostMapping("/sea")
    public List<Mork> seaMork(String name){
        return morkService.seaMork(name);
    }

    @GetMapping("/count")
    public int countMork(){
        return morkService.countMork();
    }

    @GetMapping("/recommend")
    public int recommend(String name,String link,String text,String user,String email,String model){
        int out=0;
        HtmlEmail htmlemail = new HtmlEmail();
        htmlemail.setHostName("smtp.qq.com");
        htmlemail.setSSLOnConnect(true);
        htmlemail.setSslSmtpPort("465");
        htmlemail.setCharset("UTF-8");
        htmlemail.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
        try {
            htmlemail.setFrom("chenyc2021@qq.com", email,"utf-8");
            htmlemail.setSubject("推荐网站");
            StringBuilder sb=new StringBuilder();
            sb.append("网站名称：").append(name).append('\n')
                    .append("网站网址：").append(link).append('\n')
                    .append("网址介绍：").append(text).append('\n')
                    .append("推荐人").append(user).append('\n')
                    .append("推荐人邮箱").append(email);
            htmlemail.setHtmlMsg(model);
            // htmlemail.setHtmlMsg(sb.toString());
            htmlemail.addTo("chenyc2021@qq.com");
            htmlemail.send();
            out=1;

        } catch (EmailException e) {
            e.printStackTrace();
            out=0;
        } finally {
            if(out==1){
                try {
                    HtmlEmail emailOut = new HtmlEmail();
                    emailOut.setHostName("smtp.qq.com");
                    emailOut.setSSLOnConnect(true);
                    emailOut.setSslSmtpPort("465");
                    emailOut.setCharset("UTF-8");
                    emailOut.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
                    emailOut.setFrom("chenyc2021@qq.com", "mycard管理员","utf-8");
                    emailOut.setSubject("回复");
                    // emailOut.setHtmlMsg(reply);
                    emailOut.addTo(email);
                    //发送邮件
                    // emailOut.send();
                } catch (EmailException e) {
                    e.printStackTrace();
                }
            }
            return out;
        }
    }
    @GetMapping("/reply")
    public void reply(String email,String model){
        HtmlEmail emailOut = new HtmlEmail();
        emailOut.setHostName("smtp.qq.com");
        emailOut.setSSLOnConnect(true);
        emailOut.setSslSmtpPort("465");
        emailOut.setCharset("UTF-8");
        emailOut.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
        try {
            emailOut.setFrom("chenyc2021@qq.com", "mycard管理员","utf-8");
            emailOut.setSubject("回复");
            emailOut.setHtmlMsg(model);
            emailOut.addTo(email);
            emailOut.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }
}
