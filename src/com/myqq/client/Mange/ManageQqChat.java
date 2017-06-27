package com.myqq.client.Mange;

import java.util.HashMap;

import com.myqq.client.ui.ChatRoom;

public class ManageQqChat {
private static HashMap hm=new HashMap<String, ChatRoom>();
	
	//加入
	public static void addQqChat(String loginIdAnFriendId,ChatRoom qqChat)
	{
		if(hm.get(loginIdAnFriendId)==null)
			hm.put(loginIdAnFriendId, qqChat);
	}
	//取出
	public static ChatRoom getQqChat(String loginIdAnFriendId )
	{
		return (ChatRoom)hm.get(loginIdAnFriendId);
	}
	
	public static boolean isNotExist(String loginIdAnFriendId){
		return hm.get(loginIdAnFriendId)==null;
	}
	public static void RemoveQqChat(String string) {
		// TODO 自动生成的方法存根
		hm.remove(getQqChat(string));
	}
	
	
}
