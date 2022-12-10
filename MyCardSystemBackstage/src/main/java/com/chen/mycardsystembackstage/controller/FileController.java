package com.chen.mycardsystembackstage.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.mycardsystembackstage.service.*;
import com.chen.mycardsystembackstage.utils.StringToUtf8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public JSONObject uploadFileController(@RequestParam(value = "multipartFile",required = false) MultipartFile multipartFile,int id) throws IOException {
//        System.out.println("调用批量上传方法");
//        System.out.println(id);
        return fileService.uploadFileController(multipartFile,id);
    }
}
