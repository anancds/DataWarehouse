/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/28 13:41
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>日期工具类</p>
 *
 * @author chendongsheng5 2016/12/28 13:41
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/28 13:41
 * @modify by reason:{方法名}:{原因}
 */
public class DateUtils {

	private static final Logger LOG = LogManager.getLogger(DateUtils.class);

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_All_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_append = "yyyyMMdd";
	public static final String DATE_FORMAT = "yyyy-MM";
	public static final int DEFAULT_STR_NUMBER = 2;
	private static final int[] DAY_OF_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * 对于时间的年、月、日、时、分、秒、毫秒位补
	 *
	 * @param coverNumber
	 * @param strSourse
	 * @return
	 */
	public static String coverString(int coverNumber, String strSourse) {
		int templength = coverNumber - strSourse.length();
		for (int i = 0; i < templength; i++) {
			strSourse = "0" + strSourse;
		}
		return strSourse;
	}

	/**
	 * 解析时间为字符串
	 *
	 * @param date
	 * @param pattern 字符串格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 字符串时间转成long类型时间
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static long transStr2long(String dateStr, String pattern) {
		if (StringUtils.hasText(dateStr)) {
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}

		return date == null ? 0 : date.getTime();
	}

	/**
	 * 向前推多少个日、月
	 *
	 * @param endDate
	 * @param num
	 * @param type
	 * @return
	 */
	public static String getStartData(Date endDate, int num, int type) {
		String startDate = null;

		if (null == endDate || num <= 0 || type <= 0) {
			return startDate;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);

		if (type == 1) { // 日
			calendar.add(Calendar.DATE, -num);
		} else if (type == 2) { // 月
			calendar.add(Calendar.MONTH, -num);
		} else if (type == 3) { // 年
			calendar.add(Calendar.YEAR, -num);
		}

		return new SimpleDateFormat(DATE_FORMAT).format(calendar.getTime());
	}

