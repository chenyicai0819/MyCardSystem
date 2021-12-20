package com.chen.mycardsystembackstage.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.utils
 * @date 2021/12/7 20:15
 * @since 1.0
 */
@Component
public class AuthUtil {

    public static final String APPID = "wx45c1428e5584fcdb";
    public static final String APPSECRET = "98174450eb706ada330f37e646be85d5";

    public JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject jsonObject = null;
        //首先初始化HttpClient对象
        DefaultHttpClient client = new DefaultHttpClient();
        //通过get方式进行提交
        HttpGet httpGet = new HttpGet(url);
        //通过HTTPclient的execute方法进行发送请求
        HttpResponse response = client.execute(httpGet);
        //从response里面拿自己想要的结果
        HttpEntity entity = response.getEntity();
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            // jsonObject = JSONObject.fromObject(result);
            jsonObject= JSON.parseObject(result);
        }
        //把链接释放掉
        httpGet.releaseConnection();
        return jsonObject;
    }
}
