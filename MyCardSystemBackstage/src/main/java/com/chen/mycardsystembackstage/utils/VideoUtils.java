package com.chen.mycardsystembackstage.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Project : MyCardSystem - videoUtils
 * Powered by GeorgeChen On 2023-03-08 14:44:59
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 视频工具类
 **/
@Component
public class VideoUtils {

    /**
     * 获取抖音视频工具类
     * @return
     */
    public JSONObject getVideoForDy(){
        String url = "https://zj.v.api.aa1.cn/api/video_dyv2";
        Object o= null;
        o = HttpClientUtil.doGetPost(url,"GET",null);
        JSONObject jsonObject = JSON.parseObject(o.toString());
        return jsonObject;
    }
}
