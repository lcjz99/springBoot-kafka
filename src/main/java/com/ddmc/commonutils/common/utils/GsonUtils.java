package com.ddmc.commonutils.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2018  
 * Company:		叮咚买菜
 * Author:		lichao
 * Version:		1.0  
 * Create at:	2018年7月29日 下午9:27:06  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
public class GsonUtils {

	private static Gson gson = new Gson();

	private static GsonBuilder gsonBuilder = new GsonBuilder();

	/**
	 * 对象转json字符
	 * 
	 * @param object
	 * @return
	 */
	public static String getJson(Object object) {
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		return gsonBuilder.create().toJson(object);
	}

	/**
	 * 对象转json字符
	 * 
	 * @param object
	 * @param date
	 * @return
	 */
	public static String getJson(Object object, String dateFormat) {
		return gsonBuilder.setDateFormat(dateFormat).create().toJson(object);
	}

	/**
	 * json转对象
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		return gson.fromJson(json, type);
	}
}
