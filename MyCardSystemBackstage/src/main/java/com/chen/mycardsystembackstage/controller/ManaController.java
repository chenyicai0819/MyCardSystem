package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.ColumnName;
import com.chen.mycardsystembackstage.entity.Manages;
import com.chen.mycardsystembackstage.service.ManaService;
import org.springframework.cache.annotation.Cacheable;
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
 * @date 2021/10/22 16:43
 * @since 1.0
 */
@RestController
@RequestMapping("/mana")
public class ManaController {

    @Resource
    private ManaService manaService;

    @GetMapping("/get")
    // @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.ManaMapper::get")
    public List<Manages> getMana(){
        return manaService.getMana();
    }

    @PostMapping("/head")
    public List<ColumnName> getHead(int head){
        return manaService.getHead(head);
    }


}
