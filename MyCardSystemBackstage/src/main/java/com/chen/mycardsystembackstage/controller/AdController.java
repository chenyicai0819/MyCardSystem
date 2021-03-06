package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.service.AdService;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2021/11/1 22:10
 * @since 1.0
 */

@RestController
@RequestMapping("/ad")
public class AdController {

    Map<String,Object> map=new HashMap<>();

    InetAddress addr;

    {
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }



    @Resource
    private AdService adService;
    @Autowired
    private WeChatNotify wcn;

    @GetMapping("/get")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.AdMapper::get")
    public List<Ad> getAd(int page,int size){
        List<Ad> ad=adService.getAd(page,size);
        return ad;
    }
    @GetMapping("/show")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.AdMapper::show")
    public List<Ad> showCard(){
        return adService.showAd();
    }

    @PostMapping("/add")
    public int addAd(String id, String name, String text, String img, String adUpDate,String adDownDate){
        int count=adService.countAd()+1;
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp upDate = Timestamp.valueOf(adUpDate);
        Timestamp downDate = Timestamp.valueOf(adDownDate);
        Ad ad=new Ad();
        ad.setAdId("a"+count);
        ad.setAdName(name);
        ad.setAdText(text);
        ad.setAdImg(img);
        ad.setAdUpDate(upDate);
        ad.setAdDownDate(downDate);
        map.put("ip",addr.getHostAddress());
        map.put("type","????????????");
        map.put("name",name);
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return adService.addAd(ad);
    }
    @PostMapping("/del")
    public int delAd(String id){
        return adService.delAd(id);
    }
    @PostMapping("/sea")
    public List<Ad> seaAd(String name){
        return adService.seaAd(name);
    }
    @PostMapping("/up")
    public int upAd(String id, String name, String text, String img, String adUpDate,String adDownDate){
        Ad ad=new Ad();
        Timestamp upDate = Timestamp.valueOf(adUpDate);
        Timestamp downDate = Timestamp.valueOf(adDownDate);
        ad.setAdId(id);
        ad.setAdName(name);
        ad.setAdText(text);
        ad.setAdImg(img);
        ad.setAdUpDate(upDate);
        ad.setAdDownDate(downDate);
        return adService.upAd(ad);
    }
    @GetMapping("/count")
    public int countAd(){
        return adService.countAd();
    }

    @GetMapping("/buy")
    public int buyAd(String title,String text,String money,String time,String phone,String buyemail) {
        int out=0;
        // ??????
        // EmailAttachment attachment = new EmailAttachment();
        // // ????????????
        // attachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        // // ???????????????
        // attachment.setDisposition(EmailAttachment.ATTACHMENT);
        // // ????????????
        // attachment.setDescription("Apache logo");
        // ?????????
        // attachment.setName("Apache logo");
        // // ????????????
        // email.attach(attachment);
        // // ????????????????????????
        // email.attach(new File("D:\\MyFile\\??????\\94307472_p0.jpg"));
        // ??????????????????????????????????????????????????????????????????????????????
        // ????????????
        HtmlEmail email = new HtmlEmail();
        // ??????????????????????????????????????????IP??????
        email.setHostName("smtp.qq.com");
        // ????????????????????????
        email.setSSLOnConnect(true);
        // QQ????????????????????????465
        email.setSslSmtpPort("465");
        // ???????????????????????????????????????????????????
        email.setCharset("UTF-8");
        // ??????????????????????????????????????????
        email.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
        try {
            // ??????????????????????????????????????????????????????????????????
            email.setFrom("chenyc2021@qq.com", buyemail,"utf-8");
            // ??????
            email.setSubject(title);
            // ??????
            StringBuilder sb=new StringBuilder();
            sb.append("???????????????").append(text).append('\n')
                    .append("???????????????").append(money).append('\n')
                    .append("???????????????").append(phone).append('\n')
                    .append("????????????").append(time);
            email.setHtmlMsg(sb.toString());
            email.addTo("chenyc2021@qq.com");
            //????????????
            email.send();
            out=1;

        } catch (EmailException e) {
            e.printStackTrace();
            out=0;
        } finally {
            System.out.println(out);
            if(out==1){
                try {
                    HtmlEmail emailOut = new HtmlEmail();
                    emailOut.setHostName("smtp.qq.com");
                    emailOut.setSSLOnConnect(true);
                    emailOut.setSslSmtpPort("465");
                    emailOut.setCharset("UTF-8");
                    emailOut.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
                    emailOut.setFrom("chenyc2021@qq.com", "mycard?????????","utf-8");
                    emailOut.setSubject("??????");
                    emailOut.setHtmlMsg("?????????????????????????????????????????????????????????????????????????????????");
                    emailOut.addTo(buyemail);
                    //????????????
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
        try {
            HtmlEmail emailOut = new HtmlEmail();
            emailOut.setHostName("smtp.qq.com");
            emailOut.setSSLOnConnect(true);
            emailOut.setSslSmtpPort("465");
            emailOut.setCharset("UTF-8");
            emailOut.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
            emailOut.setFrom("chenyc2021@qq.com", "mycard?????????","utf-8");
            emailOut.setSubject("??????");
            emailOut.setHtmlMsg(model);
            emailOut.addTo(email);
            //????????????
            emailOut.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
