package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Imgs;
import com.chen.mycardsystembackstage.service.ImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2022/5/27 15:49
 * @since 1.0
 */

@RestController
@RequestMapping("/imgs")
public class ImgsController {

    @Autowired
    private ImgsService is;

    @GetMapping("/all")
    // @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.ImgsController::all")
    public List<Imgs> AllImgs(){
        return is.AllImgs();
    }
    @GetMapping("/get")
    public List<Imgs> GetImgs(int page,int size){
        return is.GetImgs(page,size);
    }

    @PostMapping("/add")
    public int AddImgs(String id,String text,String link,String name){
        Imgs imgs=new Imgs();
        imgs.setImgsName(name);
        imgs.setImgsLink(link);
        imgs.setImgsText(text);
        return is.AddImgs(imgs);
    }

    @PostMapping("/up")
    public int UpdataImgs(int id,String text,String link,String name){
        Imgs imgs=new Imgs();
        imgs.setImgsId(id);
        imgs.setImgsName(name);
        imgs.setImgsLink(link);
        imgs.setImgsText(text);
        return is.Updata(imgs);
    }

    @PostMapping("/del")
    public int DeleteImgs(int id){
        return is.DeleteImgs(id);
    }

    @GetMapping("/count")
    public int countImgs(){
        return is.countImgs();
    }
}
