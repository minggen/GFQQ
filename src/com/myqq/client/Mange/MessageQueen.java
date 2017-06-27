package com.myqq.client.Mange;

import com.myqq.common.Message;

public class MessageQueen {
	private String sender;
	private String geter;
	private Message msg;
	
	public MessageQueen(String sender, String geter, Message msg) {
		super();
		this.sender = sender;
		this.geter = geter;
		this.msg = msg;
	}
	public String getGeter() {
		return geter;
	}
	public void setGeter(String geter) {
		this.geter = geter;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Message getMsg() {
		return msg;
	}
	public void setMsg(Message msg) {
		this.msg = msg;
	}
}
