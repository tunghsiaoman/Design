package com.design.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * @File RString.java
 * @Date: 14-6-4
 * @Author dongjw
 * @Description
 */
public final class MyTime {

    /**
     * 格式化时间
     *
     * @return
     */
    public static String getFormatDate() {
        return getFormatDate(null, null, null);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        return getFormatDate(date, null);
    }

    /**
     * 格式化时间
     *
     * @param format
     * @return
     */
    public static String getFormatDate(String format) {
        return getFormatDate(null, format);
    }

    public static String getFormatDate(Date date, String format) {
        return getFormatDate(date, format, null);
    }

    public static String getFormatDate(String format, Locale locale) {
        return getFormatDate(null, format, locale);
    }

    /**
     * 通过时间戳获取时间字符串
     *
     * @param currentTime
     * @param f
     * @return
     */
    public static String getFormatDateByCurrentTime(Long currentTime, String f) {
        SimpleDateFormat format = new SimpleDateFormat(f);
        return format.format(currentTime);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDate(Date date, String format, Locale locale) {
        if (date == null)
            date = new Date();
        if (locale == null)
            locale = Locale.CHINA;
        if (!StringUtils.isNotEmpty(format))
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        return formatter.format(date);
    }

    /**
     * 字符转换为日期类型
     *
     * @param source
     * @param format
     * @return
     */
    public static Date formatStrToDate(String source, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 格式化星期
     *
     * @return
     */
    public static String getFormatWeek() {
        return getFormatWeek(new Date());
    }

    /**
     * 格式化星期
     *
     * @param date
     * @return
     */
    public static String getFormatWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int curr = cal.get(Calendar.DAY_OF_WEEK);
        String week = "星期六";
        switch (curr) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            default:
                week = "星期六";
                break;
        }
        return week;
    }

    /**
     * 计算日期
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date calculateDate(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * 时间戳转换成时间格式
     *
     * @param timestamp
     * @return
     */
    public static Date getDateByTimestamp(Long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return cal.getTime();
    }

    /**
     * 将字符串类型转换为时间戳类型（马凯补充）
     *
     * @param strTime：Oracle中被转换为字符串Timestamp类型，例如：2013-8-30 13:02:11.000000
     * @return：返回java.sql.Timestamp类型
     */
    public static Timestamp getTimestampByString(String strTime) {
        //Timestamp ts = new Timestamp(System.currentTimeMillis()); //该初始化方法已废弃
        Timestamp ts;
        try {
            ts = Timestamp.valueOf(strTime);
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算日期，当前日期
     *
     * @param amount
     * @return
     */
    public static Date calculateDate(int amount) {
        return calculateDate(new Date(), amount);
    }

    /**
     * @return
     */
    public static String getTimePart() {
        String hour = MyTime.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        hour = hour.substring(11, 13);
        Integer h = MyString.getInteger(hour);
        String timePart = "";
        if (h > 0 && h <= 6) {
            timePart = "早上";
        }
        if (h > 6 && h <= 12) {
            timePart = "上午";
        }
        if (h > 12 && h <= 18) {
            timePart = "下午";
        }
        if (h > 18 && h <= 24) {
            timePart = "晚上";
        }
        return timePart;
    }

    /**
     * 计算相隔天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long OperationDays(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算相隔小时数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long OperationHours(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (60 * 60 * 1000);
    }

    /**
     * 计算相隔分钟数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long OperationMinutes(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (60 * 1000);
    }

    /**
     * 计算相隔秒数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long OperationSeconds(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (1000);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String getDateTimeStrVersion(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    /**
     * yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String getDateTimeStrC(Date date) {
        // SimpleDateFormat format = new
        // SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     *
     * @param date
     * @return
     */
    public static String getDateTimeStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return format.format(date);
    }

    /**
     * yyyy年MM月dd日 HH时
     *
     * @param date
     * @return
     */
    public static String getDateTimeStrD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时");
        return format.format(date);
    }

    /**
     * yyyy年MM月dd日 HH:mm
     *
     * @param date
     * @return
     */
    public static String getDateTimeStrm(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return format.format(date);
    }

