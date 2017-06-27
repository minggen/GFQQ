package com.myqq.client.Mange;

import java.util.HashMap;

import com.myqq.client.ui.ChatRoom;

public class ManageQqChat {
private static HashMap hm=new HashMap<String, ChatRoom>();
	
	//����
	public static void addQqChat(String loginIdAnFriendId,ChatRoom qqChat)
	{
		if(hm.get(loginIdAnFriendId)==null)
			hm.put(loginIdAnFriendId, qqChat);
	}
	//ȡ��
	public static ChatRoom getQqChat(String loginIdAnFriendId )
	{
		return (ChatRoom)hm.get(loginIdAnFriendId);
	}
	
	public static boolean isNotExist(String loginIdAnFriendId){
		return hm.get(loginIdAnFriendId)==null;
	}
	public static void RemoveQqChat(String string) {
		// TODO �Զ����ɵķ������
		hm.remove(getQqChat(string));
	}
	
	
}
