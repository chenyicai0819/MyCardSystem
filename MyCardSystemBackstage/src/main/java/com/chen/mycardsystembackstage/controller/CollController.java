package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Coll;
import com.chen.mycardsystembackstage.service.CollService;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
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
 * @date 2021/10/21 22:49
 * @since 1.0
 */
@RestController
@RequestMapping("/coll")
public class CollController {

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
    private CollService collService;
    @Autowired
    private WeChatNotify wcn;

    @GetMapping("/get")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.CollMapper::get")
    public List<Coll> getColl(int page,int size){
        return collService.getColl(page, size);
    }

    @GetMapping("/show")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.CollMapper::show")
    public List<Coll> showColl(){
        return collService.showColl();
    }

    @PostMapping("/del")
    public int delColl(String id){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",addr.getHostAddress());
        map.put("type","删除书签");
        map.put("name",collService.seaNameColl(id));
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return collService.delColl(id);
    }
    @PostMapping("/sea")
    public List<Coll> seaColl(String name){
        return collService.seaColl(name);
    }
    @PostMapping("/add")
    public int addColl(String id,String name,String img,String text){
        Coll coll=new Coll();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int count=collService.countColl()+1;
        coll.setCollId("o"+count);
        coll.setCollName(name);
        coll.setCollImg(img);
        coll.setCollText(text);

        map.put("ip",addr.getHostAddress());
        map.put("type","添加书签");
        map.put("name",name);
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return collService.addColl(coll);
    }
    @PostMapping("/up")
    public int upColl(String id,String name,String img,String text){
        Coll coll=new Coll();
        coll.setCollId(id);
        coll.setCollName(name);
        coll.setCollImg(img);
        coll.setCollText(text);

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",addr.getHostAddress());
        map.put("type","更新书签");
        map.put("name",collService.seaNameColl(id));
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return collService.upColl(coll);
    }

    @GetMapping("/count")
    public int countColl(){
        return collService.countColl();
    }
}
