package com.ddmc.commonutils.common.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.ddmc.commonutils.exception.sysexcetion.SystemException;

/**
 * @title 字符串工具类
 * @description 提供操作字符串的常用工具方法
 * @author Lincoln
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	static Map<String, String> weekName = new HashMap<String, String>();
	static {
		weekName.put("2", "星期一");
		weekName.put("3", "星期二");
		weekName.put("4", "星期三");
		weekName.put("5", "星期四");
		weekName.put("6", "星期五");
		weekName.put("7", "星期六");
		weekName.put("1", "星期日");
	}

	/**
	 * 定义星座与性格数组方法（发送生日短信时使用）
	 */
	private final static int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };
	private final static String[] xingzuoArr = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座",
			"处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };
	private final static String[] bewriteArr = new String[] { "踏实稳重", "知性敏锐", "浪漫善良", "热情耿直", "务实可靠", "聪明独特", "敏感体贴",
			"乐观自信", "追求完美", "正义真诚", "冷静独立", "幽默活泼", "踏实稳重" };

	public static String getConstellation(int month, int day) {
		return day < dayArr[month - 1] ? xingzuoArr[month - 1] : xingzuoArr[month];
	}

	public static String getNature(int month, int day) {
		return day < dayArr[month - 1] ? bewriteArr[month - 1] : bewriteArr[month];
	}

	/**
	 * 将对象数组拼接成字符串 以 "," 号分隔 返回String
	 * 
	 * @param ids
	 * @return
	 */
	public static String getString(Object[] objArr) {
		return org.apache.commons.lang.StringUtils.join(objArr, ",");
	}

	/**
	 * 将对象数组转换为可显字符串
	 * 
	 * @param objArr
	 * @return
	 */
	public static String toString(Object[] objArr) {
		if (objArr == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer("[");
		for (int i = 0; i < objArr.length; i++) {
			buf.append((i > 0 ? "," : "") + objArr[i]);
		}
		buf.append("]");
		return buf.toString();
	}

	/**
	 * 获取星期几的名称
	 * 
	 * @param str
	 * @return
	 */
	public static String getWeekName(String str) {
		return weekName.get(str);
	}

	/**
	 * 将单个对象转换为可显字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		if (obj instanceof String) {
			return "\"" + obj + "\"";
		}
		if (obj instanceof Object[]) {
			return toString((Object[]) obj);
		} else {
			return String.valueOf(obj);
		}
	}

	/**
	 * 使用正则表达式验证字符串格式是否合法
	 * 
	 * @param piNoPattern
	 * @param str
	 * @return
	 */
	public static boolean patternValidate(String pattern, String str) {
		if (pattern == null || str == null) {
			throw new SystemException("参数格式不合法[patternValidate(String " + pattern + ", String " + str + ")]");
		}
		return Pattern.matches(pattern, str);
	}

	/**
	 * 验证字符串是否为空字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().equals("") || str.trim().toLowerCase().equals("null")
				|| str.trim().toLowerCase().equals("all");
	}

	/**
	 * 验证字符串是否不为空字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 如果为空,将字符串转换为NULL
	 * 
	 * @param str
	 * @return
	 */
	public static String trimToNull(String str) {
		String s = null;
		if (isBlank(str)) {
			return s;
		}
		s = str.trim();
		return s;
	}

	/**
	 * 字符编码转换器
	 * 
	 * @param str
	 * @param newCharset
	 * @return
	 * @throws Exception
	 */
	public static String changeCharset(String str, String newCharset) throws Exception {
		if (str != null) {
			byte[] bs = str.getBytes();
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 判断一个字符串是否为boolean信息
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBooleanStr(String str) {
		try {
			Boolean.parseBoolean(str);
			return true;
		} catch (Throwable t) {
			return false;

		}
	}

	/**
	 * 取得指定长度的字符串(如果长度过长,将截取后半部分特定长度,如果长度太短,则使用指定字符进行左补齐)
	 * 
	 * @param str
	 *            原始字符串
	 * @param length
	 *            要求的长度
	 * @param c
	 *            用于补位的支付
	 * @return 指定长度的字符串
	 */
	public static String getLengthStr(String str, int length, char c) {
		if (str == null) {
			str = "";
		}
		int strPaymentIdLength = str.length();
		if (strPaymentIdLength > length) {
			str = str.substring(strPaymentIdLength - length);
		} else {
			str = org.apache.commons.lang.StringUtils.leftPad(str, length, c);
		}
		return str;
	}

	/**
	 * 
	 * @Title: convertNullToLong @Description: TODO String 作非空处理 @param @param
	 *         orgStr @param @param convertStr @param @return @return
	 *         Long @throws
	 */
	public static String convertNullToString(Object orgStr, String convertStr) {
		if (orgStr == null) {
			return convertStr;
		}
		return orgStr.toString();
	}

	/**
	 * 
	 * @Title: convertNullToLong @Description: TODO Long 作非空处理 @param @param
	 *         orgStr @param @param convertStr @param @return @return
	 *         Long @throws
	 */
	public static Long convertNullToLong(Object orgStr, Long convertStr) {
		if (orgStr == null || Long.parseLong(orgStr.toString()) == 0) {
			return convertStr;
		} else {
			return Long.valueOf(orgStr.toString());
		}
	}

	/**
	 * 
	 * @Title: convertNullTolong @Description: TODO long 作非空处理 @param @param
	 *         orgStr @param @param convertStr @param @return @return
	 *         long @throws
	 */
	public static long convertNullTolong(Object orgStr, long convertStr) {
		if (orgStr == null || Long.parseLong(orgStr.toString()) == 0) {
			return convertStr;
		} else {
			return Long.parseLong(orgStr.toString());
		}
	}

	/**
	 * 
	 * @Title: convertNullToInt @Description: TODO Int 作非空处理 @param @param
	 *         orgStr @param @param convertStr @param @return @return
	 *         int @throws
	 */
	public static int convertNullToInt(Object orgStr, int convertStr) {
		if (orgStr == null || Long.parseLong(orgStr.toString()) == 0) {
			return convertStr;
		} else {
			return Integer.parseInt(orgStr.toString());
		}
	}

	/**
	 * 
	 * @Title: convertNullToInt @Description: TODO Integer 作非空处理 @param @param
	 *         orgStr @param @param convertStr @param @return @return
	 *         int @throws
	 */
	public static int convertNullToInteger(Object orgStr, int convertStr) {
		if (orgStr == null) {
			return convertStr;
		} else {
			return Integer.valueOf(orgStr.toString());
		}
	}

	/**
	 * 
	 * @Title: convertNullToDate @Description: TODO Date 作非空处理 @param @param
	 *         orgStr @param @return @return Date @throws
	 */
	public static Date convertNullToDate(Object orgStr) {
		if (orgStr == null || orgStr.toString().equals("")) {
			return new Date();
		} else {
			return (Date) (orgStr);
		}
	}

	/**
	 * 
	 * @Title: convertNullToDate @Description: TODO Date 作非空处理 @param @param
	 *         orgStr @param @return @return Date @throws
	 */
	public static BigDecimal convertNullToBigDecimal(Object orgStr) {
		if (orgStr == null || orgStr.toString().equals("")) {
			return new BigDecimal("0");
		} else {
			return (BigDecimal) (orgStr);
		}
	}

	/**
	 * 对字符串 - 在左边填充指定符号
	 * 
	 * @param s
	 * @param fullLength
	 * @param addSymbol
	 * @return
	 */
	public static String addSymbolAtLeft(String s, int fullLength, char addSymbol, Integer curLineNO) {
		if (s == null) {
			return null;
		}

		int distance = 0;
		String result = curLineNO.toString();
		String incrent = curLineNO.toString();
		int length = curLineNO.toString().length();
		distance = fullLength - length;

		if (distance <= 0) {
			System.out.println(
					"StringTools:addSymbolAtleft() --> Warinning ,the length is equal or larger than fullLength!");
		}

		else {
			char[] newChars = new char[fullLength];
			for (int i = 0; i < length; i++) {
				newChars[i + distance] = incrent.charAt(i);
			}
			for (int j = distance - 1; j >= 0; j--) {
				newChars[j] = addSymbol;
				// newChars[j] =
				// String.valueOf((int)Math.round(Math.random()*(9))).charAt(0);
			}

			result = new String(newChars);
		}
		return result;
	}

	/**
	 * 对字符串 - 在右边填充指定符号
	 * 
	 * @param s
	 * @param fullLength
	 * @param addSymbol
	 * @return
	 */
	public static String addSymbolAtRight(String s, int fullLength, char addSymbol) {
		if (s == null) {
			return null;
		}

		String result = s;
		int length = s.length();

		if (length >= fullLength) {
			System.out.println(
					"StringTools:addSymbolAtRight() --> Warinning ,the length is equal or larger than fullLength!");
		}

		else {
			char[] newChars = new char[fullLength];

			for (int i = 0; i < length; i++) {
				newChars[i] = s.charAt(i);
			}

			for (int j = length; j < fullLength; j++) {
				newChars[j] = addSymbol;
			}
			result = new String(newChars);
		}

		return result;
	}

	/**
	 * 判断两个字符串是否相同
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		} else {
			return str1.equals(str2);
		}
	}

	/**
	 * 判断两个字符串是否不同
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean notEquals(String str1, String str2) {
		return !isEquals(str1, str2);
	}

	public static Long[] convertionToLong(String[] strs) {// 将String数组转换为Long类型数组
		Long[] longs = new Long[strs.length]; // 声明long类型的数组
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i]; // 将strs字符串数组中的第i个值赋值给str
			long thelong = Long.valueOf(str);// 将str转换为long类型，并赋值给thelong
			longs[i] = thelong;// 将thelong赋值给 longs数组中对应的地方
		}
		return longs;
	}

	/**
	 * 把字符串分割成数组，使用 divider 分割。
	 * 
	 * @param value
	 *            需要分割的字符串
	 * @param divider
	 *            分割符
	 * @return 分割后的数组
	 */
	public static String[] parse1D(String value, String divider) {
		DevideStr devideStr = new DevideStr(value);
		return devideStr.DevideOneStr(divider);
	}

	/**
	 * 转化银行卡号为“**** **** **** 7777”
	 * 
	 * @param bankNO
	 * @return
	 */
	public static String bankCarkParse(String bankNO) {
		int len = bankNO.length();
		int num = len + ((len % 4 == 0) ? (len / 4 - 1) : (len / 4));
		StringBuffer str = new StringBuffer();
		int j = 0;
		for (int i = 1; i <= num; i++) {
			if (i % 5 == 0) {
				str.append(' ');
			} else {
				if (j > len - 5) {
					str.append(bankNO.charAt(j));
				} else {
					str.append('*');
				}
				j++;
				;
			}
		}
		return str.toString();
	}

	/**
	 * 掩码处理
	 * 
	 * @param prefixBit
	 * @param src
	 * @param sufixBit
	 * @param maskCode
	 * @return
	 */
	public static String toMaskStr(int prefixBit, String src, int sufixBit, char maskCode, int maskBit) {
		if (isBlank(src)) {
			return src;
		}
		int length = src.length();
		if (prefixBit >= length || sufixBit >= length || prefixBit + sufixBit >= length) {
			return src;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(src.substring(0, prefixBit));

		if (maskBit == 3) {
			sb.append("***");
		} else {
			for (int i = 0; i < maskBit; i++) {
				sb.append(maskCode);
			}
		}
		sb.append(src.substring(length - sufixBit));

		return sb.toString();
	}

	/**
	 * 掩码处理默认*
	 * 
	 * @param prefixBit
	 * @param src
	 * @param sufixBit
	 * @return
	 */
	public static String toMaskStr(int prefixBit, String src, int sufixBit) {
		return toMaskStr(prefixBit, src, sufixBit, '*', 3);
	}

	/**
	 * 掩码处理默认首未可保留中间自定义*数量
	 * 
	 * @param prefixBit
	 *            首前面保留多少个*
	 * @param src
	 *            需要处理的字符串
	 * @param sufixBit
	 *            未 保留位数
	 * @param setNum
	 *            中间需要几个*
	 * @return
	 */
	public static String toMaskStrs(int prefixBit, String src, int sufixBit, int setNum) {
		return toMaskStr(prefixBit, src, sufixBit, '*', 3);
	}

	/**
	 * 保持字符串长度不变的掩码处理
	 * 
	 * @param prefixBit
	 * @param src
	 * @param sufixBit
	 * @return
	 */
	public static String toFixedMaskStr(int prefixBit, String src, int sufixBit) {
		int length = src.length() - prefixBit - sufixBit;
		if (length < 0) {
			length = 0;
		}
		return toMaskStr(prefixBit, src, sufixBit, '*', length);
	}

	/**
	 * 字符串尾掩码处理默认*
	 * 
	 * @param src
	 * @param sufixBit
	 * @return
	 */
	public static String toMaskStr(String src, int sufixBit) {
		return toMaskStr(0, src, sufixBit, '*', 3);
	}

	/**
	 * 字符串尾掩码处理默认*
	 * 
	 * @param src
	 * @param sufixBit
	 * @param maskBit
	 * @return
	 */
	public static String toMaskStr(String src, int sufixBit, int maskBit) {
		return toMaskStr(0, src, sufixBit, '*', maskBit);
	}

	/**
	 * 字符串首掩码处理默认*
	 * 
	 * @param prefixBit
	 * @param src
	 * @return
	 */
	public static String toMaskStr(int prefixBit, String src) {
		return toMaskStr(prefixBit, src, 0, '*', 3);
	}

	/**
	 * 字符串首掩码处理默认* 4位一隔 保留最后一位
	 * 
	 * @param src
	 *            需要截取的字段值
	 * @return
	 */
	public static String toMaskStrFor4(String strs) {
		String subStr = strs.substring(strs.length() - 4, strs.length());
		String str1 = strs.substring(0, strs.length() - 4);
		String str = toFixedMaskStr(0, str1, 0);
		String newstr = "";
		int size = ((str.length()) % 4 == 0) ? ((str.length()) / 4) : ((str.length()) / 4 + 1);
		for (int i = 0; i < size; i++) {
			int endIndex = (i + 1) * 4;
			if ((i + 1) == size) {
				endIndex = str.length();
			}
			if (i == 0) {
				newstr += str.substring(i, endIndex);
			} else {
				newstr += " " + str.substring(i * 4, endIndex);
			}
		}
		return newstr + " " + subStr;
	}

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(val.toString());
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	public static String trimLastComma(String str) {
		if (null == str) {
			return str;
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 
	 * @Title: loadProperties @Description:
	 *         TODO(讀取可配置的jks資源秘鑰文件) @param @param @param @param @param @return @param @throws
	 *         Exception 设定文件 @return Properties 返回类型 @throws
	 */

	public static Properties loadProperties(String url) {
		Properties p = new Properties();
		try {
			InputStream in = StringUtils.class.getClassLoader().getResourceAsStream(url);// 这里有人用new
			// FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。
			p.load(in);
			in.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return p;
	}

	/**
	 * 驼峰字符串转下划线
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		String ret = "";
		if (StringUtils.isBlank(param)) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(ret);
				sb.append("_" + Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();

	}

	/**
	 * 检查手机号码格式是否正确 ^[1][0-9]{10}$
	 * 
	 * @param phoneStr
	 * @return
	 */
	public static boolean checkMoblie(String phoneStr) {
		if (isBlank(phoneStr)) {
			return false;
		}
		String regExp = "^[1][0-9]{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneStr);
		return m.matches();
	}

	/**
	 * 检查电话号码 ^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$
	 * 
	 * @param phoneStr
	 * @return
	 */
	public static boolean checkPhone(String phoneStr) {
		if (isBlank(phoneStr)) {
			return false;
		}
		String regExp = "^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneStr);
		return m.matches();
	}

	/**
	 * 验证身份证格式
	 * 
	 * @param cardString
	 *            ^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$
	 * @return
	 */
	public static boolean checkIdentityCard(String cardString) {
		if (isBlank(cardString)) {
			return false;
		}
		String regExp = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(cardString);
		return m.matches();
	}

	/**
	 * 匹配中文，英文，数字 ^[\u4e00-\u9fa5a-zA-Z0-9]+$
	 * 
	 * @param word
	 *            字符串
	 * @return
	 */
	public static boolean checkChineseEnglishNumber(String word) {
		if (isBlank(word)) {
			return false;
		}
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5a-zA-Z0-9]+$");
		Matcher m = p.matcher(word);
		return m.matches();
	}

	public static String getRamdomCode() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "0", "1" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		System.out.println(afterShuffle);
		String result = afterShuffle.substring(3, 9);
		return result;
	}

	/**
	 * 检查中文姓名 ^[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*$
	 * 
	 * @param word
	 * @return
	 */
	public static boolean checkChineseName(String word) {
		if (isBlank(word)) {
			return false;
		}
		Pattern p = Pattern.compile("^[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*$");
		Matcher m = p.matcher(word);
		return m.matches();
	}

	// public static void main(String[] args) {
	//
	// System.out.println(toMaskStr(6, "6225124578457895", 4));
	// System.out.println(toFixedMaskStr(0, "6225124578457895", 4));
	// System.out.println(toMaskStr(2, "6225124578457895"));
	// System.out.println(toMaskStr("6225124578457895", 4));
	// System.out.println(toMaskStr("6225124578457895", 4, 1));
	// System.out.println(toMaskStr(4, "622578451", 4, '*', 1));
	// System.out.println(toFixedMaskStr(4, "622578451", 4));
	// System.out.println("四位一隔带*：" + toMaskStrFor4("622512457845789599"));
	//
	// System.out.println(checkMoblie("01516517302"));
	// System.out.println(checkChineseName("课堂中国人"));
	// }
}
