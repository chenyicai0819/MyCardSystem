package com.chen.mycardsystembackstage.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;
import com.chen.mycardsystembackstage.entity.*;
import com.chen.mycardsystembackstage.service.*;
import com.chen.mycardsystembackstage.utils.StringToUtf8;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private StringToUtf8 stu;
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

    @Override
    public JSONObject uploadFileController(MultipartFile multipartFile,int id) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filename = multipartFile.getOriginalFilename();//获取文件的名称
        InputStream inputStream = multipartFile.getInputStream();
        // 定义一个org.apache.poi.ss.usermodel.Workbook的变量
        Workbook workbook = null;
        // 匹配文件后面的后缀名，判断是xls还是xlsx
        if (filename.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (filename.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }

        // 获取第一张表
        Sheet sheet = workbook.getSheetAt(0);
        // sheet.getPhysicalNumberOfRows()获取总的行数
        // 循环读取每一行
        //  第一行为标题，第二行为表头，所以从第三行开始读取/
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            // 循环读取每一个格
            Row row = sheet.getRow(i);
            // row.getPhysicalNumberOfCells()获取总的列数
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                // 获取数据，但是我们获取的cell类型
                Cell cell = row.getCell(index);
                // 转换为字符串类型
                cell.setCellType(CellType.STRING);
                // 获取得到字符串
                String l = cell.getStringCellValue();
//                System.out.print(l+" ");

            }
            if (id == 1){
                Card card=new Card();
                int count =cardService.countCard()+1;
                card.setCardLink(row.getCell(2).getStringCellValue());
                card.setCardImg(row.getCell(3).getStringCellValue());
                card.setCardName(row.getCell(1).getStringCellValue());
                card.setCardId("c"+count);
                int t = cardService.addCard(card);
            } else if (id == 2) {
                Mork mork=new Mork();
                int count = morkService.countMork()+1;
                mork.setMorkLink(row.getCell(2).getStringCellValue());
                mork.setMorkImg(row.getCell(4).getStringCellValue());
                mork.setMorkName(row.getCell(1).getStringCellValue());
                mork.setMorkText(row.getCell(5).getStringCellValue());
                mork.setMorkType(row.getCell(3).getStringCellValue());
                mork.setMorkId(count);
                int t= morkService.addMork(mork);
            } else if (id == 3) {
                Coll coll = new Coll();
                int count = collService.countColl()+1;
                coll.setCollImg(row.getCell(2).getStringCellValue());
                coll.setCollName(row.getCell(1).getStringCellValue());
                coll.setCollText(row.getCell(3).getStringCellValue());
                coll.setCollId("o"+count);
                int t = collService.addColl(coll);
            } else if (id == 4) {
                Ad ad = new Ad();
                int count =adService.countAd()+1;
                ad.setAdImg(row.getCell(3).getStringCellValue());
                ad.setAdName(row.getCell(1).getStringCellValue());
                ad.setAdText(row.getCell(2).getStringCellValue());
                ad.setAdUpDate(Timestamp.valueOf(row.getCell(4).getStringCellValue()));
                ad.setAdDownDate(Timestamp.valueOf(row.getCell(5).getStringCellValue()));
                ad.setAdId("a"+count);
                int t = adService.addAd(ad);
            }

//            System.out.println();
        }
        return null;
    }

    /**
     * 下载批量上传的模板，保留了第一行的数据作为参考
     * @param response
     * @param id
     */
    @Override
    public void downloadModel(HttpServletResponse response, int id) {
        ExcelWriter writer= ExcelUtil.getWriter();
        int last=0;
        String content="";
        if (id==1){
            List<Card> list=cardService.getCard(1,1);
            content = getStringCard(writer);
            writer.write(list, true);
        }else if(id==2){
            List<Mork> list=morkService.getMork(1,1);
            content = getStringMork(writer);
            writer.write(list, true);
        }else if (id==3){
            List<Coll> list=collService.getColl(1,1);
            content = getStringColl(writer);
            writer.write(list, true);
        }else if (id==4){
            List<Ad> list=adService.getAd(1,1);
            content = getStringId(writer);
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

    public static String getStringImg(ExcelWriter writer) {
        int last;
        String content;
        writer.addHeaderAlias("imgsId", "唯一ID");
        writer.addHeaderAlias("imgsName", "名称");
        writer.addHeaderAlias("imgsText", "介绍");
        writer.addHeaderAlias("imgsLink", "图片地址");
        last=3;
        content="mycard图片备份模板";
        writer.merge(last, content);
        return content;
    }

    public static String getStringId(ExcelWriter writer) {
        int last;
        String content;
        writer.addHeaderAlias("adId", "唯一ID");
        writer.addHeaderAlias("adName", "名称");
        writer.addHeaderAlias("adText", "介绍");
        writer.addHeaderAlias("adImg", "图片");
        writer.addHeaderAlias("adUpDate", "上架时间");
        writer.addHeaderAlias("adDownDate", "下架时间");
        last=5;
        content="mycard广告备份模板";
        writer.merge(last, content);
        return content;
    }

    public static String getStringColl(ExcelWriter writer) {
        int last;
        String content;
        writer.addHeaderAlias("collId", "唯一ID");
        writer.addHeaderAlias("collName", "名称");
        writer.addHeaderAlias("collImg", "图片");
        writer.addHeaderAlias("collText", "介绍");
        last=3;
        content="mycard类型备份模板";
        writer.merge(last, content);
        return content;
    }

    public static String getStringMork(ExcelWriter writer) {
        int last;
        String content;
        writer.addHeaderAlias("morkId", "唯一ID");
        writer.addHeaderAlias("morkName", "名称");
        writer.addHeaderAlias("morkLink", "链接");
        writer.addHeaderAlias("morkType", "类型");
        writer.addHeaderAlias("morkImg", "图片");
        writer.addHeaderAlias("morkText", "介绍");
        last=4;
        content="mycard书签备份模板";
        writer.merge(last, content);
        return content;
    }

    public static String getStringCard(ExcelWriter writer) {
        int last;
        String content;
        writer.addHeaderAlias("cardId", "唯一ID");
        writer.addHeaderAlias("cardName", "名称");
        writer.addHeaderAlias("cardLink", "链接");
        writer.addHeaderAlias("cardImg", "图片");
        last=3;
        content="mycard卡片备份模板";
        writer.merge(last, content);
        return content;
    }

    /**
     * @param multiFile
     * @return
     * @Author chen
     * @Description 将MultipartFile转换为File
     */
    private File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码
        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
