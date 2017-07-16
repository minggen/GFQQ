package com.myqq.client.FileMange;

import java.awt.Container;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.myqq.client.Mange.ClientConServerThread;
import com.myqq.common.Message;
import com.myqq.utils.Constants;

public class FileClientConServer {

	public  Socket s;
	public String uid;
	//���͵�һ������
	public FileClientConServer(String uid)
	{
		this.uid=uid;
		Message ms = null;
		try {
//			s=new Socket("118.89.243.102",8888);
			
			s=new Socket(Constants.SERVER_IP,8888);
			
			//������������Լ�id
			Message m=new  Message();
			m.setSender(uid);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			
			//������������ֵ�������ok�������ɹ�
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			ms=(Message)ois.readObject();
			if(ms.getCon().equals("ok"))
			{
				System.out.println("����������socket�����ɹ�"+ms.getCon());
				
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				FileClientConSerThread fccst=new FileClientConSerThread(s);
				//������ͨѶ�߳�
				fccst.start();
				MangeFileClientConSerThread.addFileClientConSerThread(uid, fccst);
	
	
				
			}else{
				//�ر�Scoket
				s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
}
