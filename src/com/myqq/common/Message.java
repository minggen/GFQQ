package com.myqq.common;

public class Message implements java.io.Serializable{
	private String mesType;//��Ϣ����	
	private String sender;//������
	private String sendnickname;
	public String getSendnickname() {
		return sendnickname;
	}

	public void setSendnickname(String sendnickname) {
		this.sendnickname = sendnickname;
	}

	private String getter;//������
	private String con;//��������
	private byte[] buf =new byte[1024];//�ļ�����
	private String sendTime;//����ʱ��


	public String getSender() {
		return sender;
	}

	public byte[] getBuf() {
		return buf;
	}

	public void setBuf(byte[] buf) {
		for(int i=0;i<buf.length;i++){
			this.buf[i]=buf[i];
		}
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

	
}
