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
    // @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.AdMapper::get")
    public List<Ad> getAd(int page,int size){
        List<Ad> ad=adService.getAd(page,size);
        return ad;
    }
    @GetMapping("/show")
    // @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.AdMapper::show")
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
        map.put("type","添加广告");
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
        // 附件
        // EmailAttachment attachment = new EmailAttachment();
        // // 附件地址
        // attachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        // // 附件的形式
        // attachment.setDisposition(EmailAttachment.ATTACHMENT);
        // // 附件描述
        // attachment.setDescription("Apache logo");
        // 附件名
        // attachment.setName("Apache logo");
        // // 添加附件
        // email.attach(attachment);
        // // 也可以是本地文件
        // email.attach(new File("D:\\MyFile\\插画\\94307472_p0.jpg"));
        // 设置收件人，有多个的话继续加参数就好了，这是可变参数
        // 创建邮件
        HtmlEmail email = new HtmlEmail();
        // 邮箱主机，可以是域名也可以是IP地址
        email.setHostName("smtp.qq.com");
        // 设置使用安全链接
        email.setSSLOnConnect(true);
        // QQ邮箱默认的端口是465
        email.setSslSmtpPort("465");
        // 有中文的话需要设置编码，不然会乱码
        email.setCharset("UTF-8");
        // 设置你的邮箱名与生成的授权码
        email.setAuthentication("chenyc2021@qq.com", "cqyruivpditnebhc");
        try {
            // 发件人信息，收件人收到邮件时看到的收件人姓名
            email.setFrom("chenyc2021@qq.com", buyemail,"utf-8");
            // 标题
            email.setSubject(title);
            // 内容
            StringBuilder sb=new StringBuilder();
            sb.append("广告内容：").append(text).append('\n')
                    .append("广告报价：").append(money).append('\r')
                    .append("联系方式：").append(phone).append('\r')
                    .append("上架时间").append(time);
            email.setHtmlMsg(sb.toString());
            email.addTo("chenyc2021@qq.com");
            //发送邮件
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
                    emailOut.setFrom("chenyc2021@qq.com", "mycard管理员","utf-8");
                    emailOut.setSubject("回复");
                    emailOut.setHtmlMsg("我们已经收到了您的邮件内容，请您耐心等待工作人员的回复");
                    emailOut.addTo(buyemail);
                    //发送邮件
                    emailOut.send();
                } catch (EmailException e) {
                    e.printStackTrace();
                }
            }
            return out;
        }
    }
}
