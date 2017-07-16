package com.myqq.client.FileMange;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
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
	
	//���캯��
	public FileClientConSerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
				
				filename = m.getCon();
				String pathname= "D:\\"+filename;
				System.out.println("�ļ�����"+m.getCon());
				
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
					 
					System.out.println("��ȡ���ӷ��������ļ�"+ m.getSender() +" �� "+m.getGetter()+" ����"+
								m.getCon()); 
						 
					
				}
				
				System.out.println("�������");
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
}
