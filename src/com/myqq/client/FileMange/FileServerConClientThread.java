package com.myqq.client.FileMange;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.myqq.common.Message;
public class FileServerConClientThread  extends Thread {
	public Socket s;
	ObjectInputStream ois = null;
	ObjectOutputStream oos =null;
	
	public FileServerConClientThread(Socket s) {
		// 把服务器和该客户端的连接赋给s
		this.s = s;
		
	}
	   
	public void run() {
		try {
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		while (true) {
			//一直接受客户端输入，转发出去		
			try {
				Message m = null;
				m = (Message) ois.readObject();
				//接受
				System.out.println("文件名----->"+m.getCon());
				//转发
				//System.out.println("byte-->"+m.getBuf());
				System.out.println("发送者"+m.getSender()+"接收者"+m.getGetter());
				FileServerConClientThread fscct  =ManageFileServerConClientThread.getFileServerConClientThread(m.getSender());
				oos=new ObjectOutputStream(fscct.s.getOutputStream());
				oos.writeObject(m);
				

//				String filename = m.getCon();
//				String pathname= "D:\\"+filename;
//				System.out.println(m.getCon());
//				
//				File file= new File(pathname);
//				FileOutputStream fos =new FileOutputStream(file);
//				if (!file.exists()) {
//					file.createNewFile();
//				}
//				
//				while(true)
//				{
//					
//					 //ois=new ObjectInputStream(s.getInputStream());
//					 m=(Message)ois.readObject();
//					 if(m.getCon().equals("over"))
//						 break;
//					 byte [] bytes  = m.getBuf();
//					 fos.write(bytes, 0, Integer.parseInt(m.getCon()));
//					 fos.flush();
//					System.out.println("读取到从服务发来的文件"+ m.getSender() +" 给 "+m.getGetter()+" 内容"+
//								m.getCon()); 
//						 
//					
//				}
//				
//				System.out.println("接受完毕");
//				fos.close();
				
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
					System.out.println("文件發送異常");
				}
		}
	}
}
