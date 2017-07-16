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
			//��8888����
			System.out.println("�ļ���������8888����");
			ServerSocket ss=new ServerSocket(8888);
			//����,�ȴ�����
			while(true)
			{
				
				Socket s=ss.accept();
				System.out.println("s-->"+s);
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message u=(Message)ois.readObject();
				String  userid=u.getSender();

				System.out.println("������"+userid);
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				Message m = new Message();
				m.setCon("ok");
				oos.writeObject(m);
				
			
				//����һ���߳�
				FileServerConClientThread fscct=new FileServerConClientThread(s);
				System.out.println("123");
				ManageFileServerConClientThread.addFileServerConClientThread(userid,fscct);
				fscct.start();
				System.out.println("�Ѽ������");
			}
		}
		catch (Exception e) {
			System.out.println("�������ļ���������");
		}
	}
	
	
	public static void main(String[] args) {
		new FileSever();
		
	}
}
