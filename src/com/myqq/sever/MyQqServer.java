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
			
			//在9999监听
			System.out.println("我是服务器，在9999监听");
			ServerSocket ss=new ServerSocket(9999);
			//阻塞,等待连接
			while(true)
			{
				Socket s=ss.accept();
				
				//接收客户端发来的信息.
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("服务器接收到用户id:"+u.getUserId()+"  密码:"+u.getPasswd());
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				
				User uu = new userdb().login(u.getUserId(), u.getPasswd());
				
				if(uu!=null)
				{
					//返回一个成功登陆的信息报
					System.out.println("yes");
					//在线用户列表加一;
					ManageOnLineUser.onLineUserlist.add(u.getUserId());
					
					String con = uu.getNick_name()+" "+uu.getUser_signature()+" "+uu.getImg();
					
					m.setCon(con);
					m.setMesType(MessageType.message_succeed);
					oos.writeObject(m);
					
					//这里就单开一个线程，让该线程与该客户端保持通讯.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//启动与该客户端通信的线程.
					scct.start();
					
					//并通知其它在线用户.
					scct.notifyOther(u.getUserId());
				}else{
					m.setMesType(MessageType.message_login_fail);
					oos.writeObject(m);
					//关闭Socket
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
