/*
 * Copyright (C) 2017 eKing Technology, Inc. All Rights Reserved.
 */

package com.chen.mycardsystembackstage.utils;

import com.chen.mycardsystembackstage.common.responsecode.CodeDefault;
import com.chen.mycardsystembackstage.utils.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 适合各种时间转换的 DateUtil
 * 后续改为 joda-time 来实现
 */
@Component
public class MyDateUtils {

    public final static String sdfyyyy = "yyyy";

    public final static String sdfyyyyMM = "yyyy-MM";

    public final static String sdfyyyyMMdd = "yyyy-MM-dd";

    public final static String sdfyyyyMMddHH = "yyyy-MM-dd HH";

    public final static String sdfyyyyMMddHHmm = "yyyy-MM-dd HH:mm";

    public final static String sdfyyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public final static String yyyyMMdd = "yyyyMMdd";

    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public final static String sdfMM = "MM";

    public final static String sdfdd = "dd";

    public final static String sdfHH = "HH";

    public final static String sdfmm = "mm";

    public final static String sdfss = "ss";

    public final static String sdfHHmm = "HH:mm";

    public final static String sdfHHmmss = "HH:mm:ss";

    /**
     * date 转化成 String
     *
     * @param date
     * @return
     */
    public static String toStringYMD(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMdd);
        return sdf.format(date);
    }

    /**
     * date 转化成 String
     *
     * @param date
     * @param format 自定义格式
     * @return
     */
    public static String toStringYMD(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }

    /**
     * date 转化成 String
     *
     * @param date
     * @return
     */
    public static String toStringYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMddHHmmss);
        return sdf.format(date);
    }

    public static String toStringHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfHHmmss);
        return sdf.format(date);
    }

    /**
     * string 转化成 date
     *
     * @param str
     * @return
     */
    public static Date toDateYMD(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMdd);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }

    public static Date toDateYM(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMM);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }

    /**
     * string 转化成 date
     *
     * @param str
     * @return
     */
    public static Date toDateYMDHMS(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMddHHmmss);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }

    /**
     * string 转化成 date
     *
     * @param str
     * @param format 格式自定义
     * @return
     */
    public static Date toDateFormat(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(CodeDefault.ILLEGAL_DATE_FORMAT, e);
        }
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(Integer year, Integer week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(Integer year, Integer week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(1);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 获得指定日期的前一天
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        return dayBefore;
    }

    public static Date stepMonth(String year, String month) {
        if (!"NA".equals(year) && !"NA".equals(month) && !year.contains("长") && !month.contains("长") && !"".equals(year) && !"".equals(month)) {
            Calendar c = Calendar.getInstance();
            if (year.length() < 4) {
                c.setTime(new Date());
                c.add(Calendar.MONTH, -(Integer.parseInt(year) * 12 + Integer.parseInt(month)));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if (month.length() < 2) {
                        month = "0" + month;
                    }
                    Date parse = sdf.parse(year + "-" + month + "-" + "00");
                    c.setTime(parse);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return toDateYM(toStringYMDHMS(c.getTime()));
        } else {
            return null;
        }


    }

    /**
     * 获得指定日期的后一天
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        //String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        int month = c.get(Calendar.MONTH) + 1;
        String monthStr = "";
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }
        int newDay = c.get(Calendar.DATE);
        String dayStr = "";
        if (newDay < 10) {
            dayStr = "0" + newDay;
        } else {
            dayStr = String.valueOf(newDay);
        }
        return c.get(Calendar.YEAR) + "年" + monthStr + "月" + dayStr + "日";
    }

    /**
     * 获取上一个月
     */
    public static String getPrevMonth(String yearAndMonth) {
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMM").parse(yearAndMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String prevMonth = dft.format(cal.getTime());
        return prevMonth;
    }

    /**
     * 获取下一个月
     */
    public static String getNextMonth(String yearAndMonth) {
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM").parse(yearAndMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String nextMonth = dft.format(cal.getTime());
        return nextMonth;
    }

    /**
     * 获取下一年
     */
    public static String getNextYear(String year) {
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy").parse(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.add(Calendar.YEAR, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy");
        String nextYear = dft.format(cal.getTime());
        return nextYear;
    }

    /**
     * 获取近num天的yyyy-MM-dd字符串
     */
    public static List<String> getDayString(Date date, int num) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMdd);
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        result.add(sdf.format(cal.getTime()));
        for (int i = 0; i < num - 1; i++) {
            cal.add(Calendar.DATE, -1);
            result.add(sdf.format(cal.getTime()));
        }
        return result;
    }

    /**
     * 比较两个日期是否在相差天数内
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @param num       相差天数
     * @return 在相差天数内返回false, 不在返回true(late了)
     */
    public static boolean isLate(Date beginDate, Date endDate, int num) {
        String beginString = toStringYMD(beginDate);
        List endStringList = getDayString(endDate, 7);
        if (endStringList.contains(beginString)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取两个日期的时间之差，返回相应的结果
     *
     * @param old
     * @param now
     * @return
     */
    public static String getDiffTime(long old, long now) {
        String diffTime;
        int day;
        if (now > old) {
            day = (int) ((now - old) / 1000 / 60 / 60 / 24);
        } else {
            day = (int) ((old - now) / 1000 / 60 / 60 / 24);
        }
        if (day > 365) {
            diffTime = "一年前";
        } else {
            if (day > 30) {
                diffTime = (day / 30) + "个月前";
            } else {
                if (day > 0) {
                    diffTime = day + "天前";
                } else {
                    int hours = (int) ((now - old) / 1000 / 60 / 60);
                    if (hours > 0) {
                        diffTime = hours + "小时前";
                    } else {
                        int minute = (int) ((now - old) / 1000 / 60);
                        if (minute == 0) {
                            diffTime = "刚刚";
                        } else {
                            diffTime = minute + "分钟前";
                        }
                    }
                }
            }
        }
        return diffTime;
    }

    public static String getAgeByBirthday(String date) {
        Date date1 = MyDateUtils.toDateYMD(date);
        String birthdayStr = MyDateUtils.toStringYMD(date1);
        if (StringUtils.isNotBlank(birthdayStr)) {
            String[] data = birthdayStr.split("-");
            Calendar birthday = new GregorianCalendar(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]));
            Calendar now = Calendar.getInstance();
            int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
            int month = now.get(Calendar.MONTH) + 1 - birthday.get(Calendar.MONTH);
            int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            if (day < 0) {
                month -= 1;
                now.add(Calendar.MONTH, -1);
                day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (month < 0) {
                month = (month + 12) % 12;
                year--;
            }
            StringBuffer tag = new StringBuffer();
            if (year > 0) {
                tag.append(year + "岁");
            }
            if (month > 0) {
                tag.append(month + "个月");
            }
            if (day > 0) {
                tag.append(day + "天");
            }
            if (year == 0 && month == 0 && day == 0) {
                tag.append("今日出生");
            }
            return String.valueOf(tag);
        } else {
            return "1岁";
        }
    }

    /**
     * 计算从date到endDate的年龄
     *
     * @param date
     * @param endDate
     * @return
     */
    public static String getAgeByBirthday(String date, Date endDate) {
        Date date1 = MyDateUtils.toDateYMD(date);
        String birthdayStr = MyDateUtils.toStringYMD(date1);
        if (StringUtils.isNotBlank(birthdayStr)) {
            String[] data = birthdayStr.split("-");
            Calendar birthday = new GregorianCalendar(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]));
            Calendar now = Calendar.getInstance();
            now.setTime(endDate);
            int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
            int month = now.get(Calendar.MONTH) + 1 - birthday.get(Calendar.MONTH);
            int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            if (day < 0) {
                month -= 1;
                now.add(Calendar.MONTH, -1);
                day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (month < 0) {
                month = (month + 12) % 12;
                year--;
            }
            StringBuffer tag = new StringBuffer();
            if (year > 0) {
                tag.append(year + "岁");
            }
            if (month > 0) {
                tag.append(month + "个月");
            }
            if (day > 0) {
                tag.append(day + "天");
            }
            if (year == 0 && month == 0 && day == 0) {
                tag.append("今日出生");
            }
            return String.valueOf(tag);
        } else {
            return "1岁";
        }
    }

    public static String getAgeByBirthdayNew(String yearStr, String monthStr, String dateStr, String date) {
        String[] data = date.split("-");
        Calendar birthday = new GregorianCalendar(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]));
        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, Integer.parseInt(yearStr));
        now.set(Calendar.MONTH, (Integer.parseInt(monthStr) - 1));
        now.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr));
        int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH) + 1 - birthday.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (day < 0) {
            month -= 1;
            now.add(Calendar.MONTH, -1);
            day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (month < 0) {
            month = (month + 12) % 12;
            year--;
        }
        StringBuffer tag = new StringBuffer();
        if (year > 0) {
            tag.append(year + "岁");
        }
        if (month > 0) {
            tag.append(month + "个月");
        }
        if (day > 0) {
            tag.append(day + "天");
        }
        if (year == 0 && month == 0 && day == 0) {
            tag.append("今日出生");
        }
        return String.valueOf(tag);
    }

    public static Float getAge(String yearStr) {
        Float result = null;
        if (yearStr.equals("今日出生")) {
            result = 1 / 12f;
        } else {
            String str = yearStr.replace("岁", ",").replace("个月", ",").replace("天", ",");
            String[] strs = str.split(",");
            if (yearStr.contains("岁") && yearStr.contains("个月") && yearStr.contains("天")) {
                result = Float.parseFloat(strs[0]) + Float.parseFloat(strs[1]) / 12;
            } else if (yearStr.contains("岁") && yearStr.contains("天")) {
                result = Float.parseFloat(strs[0]);
            } else if (yearStr.contains("岁") && yearStr.contains("个月")) {
                result = Float.parseFloat(strs[0]) + Float.parseFloat(strs[1]) / 12;
            } else if (yearStr.contains("个月") && yearStr.contains("天")) {
                result = Float.parseFloat(strs[0]) / 12;
            } else if (yearStr.contains("岁")) {
                result = Float.parseFloat(strs[0]);
            } else if (yearStr.contains("个月")) {
                result = Float.parseFloat(strs[0]) / 12;
            } else if (yearStr.contains("天")) {
                result = 1 / 12f;
            }
        }
        return result;
    }


    /**
     * 根据指定时间，通过计算 hour，得到新的时间字符串
     *
     * @param hour 正数未来的小时；负数为之前的小时
     * @param date 指定时间对象
     */
    public static String calendarForHour(int hour, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        String format = sdf.format(calendar.getTime());
        return format;
    }


    public static String extractYearMonthDayOfIdCard(String id) {
        String year = null;
        String month = null;
        String day = null;
        //正则匹配身份证号是否是正确的，15位或者17位数字+数字/x/X
        if (id.matches("^\\d{15}|\\d{17}[\\dxX]$")) {
            year = id.substring(6, 10);
            month = id.substring(10, 12);
            day = id.substring(12, 14);
        } else {
            System.out.println("身份证号码不匹配！");
            return null;
        }
        return year + "-" + month + "-" + day;
    }

    /**
     * 根据创建时间修改age_num的值
     *
     * @param date
     * @param createTime
     * @return
     */
    public static String getAgeByCreateTime(Date date, Date createTime) {
        String birthdayStr = MyDateUtils.toStringYMD(date);
        if (StringUtils.isNotBlank(birthdayStr)) {
            String[] data = birthdayStr.split("-");
            Calendar birthday = new GregorianCalendar(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            Calendar now = Calendar.getInstance();
            now.setTime(createTime);

            int day = now.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH);
            int month = now.get(Calendar.MONTH) + 1 - birthday.get(Calendar.MONTH);
            int year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            if (day < 0) {
                month -= 1;
                now.add(Calendar.MONTH, -1);
                day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (month < 0) {
                month = (month + 12) % 12;
                year--;
            }
            StringBuilder tag = new StringBuilder();
            if (year > 0) {
                tag.append(year).append("岁");
            }
            if (month > 0) {
                tag.append(month).append("个月");
            }
            if (day > 0) {
                tag.append(day).append("天");
            }
            if (year == 0 && month == 0 && day == 0) {
                tag.append("今日出生");
            }
            return String.valueOf(tag);
        } else {
            return "1岁";
        }
    }

    /**
     * 根据指定时间，通过计算 month，得到新的时间字符串
     *
     * @param month 正数未来的月份；负数为之前的月份
     * @param date  指定时间对象
     */
    public static String calendarForMonth(int month, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddHHmmss);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        String format = sdf.format(calendar.getTime());
        return format;
    }

    /**
     * 计算睡眠时长
     *
     * @param sleepTime 入睡时间
     * @param awakeTime 醒来时间
     * @return
     * @throws ParseException
     */
    public static String calSleepTime(String sleepTime, String awakeTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdfHHmm);
        Date sleepDate = simpleDateFormat.parse(sleepTime);
        Date awakeDate = simpleDateFormat.parse(awakeTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(awakeDate);
        calendar.add(Calendar.DATE, 1);
        awakeDate = calendar.getTime();
        long sleepDateTime = sleepDate.getTime();
        long awakeDateTime = awakeDate.getTime();

        int hour = (int) (awakeDateTime - sleepDateTime) / 1000 / 60 / 60;
        if (hour > 24) {
            hour -= 24;
        }
        int minute = (int) (awakeDateTime - sleepDateTime) / 1000 / 60 % 60;
        return hour + "小时" + minute + "分钟";
    }

    /**
     * 计算平均睡眠时间
     *
     * @param asleepNight         平时晚上睡着的时间
     * @param awakeMorning        平时早晨醒来的时间
     * @param asleepNightWeekend  周末晚上睡着的时间
     * @param awakeMorningWeekend 周末早晨醒来的时间
     * @return
     * @throws ParseException
     */
    public static String calAvgSleepTime(String asleepNight, String awakeMorning, String asleepNightWeekend, String awakeMorningWeekend) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdfHHmm);
        Date asleepNightDate = simpleDateFormat.parse(asleepNight);
        Date awakeMorningDate = simpleDateFormat.parse(awakeMorning);
        Date asleepNightWeekendDate = simpleDateFormat.parse(asleepNightWeekend);
        Date awakeMorningWeekendDate = simpleDateFormat.parse(awakeMorningWeekend);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(awakeMorningDate);
        calendar.add(Calendar.DATE, 1);
        awakeMorningDate = calendar.getTime();
        calendar.setTime(awakeMorningWeekendDate);
        calendar.add(Calendar.DATE, 1);
        awakeMorningWeekendDate = calendar.getTime();
        long asleepNightDateTime = asleepNightDate.getTime();
        long awakeMorningDateTime = awakeMorningDate.getTime();
        long asleepNightWeekendDateTime = asleepNightWeekendDate.getTime();
        long awakeMorningWeekendDateTime = awakeMorningWeekendDate.getTime();
        int hour = (int) ((awakeMorningDateTime - asleepNightDateTime) * 5 + (awakeMorningWeekendDateTime - asleepNightWeekendDateTime) * 2) / 7 / 1000 / 60 / 60;
        if (hour > 24) {
            hour -= 24;
        }
        int minute = (int) ((awakeMorningDateTime - asleepNightDateTime) * 5 + (awakeMorningWeekendDateTime - asleepNightWeekendDateTime) * 2) / 7 / 1000 / 60 % 60;
        return hour + "小时" + minute + "分钟";
    }

    /**
     * 将时间+8个小时再写入mongo
     *
     * @param date 日期
     * @return {@link Date}
     */
    public static Date getMongoDate(Date date) {
        if (date == null) {
            return null;
        }
        Date newDate = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 增加8个小时
            calendar.add(Calendar.HOUR_OF_DAY, 8);
            newDate = calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 获取一个时间段内平均拆分后的时间点
     *
     * @param beginTime
     * @param endTime
     * @param count
     * @return
     */
    public static List<Date> getSplitTimePoint(Date beginTime, Date endTime, Integer count) {
        if (count == 0) {
            return new ArrayList<>();
        }
        //两个时间点之间的毫秒差再加上一天的毫秒数
        long num = endTime.getTime() - beginTime.getTime() + 24 * 60 * 60 * 1000;//时间戳相差的毫秒数
        //每个时间段的毫秒数
        double timePeriod = (double) num / count;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);
        calendar.add(Calendar.MILLISECOND, (int) timePeriod / 2);
        List<Date> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(calendar.getTime());
            calendar.add(Calendar.MILLISECOND, (int) timePeriod);
        }
        return result;
    }

    /**
     * 通过出生日期获取年龄的整数，并计算处于哪个年龄段
     *
     * @param birthday yyyy-MM-dd
     * @return int
     */
    public static String getAgeGroup(Date birthday) {
        String ageGroup = null;
        Calendar from = Calendar.getInstance();
        from.setTime(birthday);
        Calendar to =  Calendar.getInstance();
        to.setTime(new Date());
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);

        int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
        if (month < 3){
            ageGroup = "<3月";
        }else if (month <= 5){
            ageGroup = "3-5月";
        }else if (month <= 8){
            ageGroup = "6-8月";
        }else if (month <= 11){
            ageGroup = "9-11月";
        }else if (month <= 17){
            ageGroup = "12-17月";
        }else if (month <= 23){
            ageGroup = "18-23月";
        }else if (month <= 29){
            ageGroup = "24-29月";
        }else if (month<= 35){
            ageGroup = "30-35月";
        }
        return ageGroup;
    }

    public static Date getNextDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        return calendar.getTime();
    }

    /**
     * 获取儿童月龄
     * @param birthday
     * @return
     */
    public static Integer getAgeMonth(Date birthday){
        if (birthday == null) {
            return null;
        }
        Calendar from = Calendar.getInstance();
        from.setTime(birthday);
        Calendar to =  Calendar.getInstance();
        to.setTime(new Date());
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);

        int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
        return month;
    }

    /**
     * 获取儿童的年龄（天数）
     * @param birthday
     * @return
     */
    public static Integer getAgeDays(Date birthday){
        if (birthday == null) {
            return null;
        }
        Date now = new Date();
        int ageDays = (int) ((now.getTime() - birthday.getTime()) / 1000 / 60 / 60 / 24);
        return ageDays;
    }
}