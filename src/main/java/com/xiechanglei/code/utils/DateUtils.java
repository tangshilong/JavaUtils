package com.xiechanglei.code.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	public static SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 以"yyyy-MM-dd HH:mm:ss" 格式将date转化成String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return DEFAULT_SDF.format(date);
	}

	/**
	 * 以指定格式将date转化成String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 以 "yyyy-MM-dd HH:mm:ss" 格式解析时间字符串
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String str) throws ParseException {
		return DEFAULT_SDF.parse(str);
	}

	/**
	 * 以指定格式解析时间字符串
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String str, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(str);
	}

	/**
	 * 获取本月的第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		return getFirstDayOfMonth(new Date());
	}

	/**
	 * 获取指定月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.DAY_OF_MONTH, 1);
		toZeroTimeOfDay(instance);
		return instance.getTime();
	}

	/**
	 * 获取本月的最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		return getLastDayOfMonth(new Date());
	}

	/**
	 * 获取指定月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.MONTH, 1);
		instance.set(Calendar.DAY_OF_MONTH, 1);
		toZeroTimeOfDay(instance);
		instance.add(Calendar.MILLISECOND, -1);
		return instance.getTime();
	}

	/**
	 * 将calendar里面的时分秒归0
	 * 
	 * @param instance
	 */
	private static void toZeroTimeOfDay(Calendar instance) {
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * 指定日期往前的N天集合
	 */
	public static List<Date> getLastDays(int count, Date date) {
		List<Date> dates = new ArrayList<Date>();
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		for (int i = 0; i < count; i++) {
			instance.add(Calendar.DATE, -1);
			dates.add(instance.getTime());
		}
		return dates;
	}

	public static List<Date> getLastDays(int count) {
		return getLastDays(count, new Date());
	}

	/**
	 * 指定日期往后的N天集合
	 */
	public static List<Date> getNextDays(int count, Date date) {
		List<Date> dates = new ArrayList<Date>();
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		for (int i = 0; i < count; i++) {
			instance.add(Calendar.DATE, 1);
			dates.add(instance.getTime());
		}
		return dates;
	}

	public static List<Date> getNextDays(int count) {
		return getNextDays(count, new Date());
	}

	/**
	 * 是否是同一天
	 * 
	 * @return
	 */
	public static boolean isSameDay(Date day1, Date day2) {
		if (day1 == null || day2 == null) {
			return false;
		}
		long day1_d = day1.getTime() / (24 * 60 * 60 * 1000);
		System.out.println(day1_d);
		long day2_d = day2.getTime() / (24 * 60 * 60 * 1000);
		System.out.println(day2_d);
		return (day1_d - day2_d) == 0;
	}

	public static void main(String[] args) throws ParseException {
		boolean sameDay = isSameDay(parseDate("2016-07-02 01:12:12"), parseDate("2016-07-01 12:12:12"));
		System.out.println(sameDay);
	}

}
