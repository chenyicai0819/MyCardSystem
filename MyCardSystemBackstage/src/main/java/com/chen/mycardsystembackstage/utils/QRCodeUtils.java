package com.chen.mycardsystembackstage.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

/**
 * Project : MyCardSystem - QRCodeUtils
 * Powered by GeorgeChen On 2023-03-07 15:41:03
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 二维码工具类
 *
 **/
@Component
public class QRCodeUtils {

    private static String QRCodePath = "D:\\mycard\\qrcode"+System.currentTimeMillis()+".jpg";

    /**
     * 创建二维码
     * @param link
     */
    public void create(String link,HttpServletResponse response){
        QrCodeUtil.generate(link, 100, 100, FileUtil.file(QRCodePath));
        download(QRCodePath,response);
    }

    /**
     * 创建二维码，自定义宽高
     * @param link
     * @param height
     * @param width
     * @param response
     */
    public void create(String link,int height,int width,HttpServletResponse response){
        QrConfig config = new QrConfig(300, 300);
        QrCodeUtil.generate(link, width, height, FileUtil.file(QRCodePath));
        download(QRCodePath,response);
    }

    /**
     * 生成带有小图的二维码
     * @param link 内容
     * @param response 输出流
     * @param file 小图
     */
    public void create(String link, HttpServletResponse response, MultipartFile file){
        QrCodeUtil.generate(link,QrConfig.create().setImg((File) file),FileUtil.file(QRCodePath));
        download(QRCodePath,response);
    }



    private void download(String downUrl, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/jpeg");

        try {
            File file = new File(downUrl);
            if (!file.exists()) {
                response.sendError(404, "File not found!");
                return ;
            }
            long fileLength = file.length();
            response.setHeader("Content-Length", String.valueOf(fileLength));
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
