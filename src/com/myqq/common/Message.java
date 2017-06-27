package com.myqq.common;

public class Message implements java.io.Serializable{
	private String mesType;//消息类型	
	private String sender;//发送者
	private String sendnickname;
	public String getSendnickname() {
		return sendnickname;
	}

	public void setSendnickname(String sendnickname) {
		this.sendnickname = sendnickname;
	}

	private String getter;//接受者
	private String con;//发送内容
	private byte[] buf ;//文件传送
	private String sendTime;//发送时间

	private Integer back=0;//背景颜色
	private Integer fore=2;//字体颜色
	private Integer style=2;
	private Integer size=1;
	private String family="楷体";
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getBack() {
		return back;
	}

	public void setBack(Integer back) {
		this.back = back;
	}

	public Integer getFore() {
		return fore;
	}

	public void setFore(Integer fore) {
		this.fore = fore;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	public String getFont() {
		// TODO 自动生成的方法存根
		return null;
	}

	public String getCol() {
		// TODO 自动生成的方法存根
		return null;
	}
}
