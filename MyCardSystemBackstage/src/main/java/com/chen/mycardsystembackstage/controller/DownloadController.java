package com.chen.mycardsystembackstage.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.entity.Coll;
import com.chen.mycardsystembackstage.entity.Mork;
import com.chen.mycardsystembackstage.service.AdService;
import com.chen.mycardsystembackstage.service.CardService;
import com.chen.mycardsystembackstage.service.CollService;
import com.chen.mycardsystembackstage.service.MorkService;
import com.chen.mycardsystembackstage.utils.StringToUtf8;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2022/3/18 22:36
 * @since 1.0
 */
@Controller
@RequestMapping("/download")
public class DownloadController {
    @Resource
    private CardService cardService;
    @Resource
    private MorkService morkService;
    @Resource
    private CollService collService;
    @Resource
    private AdService adService;
    @Autowired
    private StringToUtf8 stu;

    @GetMapping("/message")
    public void downloadMessage(HttpServletResponse response,int id){
        ExcelWriter writer= ExcelUtil.getWriter();
        int last=0;
        String content="";
        if (id==1){
            List<Card> list=cardService.showCard();
            writer.addHeaderAlias("cardId", "唯一ID");
            writer.addHeaderAlias("cardName", "名称");
            writer.addHeaderAlias("cardLink", "链接");
            writer.addHeaderAlias("cardImg", "图片");
            last=3;content="mycard卡片备份表";
            writer.merge(last, content);
            writer.write(list, true);
        }else if(id==2){
            List<Mork> list=morkService.showMork();
            writer.addHeaderAlias("morkId", "唯一ID");
            writer.addHeaderAlias("morkName", "名称");
            writer.addHeaderAlias("morkLink", "链接");
            writer.addHeaderAlias("morkType", "类型");
            writer.addHeaderAlias("morkImg", "图片");
            last=4;content="mycard书签备份表";
            writer.merge(last, content);
            writer.write(list, true);
        }else if (id==3){
            List<Coll> list=collService.showColl();
            writer.addHeaderAlias("collId", "唯一ID");
            writer.addHeaderAlias("collName", "名称");
            writer.addHeaderAlias("collImg", "图片");
            writer.addHeaderAlias("collText", "介绍");
            last=3;content="mycard类型备份表";
            writer.merge(last, content);
            writer.write(list, true);
        }else if (id==4){
            List<Ad> list=adService.allAd();
            writer.addHeaderAlias("adId", "唯一ID");
            writer.addHeaderAlias("adName", "名称");
            writer.addHeaderAlias("adText", "介绍");
            writer.addHeaderAlias("adImg", "图片");
            writer.addHeaderAlias("adUpDate", "上架时间");
            writer.addHeaderAlias("adDownDate", "下架时间");
            last=5;content="mycard广告备份表";
            writer.merge(last, content);
            writer.write(list, true);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        String name = stu.getUTF8XMLString(content);

        response.setHeader("Content-Disposition", "attachment;filename=" +name+ ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }
}
