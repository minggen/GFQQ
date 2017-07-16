package com.myqq.client.FileMange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import com.myqq.common.Message;
import com.myqq.common.MessageType;

public class sendFileToServer {
	
	public static void send(String  self,String friend) {	
		System.out.println(self+"要给"+friend+"发文件");
		new FileClientConServer(self);
		Message m = new Message();
		m.setGetter(friend);
		m.setSender(self);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(MangeFileClientConSerThread.FileClientConSerThread(self).getS().getOutputStream());
			System.out.println("得到对象流");
			
			JFileChooser jfilefhooser = new JFileChooser();
			jfilefhooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfilefhooser.showOpenDialog(null) ;
	        File file = jfilefhooser.getSelectedFile();  
			//m是我要开始发消息了 
			byte[] bytes = new byte[1024]; 
			int length = 0; 
			long progress = 0; 
			
			FileInputStream fis = new FileInputStream(file);   
			
			
				
				
				Message filemessage = new Message();
				
				filemessage.setMesType(MessageType.message_File);
				filemessage.setGetter(m.getSender());
				filemessage.setSender(m.getGetter());
				filemessage.setCon(file.getName());
				oos.writeObject(filemessage);
				System.out.println("发送文件名"+file.getName());
				
			
				while((length = fis.read(bytes, 0, bytes.length)) != -1){
					System.out.println("11212");
					
					filemessage = new Message();
					filemessage.setBuf(bytes);
					filemessage.setMesType(MessageType.message_File);
					filemessage.setGetter(m.getSender());
					filemessage.setSender(m.getGetter());
					filemessage.setCon(length+"");
					oos.writeObject(filemessage);
//					Thread.sleep(10000);
					System.out.println("正在发送文件");
				}
				
				
				//告诉客户端结束
				filemessage = new Message();
				filemessage.setGetter(m.getSender());
				filemessage.setSender(m.getGetter());
				filemessage.setMesType(MessageType.message_File);
				filemessage.setCon("over");
				oos.writeObject(filemessage);
				
				System.out.println("发送完毕");
			} catch (Exception e1) {
				e1.printStackTrace();		
			}
			
	}
	

}