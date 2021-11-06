package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Coll;
import com.chen.mycardsystembackstage.service.CollService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private CollService collService;

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
        return collService.delColl(id);
    }
    @PostMapping("/sea")
    public List<Coll> seaColl(String name){
        return collService.seaColl(name);
    }
    @PostMapping("/add")
    public int addColl(String id,String name,String img,String text){
        Coll coll=new Coll();
        int count=collService.countColl()+1;
        coll.setCollId("o"+count);
        coll.setCollName(name);
        coll.setCollImg(img);
        coll.setCollText(text);
        return collService.addColl(coll);
    }
    @PostMapping("/up")
    public int upColl(String id,String name,String img,String text){
        Coll coll=new Coll();
        coll.setCollId(id);
        coll.setCollName(name);
        coll.setCollImg(img);
        coll.setCollText(text);
        return collService.upColl(coll);
    }

    @GetMapping("/count")
    public int countColl(){
        return collService.countColl();
    }
}
