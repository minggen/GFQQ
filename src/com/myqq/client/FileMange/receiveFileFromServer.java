package com.myqq.client.FileMange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import com.myqq.client.Mange.ManageQqChat;
import com.myqq.common.Message;
import com.myqq.common.MessageType;

public class receiveFileFromServer {
	public static void main(String[] args) {	
		
		
		new Thread(){
			public void run() {receivefile("2");};
		}.start();
		sendFileToServer.send("1", "2");
	}

	public static void receivefile(String string) {
		// TODO 自动生成的方法存根
		new  FileClientConServer(string);
	}
	
	
	
	
}