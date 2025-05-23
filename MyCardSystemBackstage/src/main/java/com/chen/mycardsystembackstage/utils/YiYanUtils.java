package com.chen.mycardsystembackstage.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Project : MyCardSystem - YiYanUtils
 * Powered by GeorgeChen On 2023-03-07 17:18:28
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 每日一言API调用
 **/
@Component
public class YiYanUtils {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public JSONObject sendRequestWithHttpClient() {
        try {
            String url = "https://v1.hitokoto.cn?c=i";
            Object o= null;
            o = doGetPost(url,"GET",null);
            JSONObject jsonObject = JSON.parseObject(o.toString());
            String hitokoto = jsonObject.getString("hitokoto");
            String message = jsonObject.getString("hitokoto")+"   ————"+jsonObject.getString("from")+"("+jsonObject.getString("from_who")+")";
            System.out.println(message);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取历史上的今天（免费API一天只能用50次）
     * @return
     */
    public Object getHistory(){
        try {
            int month = DateUtil.thisMonth()+1;
            int day=DateUtil.thisDayOfMonth();
            String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=6176e18c7631e130bcf3588144c50c7e&date="+month+"/"+day;
            String o= null;
            o = HttpClientUtil.get(url);
            JSONObject jsonObject = JSON.parseObject(o);
            JSONArray results = jsonObject.getJSONArray("result");
            StringBuffer sb = new StringBuffer();
            sb.append("历史上的今天："+DateUtil.thisYear()+"年"+month+"月"+day+"日");
            for(int i=0; i<results.size(); i++){
                JSONObject result = (JSONObject) results.get(i);
                String title = result.getString("title");
                String date = result.getString("date");
                // System.out.println(date+":"+title);
                sb.append("\r\n");
                sb.append(date+":"+title);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送get和post请求
     * @param apiUrl
     * @param methodType
     * @param map
     * @return
     */
    private static String doGetPost(String apiUrl, String methodType, Map<String,Object> map){

        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        String result = null;
        try{
            // 创建连接
            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod(methodType) ;
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();

            if(methodType.equals("POST")){
                outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                outputStreamWriter.append(JSON.toJSONString(map));
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            // 读取响应
            inputStream = httpURLConnection.getInputStream();
            int length = (int) httpURLConnection.getContentLength();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] t = new byte[512];
                int len = 0;
                int pos = 0;
                while ((len = inputStream.read(t)) > 0) {
                    System.arraycopy(t, 0, data, pos, len);
                    pos += len;
                }
                result = new String(data, "UTF-8");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  result;
    }

    /**
     * 将对象转为Json
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
