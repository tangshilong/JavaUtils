package com.xiechanglei.code.utils;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * AES 加密工具类
 */
public class AESUtil {

	public static final String encode = "UTF-8";

	/**
	 * 对字符串加密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encryptStr(String content, String password) {
		try {
			byte[] encrypt = encrypt(content.getBytes(encode), password.getBytes(encode));
			return new String(Base64.getEncoder().encode(encrypt), encode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对字符串解密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decryptStr(String content, String password) {
		try {
			byte[] decode = Base64.getDecoder().decode(content.getBytes(encode));
			return new String(decrypt(decode, password.getBytes(encode)), encode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加密
	 * 
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] byteContent, byte[] password) throws Exception {
		return aes(byteContent, password, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 解密
	 * 
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] content, byte[] password) throws Exception {
		return aes(content, password, Cipher.DECRYPT_MODE);
	}

	private static byte[] aes(byte[] byteContent, byte[] password, int encryptMode) throws Exception {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(encryptMode, key);
			return cipher.doFinal(byteContent);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

}
