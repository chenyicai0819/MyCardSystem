package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.VO.UtilsPdfVo;
import com.chen.mycardsystembackstage.utils.PdfUtils;
import com.chen.mycardsystembackstage.utils.QRCodeUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Project : MyCardSystem - UtilsController
 * Powered by GeorgeChen On 2023-03-03 14:59:50
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@RestController
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    private PdfUtils pdfUtils;
    @Autowired
    private QRCodeUtils qrCodeUtils;

    @PostMapping("/imageToPdf")
    public void imageToPdf(@RequestParam(value = "multripartFile",required = false)MultipartFile[] files,
                           HttpServletRequest request
    ){
        pdfUtils.ImgToPdf((File[]) files,"name");
    }
    @PostMapping("/createQrCode")
    public void createQrCode(HttpServletRequest request, HttpServletResponse response,
            @ApiParam("二维码包含的内容，可直接写文字，链接需要是完整的网址") @RequestParam String link,
                             @ApiParam("小图") @RequestParam(required = false) MultipartFile file,
                             @ApiParam("高度") @RequestParam(required = false,defaultValue = "0") int height,
                             @ApiParam("宽度") @RequestParam(required = false,defaultValue = "0") int width
                             ){
        if (file!= null){
            qrCodeUtils.create(link,response,file);
        } else if (height != 0 && width != 0) {
            qrCodeUtils.create(link,height,width,response);
        } else {
            qrCodeUtils.create(link,response);
        }

    }
}
