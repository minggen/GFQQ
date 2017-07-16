package com.myqq.sever;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.myqq.client.FileMange.FileSever;
import com.myqq.client.Mange.MessageQueen;
import com.myqq.common.Message;
import com.myqq.common.MessageType;

public class SerConClientThread extends Thread {

	Socket s;
	public ObjectInputStream oisf=null;
	
	public SerConClientThread(Socket s) {
		// 把服务器和该客户端的连接赋给s
		this.s = s;

	}

	// 让该线程去通知其它用户
	public void notifyOther(String iam) {
		// 得到所有在线的人的线程
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			// 取出在线人的id
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}
	}

	public void run() {

		while (true) {

			// 这里该线程就可以接收客户端的信息.
			try {
				ObjectInputStream ois = null;
				ois = new ObjectInputStream(s.getInputStream());
				Message m = null;
				m = (Message) ois.readObject();
				// 对从客户端取得的消息进行类型判断，然后做相应的处理
				if (m.getMesType().equals(MessageType.message_comm_mes)
						|| m.getMesType().equals(MessageType.message_shake)
						|| m.getMesType().equals(MessageType.message_addFriend)
						|| m.getMesType().equals(MessageType.message_face)
						) {
					System.out.println(m.getSender() + " 给 " + m.getGetter() + " 说:" + m.getCon());
					// 一会完成转发.
					// 取得接收人的通信线程
					// 在线就转发

					if (m.getMesType().equals(MessageType.message_fileRequest)) {
						System.out.println("服务器收到" + m.getSender() + "想给" + m.getGetter() + "发文件的请求");
					}
					if (ManageClientThread.isOnLine(m.getGetter())) {
						System.out.println(m.getGetter() + "在线了");
						SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);

					}
					// 不在线就加入队列
					else {
						// 加入消息队列
						System.out.println(m.getGetter() + "不在线");
						ManageOnLineUser.msgList.add(new MessageQueen(m.getSender(), m.getGetter(), m));
					}

					for (Iterator iterator = ManageOnLineUser.msgList.iterator(); iterator.hasNext();) {
						MessageQueen msgq = (MessageQueen) iterator.next();

						if (ManageClientThread.isOnLine(msgq.getGeter())) {
							// 转发消息
							System.out.println(msgq.getGeter() + "现在在线了");
							SerConClientThread sc = ManageClientThread.getClientThread(msgq.getGeter());
							ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
							oos.writeObject(msgq.getMsg());

							ManageOnLineUser.msgList.remove(msgq);

						} else {
							System.out.printf(msgq.getGeter() + "还是不在线");

						}
					}
				} 
				
				if (m.getMesType().equals(MessageType.message_agreeAddFriend)) {
					System.out.println("同意加好友");

				} 
				
				if (m.getMesType().equals(MessageType.message_agreeFileRequest)) {
					System.out.println(m.getSender() + "同意接受文件");

					SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);

				}
				
				if(m.getMesType().equals(MessageType.message_fileRequest)){
										
					SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
					
				}
	

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}
}
