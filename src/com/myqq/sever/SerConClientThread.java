package com.myqq.sever;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.myqq.client.Mange.MessageQueen;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
public class SerConClientThread  extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s)
	{
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s=s;
	}
	
	//�ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String iam)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		
		while(it.hasNext())
		{
			Message m=new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			//ȡ�������˵�id
			String onLineUserId=it.next().toString();
			try {
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
	}
	
	public void run()
	{
		
		while(true)
		{
			
			//������߳̾Ϳ��Խ��տͻ��˵���Ϣ.
			try {
				ObjectInputStream ois=null;
				Message m =null;
				if(s.isConnected())
				{ 
				ois=new ObjectInputStream(s.getInputStream());
				 m=(Message)ois.readObject();
				}
				
				//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if(m.getMesType().equals(MessageType.message_comm_mes)||m.getMesType().equals(MessageType.message_shake))
				{
					System.out.println(m.getSender()+" �� "+m.getGetter()+" ˵:"+m.getCon());
					//һ�����ת��.
					//ȡ�ý����˵�ͨ���߳�
					//���߾�ת��
					if(ManageClientThread.isOnLine(m.getGetter()))
					{
						System.out.println(m.getGetter()+"������");
						SerConClientThread sc=ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);
					}
					//�����߾ͼ������
					else{
						//������Ϣ����
						System.out.println(m.getGetter()+"������");
						ManageOnLineUser.msgList.add(new MessageQueen(m.getSender(), m.getGetter(),m));
					}
					
					for (Iterator iterator = ManageOnLineUser.msgList.iterator(); iterator.hasNext();) {
						MessageQueen msgq = (MessageQueen) iterator.next();
						
						if(ManageClientThread.isOnLine(msgq.getGeter())){
							//ת����Ϣ
							System.out.println(msgq.getGeter()+"����������");
							SerConClientThread sc=ManageClientThread.getClientThread(msgq.getGeter());
							ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
							oos.writeObject(msgq.getMsg());
							
							ManageOnLineUser.msgList.remove(msgq);
							
						}
						else{
							System.out.printf(msgq.getGeter()+"���ǲ�����");
							
						}
					}
				}
				else if(m.getMesType().equals(MessageType.message_get_onLineFriend))
				{
					System.out.println(m.getSender()+" Ҫ���ĺ���");
					//���ڷ������ĺ��Ѹ��ÿͻ��˷���.
					String res=ManageClientThread.getAllOnLineUserid();
					Message m2=new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				else if(m.getMesType().equals(MessageType.message_addFriend)){
					System.out.println(m.getSender()+"���"+m.getGetter()+"Ϊ����");
					if(ManageClientThread.isOnLine(m.getGetter()))
					{
						SerConClientThread sc=ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);
						System.out.println("���͸�"+m.getGetter()+":"+m.getSender()+"�����");
					}
					else{
						
					}
							
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
			
		}
		
		
	}
}
