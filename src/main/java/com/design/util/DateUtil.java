package com.design.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xs
 * @history
 * @see
 * @since v1.1
 */
public class DateUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 将时间转成"yyyyMMdd"格式的字符串
     *
     * @param date 时间
     * @return
     * @author dongruijun
     * @created 2011-10-27 上午10:04:45
     */
    public static String formatToYYYYMMDDStr(final Date date) {
        if (date != null) {
            return DateFormat.getInstance("yyyyMMdd").format(date);
        } else {
            return "null";
        }

    }

    /**
     * 将时间转成"yyyy-MM-dd"格式的字符串
     *
     * @param date 时间
     * @return
     * @author dongruijun
     * @created 2011-10-27 上午10:04:45
     */
    public static String formatToYYYYMMDD(final Date date) {
        if (date != null) {
            return DateFormat.getInstance("yyyy-MM-dd").format(date);
        } else {
            return "null";
        }

    }

    public static Date formatToDayByYYYYMMDDMMHH(final String str)
            throws ParseException {
        DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm");
        return format.parse(str);
    }

    public static Date formatToDayByYYYYMMDDMMHHmm(final String str)
            throws ParseException {
        DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        return format.parse(str);
    }

    public static String formatToYYYYMMDDMMHHSS(final Date date) {
        DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 返回一个二维数组，单位分别是月和日，代表两个Date之差。 <br>
     * 本方法忽略小时和分钟。 <br>
     * <br>
     * 例： <br>
     * 1，2012年6月1日到2012年6月3日，返回值是[0,2] （2天） <br>
     * 2，2012年6月15日到2012年8月4日，返回值是[1,20] （1个月零20天） <br>
     * 3，2011年11月3日到2013年1月14日，返回值是[14,11] （14个月零11天）
     *
     * @param olderDate 较早的日期
     * @param newerDate 较晚的日期
     * @return
     * @throws IllegalPlatformAugumentException 当olderDate晚于newerDate时
     * @author 宋代建
     * @created 2012-11-12 下午10:25:55
     */
    public static int[] getDateDifferenceInMonthAndDay(final Date olderDate,
                                                       final Date newerDate) throws Exception {
        int[] differenceInMonthAndDay = new int[2];
        int years = 0;
        int months = 0;
        int days = 0;

        Calendar older = Calendar.getInstance();
        Calendar newer = Calendar.getInstance();
        older.setTime(olderDate);
        newer.setTime(newerDate);

        if (olderDate.getTime() > newerDate.getTime()) {
            throw new Exception();
        } else {
            years = newer.get(Calendar.YEAR) - older.get(Calendar.YEAR);
            if (years >= 0) {

                months = newer.get(Calendar.MONTH) - older.get(Calendar.MONTH) + 12
                        * years;
                older.add(Calendar.MONTH, months);
                days = newer.get(Calendar.DATE) - older.get(Calendar.DATE);

                if (days < 0) {
                    months = months - 1;
                    older.add(Calendar.MONTH, -1);
                }

                days = daysBetween(newer.getTime(), older.getTime());
                differenceInMonthAndDay[0] = months;
                differenceInMonthAndDay[1] = days;
            }

        }
        return differenceInMonthAndDay;
    }

    /**
     * 取两个Date之间的天数差<br>
     * <br>
     * 例：newerDate是6月3日，olderDate是5月31日，则应返回3 <br>
     * 一个更极端的列子：newerDate是6月3日 00:01，olderDate是6月2日
     * 23:59，则应返回1，说明相差一天，即便实际上只差2分钟 <br>
     * 此代码来自网上 <br>
     * http://blog.csdn.net/rmartin/article/details/1452867
     *
     * @param newerDate
     * @param olderDate
     * @return
     * @created 2012-11-13 上午12:47:34
     */
    public static int daysBetween(final Date newerDate, final Date olderDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(newerDate);
        cReturnDate.setTime(olderDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return millisecondsToDays(intervalMs);
    }

    /**
     * 用于天数比较（常用）
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetweenInt(Date smdate, Date bdate) throws ParseException {

        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    private static int millisecondsToDays(final long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    private static void setTimeToMidnight(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     * 获取当天的起始日期
     *
     * @return
     * @author 董小满
     * @since 2014-05-19
     */
    public static Long getStartTime() {

        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取当天的结束日期
     *
     * @return
     * @author 董小满
     * @since 2014-05-19
     */
    public static Long getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 获取两个日期相差月份
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     * @author byxing
     */
    public static int getMonthSpace(Date date1, Date date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(sdf.format(date1)));
        c2.setTime(sdf.parse(sdf.format(date2)));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    /**
     * 根据日期输出month个月后的日期
     * 例如： date=2014-01-01  month=3  返回：2014-03-01
     *
     * @return
     * @author byxing
     */
    public static Date getMonthDate(Date date, int month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(sdf.parse(sdf.format(date)));
        c1.add(Calendar.MONTH, month);
        return c1.getTime();
    }

    public static void main(String[] args) {
        Date date = new Date();
        Date date1;
        try {
            date1 = getMonthDate(date, 3);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ;

            System.out.println(sdf.format(date1));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
