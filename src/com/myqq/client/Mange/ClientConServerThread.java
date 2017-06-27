package com.myqq.client.Mange;
import java.io.IOException;
/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
 */
import java.io.ObjectInputStream;
import java.net.Socket;

import com.myqq.client.ui.ChatRoom;
import com.myqq.client.ui.MainWindow;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.sever.db.userdb;
public class ClientConServerThread extends Thread {

	private Socket s;
	
	//���캯��
	public ClientConServerThread(Socket s)
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
				
				System.out.println("��ȡ���ӷ���������Ϣ"+ m.getSender() +" �� "+m.getGetter()+" ����"+
						m.getCon());
			
				System.out.println(ManageQqChat.isNotExist(m.getGetter()+" "+m.getSender()));
				
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{	
					//�Ѵӷ����������Ϣ����ʾ������ʾ���������
					ChatRoom qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					System.out.println(m.getGetter()+" "+m.getSender());
					//��ʾ
					if(qqChat!=null)
					qqChat.showMessage(m);
					else{
						System.out.println("�ǿյ�");
						
						userdb udb =new userdb();
						ChatRoom chatRoom = new ChatRoom(udb.getUserFromUserid(m.getGetter()),udb.getUserFromUserid(m.getSender()));
						ManageQqChat.addQqChat(m.getGetter()+" "+m.getSender(), chatRoom);
						chatRoom.showMessage(m);
						chatRoom.setVisible(false);				
						chatRoom.shake();
						
						
					}
				}
				else if(m.getMesType().equals(MessageType.message_shake)){
					ChatRoom qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					System.out.println(m.getGetter()+"��"+m.getSender()+"����һ�����ڶ���");
					//��ʾ
					if(qqChat!=null)
					{
						qqChat.setVisible(true);
						qqChat.shake();
					}
					else{
						userdb udb =new userdb();
						ChatRoom chatRoom = new ChatRoom(udb.getUserFromUserid(m.getGetter()),udb.getUserFromUserid(m.getSender()));
						ManageQqChat.addQqChat(m.getGetter()+" "+m.getSender(), chatRoom);					
						chatRoom.shake();
					}
				}
				else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					System.out.println("�ͻ��˽��յ�"+m.getCon());
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					System.out.println("getter="+getter);
					//�޸���Ӧ�ĺ����б�.
					//QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
				//	if(qqFriendList)
					//�������ߺ���.
//					if(qqFriendList!=null)
//					{
//						qqFriendList.upateFriend(m);
//					}
					
					
					/***
					 * ��ʼ�������б�
					 * ��FriendPanel����update(m)����,��MainWindow ��������࣬���º����б�
					 */
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
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
