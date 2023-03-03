package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.VO.UtilsPdfVo;
import com.chen.mycardsystembackstage.utils.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/imageToPdf")
    public void imageToPdf(@RequestParam(value = "multripartFile",required = false)MultipartFile[] files,
                           HttpServletRequest request
    ){
        pdfUtils.ImgToPdf((File[]) files,"name");
    }
}
