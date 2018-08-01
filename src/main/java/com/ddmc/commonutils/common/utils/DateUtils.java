package com.ddmc.commonutils.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		叮咚买菜
 * Author:		lichao
 * Version:		1.0  
 * Create at:	2018年7月29日 下午9:26:53  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
public class DateUtils {

	/**
	 * 格式推出时间
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static Date str2Date(String format, String date) {
		if (StringUtils.isBlank(format) || StringUtils.isBlank(date)) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = null;
		try {
			simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 推算时间
	 * 
	 * @param date
	 * @param amount
	 * @return Date
	 */
	public static Date rollDate(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return cal.getTime();
	}

	/**
	 * 推算时间按小时
	 * 
	 * @param date
	 * @param hour
	 * @return Date
	 */
	public static Date rollHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}

	/**
	 * 比较日期时间
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean afterDate(Date d1, Date d2) {
		return d1.after(d2);
	}

	/**
	 * 得到当天最早的时间
	 * 
	 * @return
	 */
	public static Date getNowDate() {
		Date d = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(d);
		calender.set(Calendar.HOUR, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		return calender.getTime();
	}

	/**
	 * yyyy-M-d -> yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String covertPageDate(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return dateStr;
		}
		String[] str = StringUtils.split(dateStr, "-");
		StringBuffer sb = new StringBuffer();
		sb.append(str[0] + "-");
		if (str[1].length() == 1) {
			sb.append("0" + str[1]);
		} else {
			sb.append(str[1]);
		}
		sb.append("-");
		if (str[2].length() == 1) {
			sb.append("0" + str[2]);
		} else {
			sb.append(str[2]);
		}

		return sb.toString();
	}

	/**
	 * 获取当前开始日期
	 * 
	 * @return
	 */
	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 到分钟 不到1分钟返回空字符串""
	 * 
	 * @param ms
	 * @return
	 */
	public static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (minute > 0) {
			sb.append(minute + "分钟");
		}
		return sb.toString();
	}

	/**
	 * 到小时 不到1小时返回空字符串""
	 * 
	 * @param ms
	 * @return
	 */
	public static String formatTime2(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		return sb.toString();
	}

}
