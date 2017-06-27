package com.myqq.common;

public class User	implements java.io.Serializable {
	//êÇ³Æ
	private String nick_name;
	//QQºÅ
	private String userId;
	//QQÃÜÂë
	private String passwd;
	//¸öÇ©
	private String user_signature;
	//Í·Ïñ
	private String img = "./tx.gif";
	
	public String getNick_name() {
		return nick_name;
	}

	public User(String nick_name, String userId, String passwd, String user_signature, String img) {
		super();
		this.nick_name = nick_name;
		this.userId = userId;
		this.passwd = passwd;
		this.user_signature = user_signature;
		this.img = img;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUser_signature() {
		return user_signature;
	}

	public void setUser_signature(String user_signature) {
		this.user_signature = user_signature;
	}

	public User() {
		super();
	}

	public User(String nick_name, String userId, String passwd, String user_signature) {
		super();
		this.nick_name = nick_name;
		this.userId = userId;
		this.passwd = passwd;
		this.user_signature = user_signature;
	}
	
	
}
