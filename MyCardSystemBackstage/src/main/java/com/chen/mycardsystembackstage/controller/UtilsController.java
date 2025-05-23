package com.chen.mycardsystembackstage.controller;

import cn.hutool.crypto.SmUtil;
import com.chen.mycardsystembackstage.entity.VO.UtilsPdfVo;
import com.chen.mycardsystembackstage.utils.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private YiYanUtils yiYanUtils;
    @Autowired
    private VideoUtils videoUtils;
    @Autowired
    private SportUtils sportUtils;
    @Resource
    private Sm3Utils sm3Utils;

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

    /**
     * 获取每日一言
     */
    @GetMapping("/getYiyan")
    public Object getYiyan(){
        return yiYanUtils.sendRequestWithHttpClient();
    }

    /**
     * 获取历史上的今天
     */
    @GetMapping("/getTodayForHistory")
    public Object getTodayForHistory(){
        return yiYanUtils.getHistory();
    }

    /**
     * 获取抖音视频
     * @return
     */
    @GetMapping("/getDy")
    public Object getDy(){
        return videoUtils.getVideoForDy();
    }

    /**
     * 获取每日NBA数据
     * @return
     */
    @GetMapping("/getNba")
    public Object getNba(){
        return sportUtils.getNba();
    }

    @GetMapping("/getSm3")
    public String getSm3(){
        String appCode = "ycJqGD";
        String appSecretKey = "770df23d-ecee-4087-aa04-f5ecb190707b";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(UUID.randomUUID());

        String signKey = appCode + appSecretKey + requestId + timestamp;
        System.out.println(signKey);
        String digestHex = SmUtil.sm3(signKey);
        System.out.println(digestHex);
        return digestHex;
    }
}
