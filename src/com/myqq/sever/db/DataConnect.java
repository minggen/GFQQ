package com.myqq.sever.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

	private static Connection conn;

	private DataConnect() {

	}

	public static Connection getConnect() {
		try {
		if (null == conn) {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://59433b1ea13fe.gz.cdb.myqcloud.com:15976/GFQQ?characterEncoding=UTF-8";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "cdb_outerroot", "w919767736MG");
			System.out.println("Succeeded connecting to the Database!");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return conn;
}
	
	public static void main(String[] args) {
		DataConnect.getConnect();
		System.out.println("dfds");
	}
}
