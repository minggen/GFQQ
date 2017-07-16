package com.myqq.client.Mange;
import java.io.IOException;
/**
 * 这是客户端和服务器端保持通讯的线程.
 */
import java.io.ObjectInputStream;
import java.net.Socket;

import com.myqq.client.FileMange.sendFileToServer;
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
				
				if(m.getMesType().equals(MessageType.message_comm_mes)
						||m.getMesType().equals(MessageType.message_face))
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
				else if(m.getMesType().equals(MessageType.message_addFriend))
						
				{
					System.out.println(m.getSender()+"想加"+m.getGetter()+"为好友");
					MainWindow mw = ManageMainWindow.getWindow(m.getGetter());
					mw.showAddRequest(m);
			
				}
				else if(m.getMesType().equals(MessageType.message_fileRequest)){
					System.out.println("sa"+m.getSender()+"给"+m.getGetter()+"发送一个文件");
					ChatRoom chatRoom = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					
					if(chatRoom!=null)
					{
						chatRoom.setVisible(true);
						System.out.println(m.getSender()+"客户端收到文件请求"+m.getGetter());
						chatRoom.showAddRequest(m.getSender(),m.getGetter());
					}
					else{
						userdb udb =new userdb();
						ChatRoom chatRoom2 = new ChatRoom(udb.getUserFromUserid(m.getGetter()),udb.getUserFromUserid(m.getSender()));
						ManageQqChat.addQqChat(m.getGetter()+" "+m.getSender(), chatRoom2);					
						System.out.println("客户端收到文件请求2");
						chatRoom2.showAddRequest(m.getSender(),m.getGetter());
					}
					
					
				}
				//开始发送文件
				else if(m.getMesType().equals(MessageType.message_agreeFileRequest)){
					System.out.println("他同意接受文件，接下来发送文件");		
					System.out.println(m.getGetter()+","+ m.getSender());
					sendFileToServer.send(m.getGetter(), m.getSender());
				}
				else if(m.getMesType().equals(MessageType.message_File)){
					//接受byte
//					JFileChooser jfilefhooser = new JFileChooser();
//					jfilefhooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//					jfilefhooser.showSaveDialog(ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender()));  
//			        File file = jfilefhooser.getSelectedFile();  
//			        FileOutputStream fos = new FileOutputStream(file); 
//					  
//				    // 开始接收文件 
//				       do { 
//				    	  byte[] bytes = new byte[1024]; 
//				    	  if(m.getCon().equals("over"))
//				    		  break;
//				    	  bytes = m.getBuf();
//				    	  
//				          fos.write(bytes, 0, Integer.valueOf(m.getCon())); 
//				          fos.flush(); 
//				          System.out.println("正在接受文件");
//				          m=(Message)ois.readObject();
//				        }while(m.getMesType().equals(MessageType.message_File));
//				       System.out.println("接受完毕");
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
