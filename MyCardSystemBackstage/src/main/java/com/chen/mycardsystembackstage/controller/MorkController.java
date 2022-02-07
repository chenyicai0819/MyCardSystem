package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Mork;
import com.chen.mycardsystembackstage.service.MorkService;
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
 * @date 2021/10/21 23:14
 * @since 1.0
 */
@RestController
@RequestMapping("/mork")
public class MorkController {

    @Resource
    private MorkService morkService;

    @GetMapping("/get")
    // @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.MorkMapper::get")
    public List<Mork> getMork(int page,int size){
        return morkService.getMork(page,size);
    }
    @GetMapping("/show")
    // @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.MorkMapper::show")
    public List<Mork> showMork(){
        return morkService.showMork();
    }

    @PostMapping("/add")
    public int addMork(Integer id,String name,String link,String type,String img){
        Mork mork=new Mork();
        mork.setMorkName(name);
        mork.setMorkLink(link);
        mork.setMorkType(type);
        mork.setMorkImg(img);
        return morkService.addMork(mork);
    }

    @PostMapping("/up")
    public int upMork(int id,String name,String link,String type,String img){
        Mork mork=new Mork();
        mork.setMorkName(name);
        mork.setMorkLink(link);
        mork.setMorkType(type);
        mork.setMorkImg(img);
        mork.setMorkId(id);
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
}
