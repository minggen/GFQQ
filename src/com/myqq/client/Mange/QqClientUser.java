package com.myqq.client.Mange;

import com.myqq.common.Message;
import com.myqq.common.User;

public class QqClientUser {
	
	//������֤
	
	public Message checkUser(User u)
	{
		
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
}
