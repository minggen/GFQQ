package com.myqq.sever;

import com.myqq.client.FileMange.FileSever;

public class run {
public static void main(String[] args) {
	
	new Thread(){
		public void run(){
		new MyQqServer();
		}
	}.start();
	
	new FileSever();
}
}
