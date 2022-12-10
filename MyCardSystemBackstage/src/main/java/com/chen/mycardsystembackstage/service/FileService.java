package com.chen.mycardsystembackstage.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface FileService {
    JSONObject uploadFileController(MultipartFile multipartFile,int id) throws IOException;
    public void downloadModel(HttpServletResponse response, int id);
}
