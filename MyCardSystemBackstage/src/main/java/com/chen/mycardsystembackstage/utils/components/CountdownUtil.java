package com.chen.mycardsystembackstage.utils.components;

import cn.hutool.core.date.DateUtil;
import com.chen.mycardsystembackstage.entity.Holidays;
import com.chen.mycardsystembackstage.mapper.component.HolidaysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Project : MyCardSystem - CountdownUtil
 * Powered by GeorgeChen On 2023-03-10 14:58:19
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 倒计时组件
 **/
@Component
public class CountdownUtil {
    @Autowired
    private HolidaysMapper holidaysMapper;

    /**
     * 节假日倒计时
     */
    public void holidays(){
        // 获取到所有的节日
        List<Holidays> holidaysList = holidaysMapper.selectList(null);
        int year = DateUtil.thisYear();
        int month = DateUtil.thisMonth()+1;
        int day = DateUtil.thisDayOfMonth();
        String date = year+"-"+month+"-"+day;


    }

    /**
     * 下班倒计时
     */
    public void offWork(){
        String year = String.valueOf(DateUtil.thisYear());
        String month = (DateUtil.thisMonth()+1) > 9 ? String.valueOf(DateUtil.thisMonth()+1) : "0"+(DateUtil.thisMonth()+1);
        String day = DateUtil.thisDayOfMonth() > 9 ? String.valueOf(DateUtil.thisDayOfMonth()) : "0"+DateUtil.thisDayOfMonth();

        String offTime = "18:30";
        String date = year+"-"+month+"-"+day+" "+offTime+":00";
        System.out.println(date);
        System.out.println(dateToStamp(date));

    }

    private String dateToStamp(String s) {
        String res;
        //设置时间模版
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
