package com.xiechanglei.code.utils;

/**
 * String的工具类
 */
public class StringUtil {
	/**
	 * 是否为null 或者空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.equals("");
	}

	/**
	 * 是否不为null 或者空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
}
