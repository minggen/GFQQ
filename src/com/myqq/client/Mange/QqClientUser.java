package com.myqq.client.Mange;

import com.myqq.common.Message;
import com.myqq.common.User;

public class QqClientUser {
	
	//调用验证
	
	public Message checkUser(User u)
	{
		
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
}
