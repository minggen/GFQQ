package com.myqq.client.FileMange;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.myqq.common.Message;
import com.myqq.common.User;

public class FileSever  {
	
	public FileSever()
	{
		
		try {	
			//在8888监听
			System.out.println("文件监听，在8888监听");
			ServerSocket ss=new ServerSocket(8888);
			//阻塞,等待连接
			while(true)
			{
				
				Socket s=ss.accept();
				System.out.println("s-->"+s);
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message u=(Message)ois.readObject();
				String  userid=u.getSender();

				System.out.println("有链接"+userid);
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				Message m = new Message();
				m.setCon("ok");
				oos.writeObject(m);
				
			
				//单开一个线程
				FileServerConClientThread fscct=new FileServerConClientThread(s);
				System.out.println("123");
				ManageFileServerConClientThread.addFileServerConClientThread(userid,fscct);
				fscct.start();
				System.out.println("已加入队列");
			}
		}
		catch (Exception e) {
			System.out.println("服务器文件监听出错");
		}
	}
	
	
	public static void main(String[] args) {
		new FileSever();
		
	}
}
