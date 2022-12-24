package com.chen.mycardsystembackstage.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.chen.mycardsystembackstage.entity.*;
import com.chen.mycardsystembackstage.service.*;
import com.chen.mycardsystembackstage.service.impl.FileServiceImpl;
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
import java.sql.Timestamp;
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
    @Resource
    private ImgsService imgsService;
    @Resource
    private FileService fileService;
    @Autowired
    private StringToUtf8 stu;
    @Autowired
    private LogsService logsService;

    /**
     * 下载备份文件
     * @param response
     * @param id
     */
    @GetMapping("/message")
    public void downloadMessage(HttpServletResponse response,int id){
        ExcelWriter writer= ExcelUtil.getWriter();
        int last=0;
        String content="";
        if (id==1){
            List<Card> list=cardService.showCard();
            content = FileServiceImpl.getStringCard(writer);
            writer.write(list, true);
        }else if(id==2){
            List<Mork> list=morkService.showMork();
            content = FileServiceImpl.getStringMork(writer);
            writer.write(list, true);
        }else if (id==3){
            List<Coll> list=collService.showColl();
            content = FileServiceImpl.getStringColl(writer);
            writer.write(list, true);
        }else if (id==4){
            List<Ad> list=adService.allAd();
            content = FileServiceImpl.getStringId(writer);
            writer.write(list, true);
        }else if (id==5){
            List<Imgs> list=imgsService.AllImgs();
            content = FileServiceImpl.getStringImg(writer);
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

    /**
     * 下载模板
     * @param response
     * @param id
     */
    @GetMapping("/model")
    public void downloadModel(HttpServletResponse response,int id){
        fileService.downloadModel(response,id);
    }

    @GetMapping("/logs")
    public void downloadLogs(HttpServletResponse response, String beginTime, String endTime, int type){
        ExcelWriter writer= ExcelUtil.getWriter();
        int last=0;
        String content="";

        Timestamp upDate = Timestamp.valueOf(beginTime);
        Timestamp downDate = Timestamp.valueOf(endTime);

        List<Logs> list=logsService.timeLogs(upDate,downDate,type);
        content = FileServiceImpl.getStringLogs(writer);
        writer.write(list, true);

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
