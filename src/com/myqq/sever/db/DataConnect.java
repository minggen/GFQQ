package com.myqq.sever.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

	private static Connection conn;

	public static Connection getConnect() {
		try {
		if (null == conn) {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://数据库地址/GFQQ?characterEncoding=UTF-8";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "账号", "密码");
			System.out.println("Succeeded connecting to the Database!");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return conn;
}
	
}
