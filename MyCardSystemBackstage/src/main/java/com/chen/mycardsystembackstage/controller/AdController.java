package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Resource
    private AdService adService;

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
        Timestamp upDate = Timestamp.valueOf(adUpDate);
        Timestamp downDate = Timestamp.valueOf(adDownDate);
        Ad ad=new Ad();
        ad.setAdId("a"+count);
        ad.setAdName(name);
        ad.setAdText(text);
        ad.setAdImg(img);
        ad.setAdUpDate(upDate);
        ad.setAdDownDate(downDate);
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
