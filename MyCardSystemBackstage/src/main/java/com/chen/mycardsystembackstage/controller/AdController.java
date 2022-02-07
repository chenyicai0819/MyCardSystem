package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.service.AdService;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
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
}
