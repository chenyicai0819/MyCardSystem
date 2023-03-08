package com.chen.mycardsystembackstage.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Project : MyCardSystem - SportUtils
 * Powered by GeorgeChen On 2023-03-08 16:29:03
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 运动数据接口工具类
 **/
@Component
public class SportUtils {

    /**
     * 获取NBA数据（免费，一天只能50次）
     * @return
     */
    public Object getNba(){
        try {
            String url = "http://apis.juhe.cn/fapig/nba/query?key=f17d24ab4ed56ff52e88cbb3e4312bbc";
            String o= null;
            o = HttpClientUtil.get(url);
            JSONObject jsonObject = JSON.parseObject(o);
            JSONObject result =JSON.parseObject(jsonObject.getString("result"));
            System.out.println(result);
            JSONArray matchs = result.getJSONArray("matchs");
            StringBuffer nbas= new StringBuffer();
            for(int i=0; i<matchs.size(); i++){
                JSONObject match = (JSONObject) matchs.get(i);
                String week = match.getString("week");
                String date = match.getString("date");
                String games = match.getString("list");
                JSONArray gameJsons = match.getJSONArray("list");
                StringBuffer sb = new StringBuffer();
                sb.append(date+week+":").append("\r\n");
                for (int j = 0; j < gameJsons.size(); j++) {
                    JSONObject game = (JSONObject) gameJsons.get(j);
                    String timeStart = game.getString("time_start");
                    String team1 = game.getString("team1");
                    String team1Score = game.getString("team1_score");
                    String team2 = game.getString("team2");
                    String team2Score = game.getString("team2_score");
                    String statusText = game.getString("status_text");
                    sb.append(timeStart).append(" ").append(statusText).append(" ")
                            .append(team1).append(":").append(team1Score)
                            .append("——")
                            .append(team2).append(":").append(team2Score);
                    sb.append("\r\n");
                }
                nbas.append(sb);
                sb.delete(0,sb.toString().length()-1);
            }
            return nbas.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
