package com.myqq.sever.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.myqq.common.User;

public class userdb extends Base {
	
	public User login(String userid, String pass) {
		String sql ="SELECT * FROM `gfqq_user` WHERE `user_name`="+Integer.valueOf(userid)+" and `user_password` = '"+pass+"'";
	
		ResultSet result = select(sql);
		return assembleUser(result);
	}
	

	public User getUserFromUserid(String userid) {
		String sql ="SELECT * FROM `gfqq_user` WHERE `user_name`='"+Integer.valueOf(userid)+"'";
	
		ResultSet result = select(sql);
		return assembleUser(result);
	}
	
	/**
	 * getByUserName: 通过账号查询 	<br/>
	 * @author SongFei
	 * @param value	账号
	 */
	public User getByUserName(String value) {
		String sql = "select * from `fqq_user`  where `fqq_user.user_name` = " + Integer.valueOf(value);
		ResultSet result = select(sql);
		return assembleUser(result);
	}
	
	/**
	 * getByNickName: 通过昵称查询	<br/>
	 * @author SongFei
	 * @param value	昵称
	 */
	public User getByNickName(String value) {
		String sql = "select * from `gfqq_user`  where `nick_name` = '"+value+"' ";
		ResultSet result = select(sql);
		return assembleUser(result);
	}
	
	/**
	 * getByAccountOrNickName: 通过账号或昵称查询	<br/>
	 * @author SongFei
	 * @param value	账号或昵称
	 */
	public User getByAccountOrNickName(String value) {
		String sql = "select * from gfqq_user fu where fu.user_name = " +
				Integer.valueOf(value)+ " or fu.nick_name = '"+value+"' ";
		ResultSet result = select(sql);
		return assembleUser(result);
	}
	
	
	// 组装User对象
	private User assembleUser(ResultSet result) {
		try {
			if (null != result && result.next()) {
				String passwd = String.valueOf(result.getString("user_password"));
				String userName = String.valueOf(result.getString("user_name"));
				String nickName = result.getString("nick_name");
				String signature = result.getString("user_signature");
				String txpic = result.getString("user_pic");
				return new User(nickName,userName,passwd, signature,txpic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