    /**
     * 获得年份
     *
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    /**
     * 获得月份
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(date);
    }

    /**
     * 获得日
     *
     * @param date
     * @return
     */
    public static String getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return format.format(date);
    }

    /**
     * 获得时
     *
     * @param date
     * @return
     */
    public static String getHour(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH");
        return format.format(date);
    }

    /**
     * 获得分
     *
     * @param date
     * @return
     */
    public static String getMinute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("mm");
        return format.format(date);
    }

    /**
     * 获得秒
     *
     * @param date
     * @return
     */
    public static String getSecond(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("ss");
        return format.format(date);
    }

    /**
     * 获取相隔月数
     *
     * @param beforedate
     * @param afterdate
     * @return
     * @throws java.text.ParseException
     */
    @SuppressWarnings("deprecation")
    public static long getDistinceMonth(String beforedate, String afterdate) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        long monthCount = 0;
        try {
            Date d1 = d.parse(beforedate);
            Date d2 = d.parse(afterdate);

            monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth() - d1.getMonth();
            // dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

        } catch (ParseException e) {
            // throw e;
        }
        return monthCount;
    }

    /**
     * 获取相隔时间数
     *
     * @param beforeDateTime
     * @param afterDateTime
     * @return
     * @throws java.text.ParseException
     */
    public static long getDistinceDay(String beforeDateTime, String afterDateTime) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long dayCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);

            dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

        } catch (ParseException e) {
            throw e;
        }
        return dayCount;
    }

    /**
     * 获取相隔时间数
     *
     * @param beforeDateTime
     * @param afterDateTime
     * @return
     * @throws java.text.ParseException
     */
    public static long getDistinceTime(String beforeDateTime, String afterDateTime) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);
            timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

        } catch (ParseException e) {
            throw e;
        }
        return timeCount;
    }

    /**
     * 获取相隔分钟数
     *
     * @param beforeDateTime
     * @param afterDateTime
     * @return
     * @throws java.text.ParseException
     */
    public static long getDistinceMinute(String beforeDateTime, String afterDateTime) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);
            timeCount = (d2.getTime() - d1.getTime()) / (60 * 1000);

        } catch (ParseException e) {
            throw e;
        }
        return timeCount;
    }

    /**
     * 获取相隔毫秒数
     *
     * @param beforeDateTime
     * @param afterDateTime
     * @return
     * @throws java.text.ParseException
     */
    public static long getDistinceTimeMillis(String beforeDateTime, String afterDateTime) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeCount = 0;
        try {
            Date d1 = d.parse(beforeDateTime);
            Date d2 = d.parse(afterDateTime);
            timeCount = (d2.getTime() - d1.getTime());

        } catch (ParseException e) {
            throw e;
        }
        return timeCount;
    }

    /**
     * @param day
     * @param x
     * @return
     */
    public static Date addDate(String day, int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
        date = cal.getTime();
        cal = null;
        return date;
    }

    /**
     * 获得学期第几周
     *
     * @param startDate
     * @return
     */
    public static Integer getWeekNum(Date startDate) {
        Integer weekNum = 0;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            int curr = cal.get(Calendar.DAY_OF_WEEK);
            long days = MyTime.OperationDays(new Date(), cal.getTime()) + curr - 1;
            switch (curr) {
                case 1:
                    //星期日
                    days += 6;
                    break;
                case 2:
                    //星期一
                    break;
                case 3:
                    //星期二
                    days += 1;
                    break;
                case 4:
                    //星期三
                    days += 2;
                    break;
                case 5:
                    //星期四
                    days += 3;
                    break;
                case 6:
                    //星期五
                    days += 4;
                    break;
                default:
                    //星期六
                    days += 5;
                    break;
            }
            if (days > 0) {
                weekNum = weekNum + MyString.getInteger(days / 7);
                if (days % 7 > 0) {
                    weekNum = weekNum + 1;
                }
            }
//            if (weekNum > 1) {
//                weekNum -= 1;
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return weekNum;
        }
        return weekNum;
    }

    /**
     * 求某年下的第几个星期的日期 返回java.uilt.Date 类型日期 时间time为当前机器时间
     *
     * @param year 要获得的年
     * @param week 第几个星期
     * @param flag 是否是第一天还是最后一天,当为true时返回第一天,false则返回最后一天
     * @return java.uilt.Date 类型日期
     * @例如 getDayByWeek(2002, 2, true) 返回Tue Jan 08 14:11:57 CST 2002
     */
    public static Date getDayByWeek(int year, int week, boolean flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.get(Calendar.WEEK_OF_YEAR);
        cal.get(Calendar.WEEK_OF_MONTH);
        if (!flag)
            cal.setTimeInMillis(cal.getTimeInMillis() + 6 * 24 * 60 * 60 * 1000);
        return cal.getTime();
    }

    /**
     * 获取相隔时间数通过时间类型
     *
     * @param d1
     * @param d2
     * @return
     * @throws java.text.ParseException
     */
    public static long getDistinceDayByDate(Date d1, Date d2) {
        return (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 修订两个日期时间只差小于24小时的情形，同一天取值为0，跨天但小于24小时，取值为1
     *
     * @param beforeDateTime
     * @param afterDateTime
     * @return
     * @throws java.text.ParseException
     */
    public static long getDistinceDayMK(String beforeDateTime, String afterDateTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = dateFormat.parse(beforeDateTime);
            Date d2 = dateFormat.parse(afterDateTime);

            //定义日历
            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = Calendar.getInstance();
            calStart.setTime(d1);
            calEnd.setTime(d2);
            int days = MyTime.getDaysBetweenCalendarMK(calStart, calEnd); //修订跨年计算失效的问题
            return days;
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * 获取两个日历之间的实际天数，支持跨年
     *
     * @param calStart ：起始日历
     * @param calEnd   ：终止日历
     * @return ：返回两个日历间的天数之差
     */
    public static int getDaysBetweenCalendarMK(Calendar calStart, Calendar calEnd) {
        if (calStart.after(calEnd)) {
            Calendar calSwap = calStart;
            calStart = calEnd;
            calEnd = calSwap;
        }
        int days = calEnd.get(Calendar.DAY_OF_YEAR) - calStart.get(Calendar.DAY_OF_YEAR);
        int endYears = calEnd.get(Calendar.YEAR);
        if (calStart.get(Calendar.YEAR) != endYears) {
            calStart = (Calendar) calStart.clone();
            do {
                days += calStart.getActualMaximum(Calendar.DAY_OF_YEAR);
                calStart.add(Calendar.YEAR, 1);
            } while (calStart.get(Calendar.YEAR) != endYears);
        }
        return days;
    }

    //获得当天0点时间
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    //获得当天24点时间
    public static long getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    //获得本周一0点时间
    public static long getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTimeInMillis();
    }

    //获得本周日24点时间
    public static int getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (int) ((cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)) / 1000);
    }

    //获得本月第一天0点时间
    public static int getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得本月最后一天24点时间
    public static int getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return (int) (cal.getTimeInMillis() / 1000);
    }
}
