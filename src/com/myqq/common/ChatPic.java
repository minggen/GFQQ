package com.myqq.common;

import java.net.URL;

import javax.swing.ImageIcon;

public class ChatPic extends ImageIcon {
	
	/** ÏÂ±ê */
	private int index;
	/** ´úºÅ */
	private int number;
	
	public ChatPic(String fileName, int im) {
		super(fileName);
		this.number = im;
	}

	public ChatPic(URL url, int index, int im) {
		super(url);
		this.number = im;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
