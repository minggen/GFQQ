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
	//发送第一次请求
	public FileClientConServer(String uid)
	{
		this.uid=uid;
		Message ms = null;
		try {
//			s=new Socket("118.89.243.102",8888);
			
			s=new Socket(Constants.SERVER_IP,8888);
			
			//向服务器发送自己id
			Message m=new  Message();
			m.setSender(uid);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			
			//读服务器返回值，如果是ok即建立成功
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			ms=(Message)ois.readObject();
			if(ms.getCon().equals("ok"))
			{
				System.out.println("服务器传回socket建立成功"+ms.getCon());
				
				//就创建一个该qq号和服务器端保持通讯连接得线程
				FileClientConSerThread fccst=new FileClientConSerThread(s);
				//启动该通讯线程
				fccst.start();
				MangeFileClientConSerThread.addFileClientConSerThread(uid, fccst);
	
	
				
			}else{
				//关闭Scoket
				s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
}
