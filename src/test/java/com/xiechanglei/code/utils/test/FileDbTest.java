package com.xiechanglei.code.utils.test;

import com.xiechanglei.code.db.FileDb;

public class FileDbTest {
	public static void main(String[] args) throws Exception {
		FileDb<String> fileDb = new FileDb<String>("d:/1.db");
		fileDb.insert("caonima");
		fileDb.insert("caonima");
		fileDb.insert("caonima");
	}
}
