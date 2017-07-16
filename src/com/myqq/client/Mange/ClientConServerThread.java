package com.myqq.client.Mange;
import java.io.IOException;
/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
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
				
				if(m.getMesType().equals(MessageType.message_comm_mes)
						||m.getMesType().equals(MessageType.message_face))
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
				else if(m.getMesType().equals(MessageType.message_addFriend))
						
				{
					System.out.println(m.getSender()+"���"+m.getGetter()+"Ϊ����");
					MainWindow mw = ManageMainWindow.getWindow(m.getGetter());
					mw.showAddRequest(m);
			
				}
				else if(m.getMesType().equals(MessageType.message_fileRequest)){
					System.out.println("sa"+m.getSender()+"��"+m.getGetter()+"����һ���ļ�");
					ChatRoom chatRoom = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					
					if(chatRoom!=null)
					{
						chatRoom.setVisible(true);
						System.out.println(m.getSender()+"�ͻ����յ��ļ�����"+m.getGetter());
						chatRoom.showAddRequest(m.getSender(),m.getGetter());
					}
					else{
						userdb udb =new userdb();
						ChatRoom chatRoom2 = new ChatRoom(udb.getUserFromUserid(m.getGetter()),udb.getUserFromUserid(m.getSender()));
						ManageQqChat.addQqChat(m.getGetter()+" "+m.getSender(), chatRoom2);					
						System.out.println("�ͻ����յ��ļ�����2");
						chatRoom2.showAddRequest(m.getSender(),m.getGetter());
					}
					
					
				}
				//��ʼ�����ļ�
				else if(m.getMesType().equals(MessageType.message_agreeFileRequest)){
					System.out.println("��ͬ������ļ��������������ļ�");		
					System.out.println(m.getGetter()+","+ m.getSender());
					sendFileToServer.send(m.getGetter(), m.getSender());
				}
				else if(m.getMesType().equals(MessageType.message_File)){
					//����byte
//					JFileChooser jfilefhooser = new JFileChooser();
//					jfilefhooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//					jfilefhooser.showSaveDialog(ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender()));  
//			        File file = jfilefhooser.getSelectedFile();  
//			        FileOutputStream fos = new FileOutputStream(file); 
//					  
//				    // ��ʼ�����ļ� 
//				       do { 
//				    	  byte[] bytes = new byte[1024]; 
//				    	  if(m.getCon().equals("over"))
//				    		  break;
//				    	  bytes = m.getBuf();
//				    	  
//				          fos.write(bytes, 0, Integer.valueOf(m.getCon())); 
//				          fos.flush(); 
//				          System.out.println("���ڽ����ļ�");
//				          m=(Message)ois.readObject();
//				        }while(m.getMesType().equals(MessageType.message_File));
//				       System.out.println("�������");
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
