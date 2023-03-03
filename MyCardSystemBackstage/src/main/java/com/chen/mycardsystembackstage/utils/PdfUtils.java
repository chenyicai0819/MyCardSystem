package com.chen.mycardsystembackstage.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Project : MyCardSystem - PdfUtils
 * Powered by GeorgeChen On 2023-03-03 14:30:41
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 *
 * PDF生成工具类
 **/
@Component
public class PdfUtils {

    @Value("${utils.pdf}")
    private String pdfPath;

    /**
     * 将多张图片拼接到PDF
     * @param name 保存的文件名
     * @param imageFiles 生成pdf的图片列表
     */
    public void ImgToPdf(File[] imageFiles, String name) {
        try {
            // PDF文件保存地址
            String outPath = pdfPath;

            String pdfPath = outPath+name+System.currentTimeMillis()+".pdf";

            File pdfFile = new File(pdfPath);
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfFile);
            // 创建文档
            Document doc = new Document();
            doc.setMargins(0,0,0,0);
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            doc.open();
            // 读取图片流
            BufferedImage img;
            // 实例化图片
            Image image;

            // 准备遍历前端传过来的图片文件
            File[] files = imageFiles;

            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png")
                        || file1.getName().endsWith(".jpg")
                        || file1.getName().endsWith(".jpeg")
                        || file1.getName().endsWith(".tif")) {
                    // 读取图片流
                    img = ImageIO.read(file1);
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    // 实例化图片
                    image = Image.getInstance(file1.getPath());
                    // 添加图片到文档
                    doc.newPage();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
