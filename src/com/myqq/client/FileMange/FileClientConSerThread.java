package com.myqq.client.FileMange;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 这是客户端和服务器端保持通讯的线程.
 */
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

import com.myqq.client.ui.ChatRoom;
import com.myqq.client.ui.MainWindow;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.sever.db.userdb;
public class FileClientConSerThread extends Thread {

	private Socket s;
	private String filename;
	
	//构造函数
	public FileClientConSerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的消息
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
				
				filename = m.getCon();
				String pathname= "D:\\"+filename;
				System.out.println("文件名："+m.getCon());
				
				File file= new File(pathname);
				FileOutputStream fos =new FileOutputStream(file);
				if (!file.exists()) {
					file.createNewFile();
				}
				
				while(true)
				{
					
					 ois=new ObjectInputStream(s.getInputStream());
					 m=(Message)ois.readObject();
					 if(m.getCon().equals("over"))
						 break;
					 byte [] bytes  = m.getBuf();
					 fos.write(bytes, 0, Integer.parseInt(m.getCon()));
					 
					System.out.println("读取到从服务发来的文件"+ m.getSender() +" 给 "+m.getGetter()+" 内容"+
								m.getCon()); 
						 
					
				}
				
				System.out.println("接受完毕");
				fos.close();
				
				//if(m.getCon().equals("begin"))
				//	do{
						//m=(Message)ois.readObject();
						
				//	}
			//	while(!m.getCon().equals("over"));
			
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
	public void close(){
		try {
			s.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
