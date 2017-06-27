package com.myqq.sever.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;

	/** ��ѯ */
	public ResultSet select(String sql) {
		try {
			System.out.println(sql);
			connect = DataConnect.getConnect();
			statement = connect.createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnect(connect, statement, resultSet);
		}
		return null;
	}

	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	/** �������������޸ġ�ɾ���� */
	public int operate(String sql) {
		int number = 0;
		try {
			System.out.println(sql);
			connect = DataConnect.getConnect();
			statement = connect.createStatement();
			number = statement.executeUpdate(sql);
			// ��������Ϊ�ֶ�������ع�
			connect.setAutoCommit(false);
			connect.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeConnect(connect, statement, resultSet);
		}
		return number;
	}

	/** �ر����� */
	public void closeConnect(Connection connect, Statement statement,
			ResultSet resultSet) {
		
		
	}

}
