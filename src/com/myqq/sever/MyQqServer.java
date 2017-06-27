package com.myqq.sever;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.common.User;
import com.myqq.sever.db.userdb;
public class MyQqServer {

	
	public MyQqServer()
	{
		
		try {
			
			//��9999����
			System.out.println("���Ƿ���������9999����");
			ServerSocket ss=new ServerSocket(9999);
			//����,�ȴ�����
			while(true)
			{
				Socket s=ss.accept();
				
				//���տͻ��˷�������Ϣ.
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("���������յ��û�id:"+u.getUserId()+"  ����:"+u.getPasswd());
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				
				User uu = new userdb().login(u.getUserId(), u.getPasswd());
				
				if(uu!=null)
				{
					//����һ���ɹ���½����Ϣ��
					System.out.println("yes");
					//�����û��б��һ;
					ManageOnLineUser.onLineUserlist.add(u.getUserId());
					
					String con = uu.getNick_name()+" "+uu.getUser_signature()+" "+uu.getImg();
					
					m.setCon(con);
					m.setMesType(MessageType.message_succeed);
					oos.writeObject(m);
					
					//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//������ÿͻ���ͨ�ŵ��߳�.
					scct.start();
					
					//��֪ͨ���������û�.
					scct.notifyOther(u.getUserId());
				}else{
					m.setMesType(MessageType.message_login_fail);
					oos.writeObject(m);
					//�ر�Socket
					s.close();
					
				}
				
				
			}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		
	}
	
}
