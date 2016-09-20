package com.xiechanglei.code.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.xiechanglei.code.utils.JsonUtil;

/**
 * 文件数据库:
 * 
 * @param <T>
 * 
 */
public class FileDb<T> {

	public static final String ENCODE = "UTF-8";
	private File storeFile = null;
	private List<T> data = null;
	private long lastModify = 0;

	public FileDb(String fileName) throws Exception {
		this.setStoreFile(new File(fileName));
	}

	public FileDb(File file) throws Exception {
		this.setStoreFile(file);
	}

	public void setStoreFile(File sotreFile) throws Exception {
		this.storeFile = sotreFile;
		if (this.storeFile.isDirectory()) {
			throw new Exception("expect a file,got a directory");
		} else if (!this.storeFile.exists()) {
			this.storeFile.getParentFile().mkdirs();
			this.storeFile.createNewFile();
			this.lastModify = this.storeFile.lastModified();
			this.data = new ArrayList<T>();
		}
	}

	private void saveData() {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(storeFile, ENCODE);
			printWriter.println(JsonUtil.toJson(this.data));
			printWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(null, printWriter);
		}
		lastModify = this.storeFile.lastModified();
	}

	@SuppressWarnings("unchecked")
	private void loadData() {
		System.out.println(lastModify);
		if (lastModify == this.storeFile.lastModified()) {
			return;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(storeFile), ENCODE));
			String result = "";
			while (true) {
				String readLine = reader.readLine();
				if (readLine != null) {
					result += readLine;
				} else {
					break;
				}
			}
			this.data = (List<T>) JsonUtil.fromJson(result, new TypeReference<List<T>>() {
			});
		} catch (Exception e) {
		} finally {
			closeStream(reader, null);
		}
		if (this.data == null) {
			this.data = new ArrayList<T>();
		}
	}

	private void closeStream(BufferedReader reader, PrintWriter writer) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (writer != null) {
			writer.close();
		}
	}

	/**
	 * 插入一个元素
	 * 
	 * @param t
	 */
	public void insert(T t) {
		this.loadData();
		this.data.add(t);
		this.saveData();
	}

	public void delete(String fql) {
		// TODO
	}

	public Filter filter(FilterChain<T> chain) {
		List<T> resu = new ArrayList<T>();
		for (T t : data) {
			if (chain.dofilter(t)) {
				resu.add(t);
			}
		}
		return new Filter(resu);
	}
}
