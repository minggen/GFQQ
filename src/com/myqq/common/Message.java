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
	private byte[] buf ;//�ļ�����
	private String sendTime;//����ʱ��

	private Integer back=0;//������ɫ
	private Integer fore=2;//������ɫ
	private Integer style=2;
	private Integer size=1;
	private String family="����";
	
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
		// TODO �Զ����ɵķ������
		return null;
	}

	public String getCol() {
		// TODO �Զ����ɵķ������
		return null;
	}
}
