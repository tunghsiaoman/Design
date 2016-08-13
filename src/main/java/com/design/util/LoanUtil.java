package com.design.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 标相关的工具类
 *
 * @author xs
 * @history
 * @see
 * @since v1.0
 */
public class LoanUtil {

    private final static int YEAR_MONTH_COUNT = 12;
    private static BigDecimal interestRate;//月利率

    /**
     * 项目编号生成规则：
     * 企业简称(大写，例如：YDJ)  + 起息日期(20120101) + 项目编号(5位 00001)  + GR/QY
     */
    public static String createLoanCode(String companyName, Date releaseTime, String borrowType, Integer loanCount) {
        String randomNum = "";
        if (loanCount < 10) {
            randomNum = "0000" + loanCount;
        } else if (loanCount < 100) {
            randomNum = "000" + loanCount;
        } else if (loanCount < 1000) {
            randomNum = "00" + loanCount;
        } else if (loanCount < 10000) {
            randomNum = "0" + loanCount;
        } else {
            randomNum = "" + loanCount;
        }

        String loanCode = companyName.toUpperCase() + DateUtil.formatToYYYYMMDDStr(releaseTime) + randomNum;
        if ("1".equals(borrowType)) {
            loanCode = loanCode + "GR";
        } else if ("2".equals(borrowType)) {
            loanCode = loanCode + "QY";
        }

        return loanCode;
    }


    /**
     * 计算loanPhase的还款日期（dueDate）和baseDate之间相差的天数。
     * <br>如果dueDate在baseDate之前（逾期），则返回一个负数，其绝对值是相差的天数
     * <br>如果dueDate在baseDate之后（提前还款），则返回一个正数，其值是相差的天数
     * <br>如果两者是同一天，则返回0
     *
     * @param loanPhase
     * @param baseDate
     * @return
     * @author 柏松
     * @created 2012-11-12 下午11:31:12
     */
    /*public static int getDifferenceDays(LoanPhase loanPhase, Date baseDate) {
        return DateUtil.daysBetween(baseDate, loanPhase.getDueDate());
    }*/

    /**
     * 计算“投资收益率”，按照“等额本息法”计算
     *
     * @param investAmount 投资金额
     * @param interest     利息金额
     * @param termCount    还款期限（月数）
     * @return BigDecimal 年利率
     * @author daijian.song
     * @date 2013-1-11 下午2:39:36
     */
    public static BigDecimal getYearlyInterestRate(BigDecimal investAmount, BigDecimal interest, int termCount) {

        //近似利率,保留小数点后100位
        BigDecimal approximateInterestRate = interest.divide(investAmount, 100, BigDecimal.ROUND_HALF_UP);

        BigDecimal count = new BigDecimal(termCount);

        //每期所得本息（实际）
        BigDecimal termReturnAmount = (investAmount.add(interest)).divide(count, 100, BigDecimal.ROUND_HALF_UP);

        //利率（以近似利率为起始点，循环递增或递减，夹逼）
        interestRate = approximateInterestRate;

        BigDecimal monthlyReturnAmount = BigDecimal.ZERO;
        BigDecimal preMonthlyReturnAmount = BigDecimal.ZERO;

        BigDecimal monthlyABS = BigDecimal.ZERO;
        BigDecimal preMonthlyABS = BigDecimal.ZERO;
        monthlyReturnAmount = getMonthlyReturnAmount(investAmount, interestRate, termCount);

        int flag = monthlyReturnAmount.compareTo(termReturnAmount);

        if (flag == 0) {
            return interestRate.multiply(new BigDecimal(YEAR_MONTH_COUNT)).setScale(4, BigDecimal.ROUND_HALF_UP);
        } else if (flag == -1) {
            while (monthlyReturnAmount.compareTo(termReturnAmount) == -1) {
                interestRate = interestRate.add(new BigDecimal(0.00001));
                monthlyReturnAmount = getMonthlyReturnAmount(investAmount, interestRate, termCount);
                if (monthlyReturnAmount.compareTo(termReturnAmount) != -1) {

                    interestRate = interestRate.subtract(new BigDecimal(0.00001));
                    preMonthlyReturnAmount = getMonthlyReturnAmount(investAmount, interestRate, termCount);
                    monthlyABS = monthlyReturnAmount.subtract(termReturnAmount).abs();
                    preMonthlyABS = preMonthlyABS.subtract(termReturnAmount).abs();

                    if (monthlyABS.compareTo(preMonthlyABS) != 1) {
                        interestRate = interestRate.add(new BigDecimal(0.00001));
                    }
                    break;
                }
            }
        } else if (flag == 1) {
            while (monthlyReturnAmount.compareTo(termReturnAmount) == 1) {
                interestRate = interestRate.subtract(new BigDecimal(0.00001));
                monthlyReturnAmount = getMonthlyReturnAmount(investAmount, interestRate, termCount);
                if (monthlyReturnAmount.compareTo(termReturnAmount) != 1) {

                    interestRate = interestRate.add(new BigDecimal(0.00001));
                    preMonthlyReturnAmount = getMonthlyReturnAmount(investAmount, interestRate, termCount);
                    monthlyABS = monthlyReturnAmount.subtract(termReturnAmount).abs();
                    preMonthlyABS = preMonthlyReturnAmount.subtract(termReturnAmount).abs();

                    if (monthlyABS.compareTo(preMonthlyABS) != 1) {
                        interestRate = interestRate.subtract(new BigDecimal(0.00001));
                    }
                    break;
                }
            }
        }

        return interestRate.setScale(6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(YEAR_MONTH_COUNT)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 根据利率算出每期所得本息
     *
     * @param investAmount 投资额
     * @param interestRate 利率
     * @param termCount    还款期数
     * @return BigDecimal 每期所得本息
     * @author daijian.song
     * @date 2013-1-11 下午10:15:52
     */
    private static BigDecimal getMonthlyReturnAmount(BigDecimal investAmount, BigDecimal interestRate, int termCount) {

        //每期所得本息（计算）
        BigDecimal monthlyReturnAmount = BigDecimal.ZERO;

        //(1+interestRate)^termCount
        BigDecimal pow = BigDecimal.valueOf(Math.pow(interestRate.add(BigDecimal.ONE).doubleValue(), (double) termCount));

        //每月还款额=[贷款本金×月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数－1]
        monthlyReturnAmount = investAmount.multiply(interestRate).multiply(pow).divide((pow.subtract(BigDecimal.ONE)), 100, BigDecimal.ROUND_HALF_UP);

        return monthlyReturnAmount;
    }


    /**
     * 返回某个按月还款的Loan在第n期的还款时间。时间计算的起点是放标时间，即Loan.passTime <br>
     * <br>
     * 某标在6月3日投满，并经确认放款给借款者（都在6月3日
     * 23：59之前完成）。则此标第一个还款日应是7月2日。在7月2日晚23：59分或之前还款，都算是正常。（一个更典型的例子：6月1日放款的标，第一个还款日应是6月30日，而不是7月1日。） <br>
     * <br>
     * 如果在7月3日0：00之后还款，则视为逾期，如果还款人在7月4日凌晨1：00还款，逾期了25个小时，则视为逾期2天。
     *
     * @param loan
     * @param phaseNumber 期数，从1开始
     * @return 在第n期的还款时间
     */
    public static Date getMonthlyRepayDate(Date releaseTime, int phaseNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseTime);

        // 2013-3-5 public-1061
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.SECOND, -1);
        calendar.add(Calendar.MONTH, phaseNumber);
        return calendar.getTime();
    }

}
