package com.myqq.client.Mange;
import java.io.IOException;
/**
 * 这是客户端和服务器端保持通讯的线程.
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
	
	//构造函数
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的消息
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
				System.out.println("读取到从服务发来的消息"+ m.getSender() +" 给 "+m.getGetter()+" 内容"+
						m.getCon());
			
				System.out.println(ManageQqChat.isNotExist(m.getGetter()+" "+m.getSender()));
				
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{	
					//把从服务器获得消息，显示到该显示的聊天界面
					ChatRoom qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					System.out.println(m.getGetter()+" "+m.getSender());
					//显示
					if(qqChat!=null)
					qqChat.showMessage(m);
					else{
						System.out.println("是空的");
						
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
					System.out.println(m.getGetter()+"给"+m.getSender()+"发了一个窗口抖动");
					//显示
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
					System.out.println("客户端接收到"+m.getCon());
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					System.out.println("getter="+getter);
					//修改相应的好友列表.
					//QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
				//	if(qqFriendList)
					//更新在线好友.
//					if(qqFriendList!=null)
//					{
//						qqFriendList.upateFriend(m);
//					}
					
					
					/***
					 * 初始化好友列表
					 * 往FriendPanel加入update(m)函数,将MainWindow 加入管理类，更新好友列表
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
