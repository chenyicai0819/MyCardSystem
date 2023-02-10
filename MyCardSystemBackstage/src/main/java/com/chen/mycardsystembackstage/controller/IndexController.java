package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.IndexMenu;
import com.chen.mycardsystembackstage.entity.VO.IndexMenuVO;
import com.chen.mycardsystembackstage.mapper.IndexMenuMapper;
import com.chen.mycardsystembackstage.service.IndexService;
import com.chen.mycardsystembackstage.utils.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project : MyCardSystem - IndexController
 * Powered by GeorgeChen On 2023-02-09 22:20:48
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation("获取全部菜单")
    @GetMapping("/getIndexMenu")
    public List<IndexMenuVO> getIndexMenu(){
        return indexService.getIndexMenu();
    }

    @ApiOperation("添加菜单")
    @PostMapping("/addIndexMenu")
    public ResultBean addIndexMenu(IndexMenu indexMenu){
        return indexService.addIndexMenu(indexMenu);
    }

    @ApiOperation("修改菜单")
    @PostMapping("/uploadIndexMenu")
    public ResultBean updateIndexMenu(IndexMenu indexMenu){
        return indexService.updateIndexMenu(indexMenu);
    }

    @ApiOperation("删除菜单")
    @PostMapping("/delIndexMenu")
    public ResultBean delIndexMenu(int id){
        return indexService.delIndexMenu(id);
    }
}
