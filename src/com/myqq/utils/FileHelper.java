package com.myqq.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFileChooser;

public class FileHelper {
	public static void main(String[] args) {
		
	    Socket socket = null;
	    System.out.println("已連接0");
	      try {
	//         socket = new Socket("172.29.11.176",8888);
	           socket = new Socket("127.0.0.1",8888);
	           System.out.println("已連接1");
	           
	      }
	      catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}