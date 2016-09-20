package com.xiechanglei.code.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 文件工具类
 *
 */
public class FileUtil {

	public static final String DEFAULT_ENCODE = "UTF-8";

	/**
	 * 文件copy
	 * 
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public static void copyFile(String from, String to) throws IOException {
		copyFile(new File(from), new File(to));
	}

	/**
	 * 文件copy
	 * 
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public static void copyFile(File from, File to) throws IOException {
		if (notExits(from)) {
			throw new FileNotFoundException();
		}
		to.getParentFile().mkdirs();
		to.createNewFile();
		FileInputStream fin = null;
		FileOutputStream fou = null;
		try {
			fin = new FileInputStream(from);
			fou = new FileOutputStream(to);
			byte[] buffer = new byte[10 * 1024];
			while (true) {
				int read = fin.read(buffer);
				if (read != -1) {
					fou.write(buffer, 0, read);
					fou.flush();
				} else {
					break;
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (fin != null) {
				fin.close();
			}
			if (fou != null) {
				fou.close();
			}
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file
	 * @return
	 */
	public static boolean exits(File file) {
		return file.exists();
	}

	/**
	 * 判断文件是否不存在
	 * 
	 * @param file
	 * @return
	 */
	public static boolean notExits(File file) {
		return !file.exists();
	}

	/**
	 * 读取文件
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void printFile(File file, String encode, ReadHander hander) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));
			String readLine = null;
			while (true) {
				readLine = reader.readLine();
				if (readLine != null) {
					hander.deal(readLine);
				} else {
					break;
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public static void printFile(File file) throws IOException {
		printFile(file, DEFAULT_ENCODE);
	}
	
	public static void printFile(File file,String encode) throws IOException {
		printFile(file, encode, new ReadHander() {
			@Override
			public void deal(String readline) {
				System.out.println(readline);
			}
		});
	}
	public static void printFile(File file,ReadHander hander) throws IOException {
		printFile(file, DEFAULT_ENCODE,hander);
	}

	public static abstract class ReadHander {
		public abstract void deal(String readline);
	}
}
