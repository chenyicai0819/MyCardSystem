package com.chen.mycardsystembackstage.utils;

import com.chen.mycardsystembackstage.entity.SequenceBean;
import com.chen.mycardsystembackstage.generate.CycRedisGenerate;
import com.chen.mycardsystembackstage.generate.CycSnowFlakeGenerate;
import com.chen.mycardsystembackstage.generate.CycUuidGenerate;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

/**
 * @author George
 * @project Cyc-Sequence
 * @package com.chen.sequence.utils
 * @date 2022/10/12 10:19
 * @since 1.0
 * 序列号模板工具类
 */
@Component
public class TemplateUtils {
    static SequenceBean sequenceBean = new SequenceBean();

    static {
        try {
            Properties properties = new Properties();
            // 读取 sequence.properties 中的内容
            InputStream in = CommonUtils.readResourceFile("sequence.properties");
            // 将所读取的内容加载到配置中
            properties.load(in);
            sequenceBean.setSequenceEngine(properties.getProperty("sequenceEngine"));
            sequenceBean.setSequenceType(properties.getProperty("sequenceType"));
            sequenceBean.setKeyName(properties.getProperty("keyName"));
            sequenceBean.setSeLength(Integer.parseInt(properties.getProperty("seLength")));
            sequenceBean.setDelTime(Long.parseLong(properties.getProperty("delTime")));
            sequenceBean.setKeyPrefix(properties.getProperty("keyPrefix"));
            sequenceBean.setEndText(Long.valueOf(properties.getProperty("endText")));
            sequenceBean.setBeginText(Long.valueOf(properties.getProperty("beginText")));
            sequenceBean.setTemplate(properties.getProperty("template"));
            sequenceBean.setFillText(Integer.parseInt(properties.getProperty("fillText")));
            sequenceBean.setIntervalNum(Integer.parseInt(properties.getProperty("intervalNum")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 传入生成的模板的工具类
     * @param sequenceBean 用户传入的 模板类型 等数据
     * @return 返回一个实际的序列号前缀
     */
    public String setTemplate(SequenceBean sequenceBean){
        // 首先拆分字符串
        String[] sts = splitString(sequenceBean.getTemplate());

        // 然后对模板内容进行拼接
        StringBuffer sb = new StringBuffer();
        sb.append(sts[0]);
        for (int i = 1; i < sts.length; i++) {
            sb.append(getTextFromTemplate(sts[i],sequenceBean));
        }
        return sb.toString();
    }

    /**
     * 拆分字符串
     * @param template 用户传入的 模板类型
     * @return 通过拆分后的模板值，根据模板的值 生成对应的序列
     */
    public String[] splitString(String template){
        // 对传入的模板进行拆分
        String[] sts = template.split("\\{");

        for (int i = 1; i < sts.length; i++) {
            sts[i]=sts[i].substring(0,sts[i].length()-1);
        }
        // 返回拆分后的结果
        return sts;
    }

    public String getTextFromTemplate(String keyword,SequenceBean sequenceBean){
        // 首先获取东八区时间
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        String out = null;
        if ("yyyy".equals(keyword)){
            // 获取年
            out = String.valueOf(calendar.get(Calendar.YEAR));
        } else if ("yy".equals(keyword)){
            // 获取两位的年
            out = String.valueOf(calendar.get(Calendar.YEAR)).substring(2,4);
        } else if ("MM".equals(keyword)) {
            // 获取月份
            out = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        } else if ("dd".equals(keyword)) {
            // 获取天数
            out = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        } else if ("HH".equals(keyword)) {
            // 获取小时数
            out = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        } else if ("mm".equals(keyword)) {
            // 获取分钟数
            out = String.valueOf(calendar.get(Calendar.MINUTE));
        } else if ("ss".equals(keyword)) {
            // 获取秒数
            out = String.valueOf(calendar.get(Calendar.SECOND));
        } else if ("SEQ".equals(keyword)) {
            // 调用生成序列号的方法生成序号
            if ("REDIS".equals(sequenceBean.getSequenceEngine())){
                CycRedisGenerate cycRedisGenerate = new CycRedisGenerate();
                out = cycRedisGenerate.getRedisSequence(sequenceBean);
            } else if ("SF".equals(sequenceBean.getSequenceEngine())) {
                CycSnowFlakeGenerate cycSnowFlakeGenerate = new CycSnowFlakeGenerate();
                out = String.valueOf(cycSnowFlakeGenerate.nextId());
            } else if ("UUID".equals(sequenceBean.getSequenceEngine())){
                CycUuidGenerate cycUuidGenerate = new CycUuidGenerate();
                out = cycUuidGenerate.getUuid(sequenceBean);
            }
        }
        return out;
    }

    /**
     * 获取序列号
     * @return
     */
    public String getSequence(){
        CycRedisGenerate cycRedisGenerate = new CycRedisGenerate();
        TemplateUtils templateUtils = new TemplateUtils();
        return templateUtils.setTemplate(sequenceBean);
    }
}
