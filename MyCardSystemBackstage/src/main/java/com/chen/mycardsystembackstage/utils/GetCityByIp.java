package com.chen.mycardsystembackstage.utils;

import com.alibaba.fastjson.JSONObject;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.utils
 * @date 2022/7/9 12:59
 * @since 1.0
 */
@Component
public class GetCityByIp {

    /**
     * description 地址库查询
     *
     * @param ip 地址
     * @return java.lang.String
     * @version 1.0
     */
    public String getRealAddress(String ip) {
        String result = "";
        try {
            // 加载地址库
            ClassPathResource resource = new ClassPathResource("static/GeoLite2-City.mmdb");
            DatabaseReader reader = new DatabaseReader.Builder(resource.getInputStream()).build();
            InetAddress inetAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(inetAddress);

            // 库内获取不到的IP，访问ali的地域查询
            if (response.getSubdivisions().size() > 0) {
                // 获取所在国家
                String country = response.getCountry().getNames().get("ja");
                // 获取所在省份
                String province = response.getSubdivisions().get(0).getNames().get("zh-CN");
                // 获取所在城市
                String city = response.getCity().getNames().get("zh-CN");
                city = city == null ? "" : city;
                result = country + province + city;
            } else {
                result = getAlibaba(ip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * description ali地域查询
     *
     * @param ip ip地址
     * @return java.lang.String
     * @version 1.0
     */
    public String getAlibaba(String ip) throws IOException {
        Map map = new HashMap();
        map.put("ip", ip);
        map.put("accessKey", "alibaba-inc");
        String result = HttpClientUtil.post("http://ip.taobao.com/outGetIpInfo", map);
        Map valueMap = JSONObject.parseObject(result, Map.class);

        // 请求成功，解析响应数据
        if ("query success".equals(valueMap.get("msg"))) {
            Map<String, String> dataMap = (Map<String, String>) valueMap.get("data");
            String country = dataMap.get("country");
            String region = dataMap.get("region");
            String city = dataMap.get("city");
            return country + region + city;
        }
        return "";
    }


}
