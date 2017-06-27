package com.myqq.sever.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myqq.common.User;

public class Frienddb  extends Base{
	
	/**
	 * 
	 * @param name 分组名称
	 * @param ownerId 谁的分组
	 * @return  user的集合
	 */
	public List<User> getUserList(String name,String ownerId ){
		List<String> memberlist = new ArrayList<String>();
		try {
			String sql = "SELECT member_id FROM `gfqq_category_member` WHERE owner_id='"+ownerId+"' and category_name='"+name+"'";
			ResultSet result = select(sql);
			
			while (null != result && result.next()) {
				
				memberlist.add(result.getString("member_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<User> list = new ArrayList<User>();
		
		for(String ownid:memberlist){
			System.out.println(ownerId+"的"+ownid);
			try {
				String sql = "SELECT * FROM `gfqq_user` WHERE user_name='"+ownid+"'";
				ResultSet result = select(sql);
				
				if(result.next()){
				String passwd = String.valueOf(result.getString("user_password"));
				String userName = String.valueOf(result.getString("user_name"));
				String nickName = result.getString("nick_name");
				String signature = result.getString("user_signature");
				String txpic = result.getString("user_pic");
				System.out.println(txpic);
				list.add(new User(nickName,userName,passwd, signature,txpic));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

//	private User assembleUser(ResultSet result) {
//		try {
//			String passwd = String.valueOf(result.getString("user_password"));
//			String userName = String.valueOf(result.getString("user_name"));
//			String nickName = result.getString("nick_name");
//			String signature = result.getString("user_signature");
//			String txpic = result.getString("user_pic");
//			return new User(nickName,userName,passwd, signature,txpic);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public static void main(String[] args) {
//		List<User>  list= new Frienddb().getUserList("wdhy", "1");
//		System.out.println();
//	}
}