	/**
	 * 验证时间合法性
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static boolean isValidDate(String date, String pattern) {
		boolean convertSuccess = true;
		if (StringUtils.hasText(date) || StringUtils.hasText(pattern)) {
			convertSuccess = false;
		}

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(date);
		} catch (ParseException e) {
			convertSuccess = false;
		}

		return convertSuccess;
	}

	/**
	 * 加减时间 second>0,加秒 second<0,减秒
	 *
	 * @param dateStr 时间（yyyy-MM-dd HH:mm:ss）
	 * @return long
	 */
	public static long addOrMinusSecond(String dateStr, int millSecond) {

		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Date date = null;
		Calendar cal = null;
		try {
			date = sdf.parse(dateStr);
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MILLISECOND, millSecond);
		} catch (ParseException e) {
			LOG.error("时间转换异常 =>addOrMinusSecond", e);
			e.printStackTrace();
		}
		return cal.getTime().getTime();
	}

	/**
	 * 把毫秒转化成日期
	 *
	 * @param millSec(毫秒数)
	 * @return
	 */

	public static String transferLongToDate(long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Date date = new Date(millSec);
		return sdf.format(date);

	}

	/**
	 * 字符串时间转成Date类型时间
	 *
	 * @param dateStr
	 * @param pattern
	 * @return date
	 */
	public static Date strToDate(String dateStr, String pattern) {
		if (StringUtils.hasText(dateStr)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期相减/数据量=每条数据递增的毫秒数
	 *
	 * @param a
	 * @param b
	 * @return long
	 */
	public static long DatePare(String a, String b, long num) {
		Date a1 = strToDate(a, "yyyy-MM-dd HH:mm:ss");
		Date b1 = strToDate(b, "yyyy-MM-dd HH:mm:ss");
		long diff = a1.getTime() - b1.getTime();//这样得到的差值是微秒级别
		return diff / num;
	}

	public static void main(String[] args) {

		//    	Date a1=strToDate("2016-10-08 14:45:58", "yyyy-MM-dd HH:mm:ss");
		//  		Date b1=strToDate("2016-10-08 13:52:26", "yyyy-MM-dd HH:mm:ss");
		//  		long diff = (a1.getTime() - b1.getTime())/1000;//这样得到的差值是微秒级别
		//  		System.out.println((67393137-33304921)/diff);
		//
		//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
		//    	long a = System.currentTimeMillis();
		//    	System.out.println("a="+a);
		//		Date date = new Date(a);
		//		//获取时间区域
		//		System.out.println(sdf.getTimeZone().getDisplayName());
		//		System.out.println(sdf.format(date));
		//		try {
		//			System.out.println(sdf.parse(sdf.format(date)).getTime()+"");
		//		} catch (ParseException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		System.out.println("-----------------------------------");
		//		//重设时间区域
		//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		//		//获取时间区域
		//		System.out.println(sdf.getTimeZone().getDisplayName());
		//		System.out.println(sdf.format(date));
		//		try {
		//			System.out.println(sdf.parse(sdf.format(date)).getTime()+"");
		//		} catch (ParseException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//    	long a=addOrMinusSecond("2016-01-01 00:00:00", 5);
		//    	System.out.println(a);
		//    	long b=addOrMinusSecond("2016-01-01 00:00:00", 10);
		//    	System.out.println(b);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss.SSS");
		long startTime = DateUtils.transStr2long("2016-09-01 00:00:00", DateUtils.DEFAULT_DATE_All_FORMAT);
		System.out.println(startTime);
		Date aa = new Date(startTime);
		System.out.println(sdf.format(aa));
	}

	/**
	 * 取得指定天数后的时间
	 *
	 * @param date      基准时间
	 * @param dayAmount 指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addDay(Date date, int dayAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayAmount);
		return calendar.getTime();
	}

	/**
	 * 取得指定小时数后的时间
	 *
	 * @param date       基准时间
	 * @param hourAmount 指定小时数，允许为负数
	 * @return 指定小时数后的时间
	 */
	public static Date addHour(Date date, int hourAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hourAmount);
		return calendar.getTime();
	}

	/**
	 * 取得指定分钟数后的时间
	 *
	 * @param date         基准时间
	 * @param minuteAmount 指定分钟数，允许为负数
	 * @return 指定分钟数后的时间
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuteAmount);
		return calendar.getTime();
	}

	/**
	 * 比较两日期对象中的小时和分钟部分的大小.
	 *
	 * @param date        日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @param anotherDate 日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
	 */
	public static int compareHourAndMinute(Date date, Date anotherDate) {
		if (date == null) {
			date = new Date();
		}

		if (anotherDate == null) {
			anotherDate = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
		int minute1 = cal.get(Calendar.MINUTE);

		cal.setTime(anotherDate);
		int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
		int minute2 = cal.get(Calendar.MINUTE);

		if (hourOfDay1 > hourOfDay2) {
			return 1;
		} else if (hourOfDay1 == hourOfDay2) {
			// 小时相等就比较分钟
			return minute1 > minute2 ? 1 : (minute1 == minute2 ? 0 : -1);
		} else {
			return -1;
		}
	}

	/**
	 * 比较两日期对象的大小, 忽略秒, 只精确到分钟.
	 *
	 * @param date        日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @param anotherDate 日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
	 */
	public static int compareIgnoreSecond(Date date, Date anotherDate) {
		if (date == null) {
			date = new Date();
		}

		if (anotherDate == null) {
			anotherDate = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();

		cal.setTime(anotherDate);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		anotherDate = cal.getTime();

		return date.compareTo(anotherDate);
	}

	/**
	 * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
	 *
	 * @return 当前时间的字符串表示
	 */
	public static String currentDate2String() {
		return date2String(new Date());
	}

	/**
	 * 取得当前时间的字符串表示，格式为2006-01-10
	 *
	 * @return 当前时间的字符串表示
	 */
	public static String currentDate2StringByDay() {
		return date2StringByDay(new Date());
	}

	/**
	 * 取得今天的最后一个时刻
	 *
	 * @return 今天的最后一个时刻
	 */
	public static Date currentEndDate() {
		return getEndDate(new Date());
	}

	/**
	 * 取得今天的第一个时刻
	 *
	 * @return 今天的第一个时刻
	 */
	public static Date currentStartDate() {
		return getStartDate(new Date());
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10 20:56:30.756
	 *
	 * @param date 时间
	 * @return 时间字符串
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 按照指定格式把时间转换成字符串，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
	 *
	 * @param date    时间
	 * @param pattern 格式
	 * @return 时间字符串
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return (new SimpleDateFormat(pattern)).format(date);
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10
	 *
	 * @param date 时间
	 * @return 时间字符串
	 */
	public static String date2StringByDay(Date date) {
		return date2String(date, "yyyy-MM-dd");
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10 20:56
	 *
	 * @param date 时间
	 * @return 时间字符串
	 */
	public static String date2StringByMinute(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10 20:56:30
	 *
	 * @param date 时间
	 * @return 时间字符串
	 */
	public static String date2StringBySecond(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm:ss");
	}

	//    /**
	//     * 根据某星期几的英文名称来获取该星期几的中文数. <br>
	//     * e.g. <li>monday -> 一</li> <li>sunday -> 日</li>
	//     *
	//     * @param englishWeekName
	//     *            星期的英文名称
	//     * @return 星期的中文数
	//     */
	//    public static String getChineseWeekNumber(String englishWeekName) {
	//        if ("monday".equalsIgnoreCase(englishWeekName)) {
	//            return "一";
	//        }
	//
	//        if ("tuesday".equalsIgnoreCase(englishWeekName)) {
	//            return "二";
	//        }
	//
	//        if ("wednesday".equalsIgnoreCase(englishWeekName)) {
	//            return "三";
	//        }
	//
	//        if ("thursday".equalsIgnoreCase(englishWeekName)) {
	//            return "四";
	//        }
	//
	//        if ("friday".equalsIgnoreCase(englishWeekName)) {
	//            return "五";
	//        }
	//
	//        if ("saturday".equalsIgnoreCase(englishWeekName)) {
	//            return "六";
	//        }
	//
	//        if ("sunday".equalsIgnoreCase(englishWeekName)) {
	//            return "日";
	//        }
	//
	//        return null;
	//    }

	/**
	 * 根据指定的年, 月, 日等参数获取日期对象.
	 *
	 * @param year  年
	 * @param month 月
	 * @param date  日
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date) {
		return getDate(year, month, date, 0, 0);
	}

	/**
	 * 根据指定的年, 月, 日, 时, 分等参数获取日期对象.
	 *
	 * @param year      年
	 * @param month     月
	 * @param date      日
	 * @param hourOfDay 时(24小时制)
	 * @param minute    分
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute) {
		return getDate(year, month, date, hourOfDay, minute, 0);
	}

	/**
	 * 根据指定的年, 月, 日, 时, 分, 秒等参数获取日期对象.
	 *
	 * @param year      年
	 * @param month     月
	 * @param date      日
	 * @param hourOfDay 时(24小时制)
	 * @param minute    分
	 * @param second    秒
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hourOfDay, minute, second);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 取得某个日期是星期几，星期日是1，依此类推
	 *
	 * @param date 日期
	 * @return 星期几
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
	 *
	 * @param date 日期对象
	 * @return 该天的结束时间
	 */
	public static Date getEndDate(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	/**
	 * 取得一个月最多的天数
	 *
	 * @param year  年份
	 * @param month 月份，0表示1月，依此类推
	 * @return 最多的天数
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		if (month == 1 && isLeapYear(year)) {
			return 29;
		}
		return DAY_OF_MONTH[month];
	}

	/**
	 * 得到指定日期的下一天
	 *
	 * @param date 日期对象
	 * @return 同一时间的下一天的日期对象
	 */
	public static Date getNextDay(Date date) {
		return addDay(date, 1);
	}

	/**
	 * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
	 *
	 * @param date 日期对象
	 * @return 该天的起始时间
	 */
	public static Date getStartDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm:ss).
	 *
	 * @param date 日期对象
	 * @return 时间字符串, 格式为: HH:mm:ss
	 */
	public static String getTime(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm).
	 *
	 * @param date 日期对象
	 * @return 时间字符串, 格式为: HH:mm
	 */
	public static String getTimeIgnoreSecond(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	/**
	 * 判断是否是闰年
	 *
	 * @param year 年份
	 * @return 是true，否则false
	 */
	public static boolean isLeapYear(int year) {
		Calendar calendar = Calendar.getInstance();
		return ((GregorianCalendar) calendar).isLeapYear(year);
	}

	/**
	 * 把字符串转换成日期，格式为2006-01-10
	 *
	 * @param str 字符串
	 * @return 日期
	 */
	public static Date string2Date(String str) {
		return string2Date(str, "yyyy-MM-dd");
	}

	/**
	 * 按照指定的格式把字符串转换成时间，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
	 *
	 * @param str     字符串
	 * @param pattern 格式
	 * @return 时间
	 */
	public static Date string2Date(String str, String pattern) {
		if (ValidateUtils.isEmpty(str)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			// ignore
		}
		return date;
	}

	/**
	 * 把字符串转换成日期，格式为2006-01-10 20:56:30
	 *
	 * @param str 字符串
	 * @return 日期
	 */
	public static Date string2DateTime(String str) {
		return string2Date(str, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 取得一年中的第几周。
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取上周的指定星期的日期。
	 */
	public static Date getDateOfPreviousWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("wrong number!");
		}

		return getDateOfRange(dayOfWeek, -7);
	}

	/**
	 * 获取本周的指定星期的日期。
	 *
	 * @param dayOfWeek 星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 */
	public static Date getDateOfCurrentWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("wrong number!");
		}

		return getDateOfRange(dayOfWeek, 0);
	}

	/**
	 * 获取下周的指定星期的日期。
	 *
	 * @param dayOfWeek 星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 */
	public static Date getDateOfNextWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("wrong number!");
		}

		return getDateOfRange(dayOfWeek, 7);
	}

	private static Date getDateOfRange(int dayOfWeek, int dayOfRange) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + dayOfRange);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
