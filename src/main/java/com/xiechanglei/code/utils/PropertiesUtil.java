package com.xiechanglei.code.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 寻找加载当前classpath下的所有的 .properties 文件，并且将他们全部加载进来 这样的话就要求名字不要起一样子的
 */
public class PropertiesUtil {

	private static final String fileDest = ".properties";
	private static List<File> emptyList = new ArrayList<File>();
	private static Map<String, String> propertiesMap = new HashMap<String, String>();

	static {
		loadProperties();
	}

	public static String get(String key) {
		return propertiesMap.get(key);
	}

	private static void loadProperties() {
		File path = new File(PropertiesUtil.class.getClassLoader().getResource("").getFile());
		findPropertiesFiles(path);
		loadPropertiesFile();
	}

	/**
	 * 加载所有的.properties 文件，获取所有的key value 保存到map
	 */
	private static void loadPropertiesFile() {
		Properties properties = new Properties();
		for (File file : emptyList) {
			FileInputStream fin = null;
			try {
				properties.clear();
				fin = new FileInputStream(file);
				properties.load(fin);
				Enumeration<Object> keys = properties.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					propertiesMap.put(key, properties.getProperty(key));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 遍历寻找所有的.properties 文件
	 * 
	 * @param file
	 */
	private static void findPropertiesFiles(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				findPropertiesFiles(file2);
			}
		} else if (file.getName().indexOf(fileDest) != -1) {
			emptyList.add(file);
		}
	}

}
