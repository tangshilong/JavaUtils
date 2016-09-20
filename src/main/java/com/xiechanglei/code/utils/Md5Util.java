package com.xiechanglei.code.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class Md5Util {
	/**
	 * Md5加密字符串，采用utf-8解码成bytes
	 * 
	 * @param value
	 * @return
	 */
	public static String getMD5String(String value) {
		try {
			return getMD5String(value.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 对bytes数组进行MD5加密
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getMD5String(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(bytes);
			return toHexString(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * bytes 数组 转16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	private static String toHexString(byte bytes[]) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = Integer.toHexString(bytes[n] & 0xff);
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}

		return hs.toString();
	}
}