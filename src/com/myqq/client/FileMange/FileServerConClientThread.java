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
		// �ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;
		
	}
	   
	public void run() {
		try {
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		while (true) {
			//һֱ���ܿͻ������룬ת����ȥ		
			try {
				Message m = null;
				m = (Message) ois.readObject();
				//����
				System.out.println("�ļ���----->"+m.getCon());
				//ת��
				//System.out.println("byte-->"+m.getBuf());
				System.out.println("������"+m.getSender()+"������"+m.getGetter());
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
//					System.out.println("��ȡ���ӷ��������ļ�"+ m.getSender() +" �� "+m.getGetter()+" ����"+
//								m.getCon()); 
//						 
//					
//				}
//				
//				System.out.println("�������");
//				fos.close();
				
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
					System.out.println("�ļ��l�ͮ���");
				}
		}
	}
}
