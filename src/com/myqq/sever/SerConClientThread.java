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
		// �ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;

	}

	// �ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String iam) {
		// �õ��������ߵ��˵��߳�
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			// ȡ�������˵�id
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

			// ������߳̾Ϳ��Խ��տͻ��˵���Ϣ.
			try {
				ObjectInputStream ois = null;
				ois = new ObjectInputStream(s.getInputStream());
				Message m = null;
				m = (Message) ois.readObject();
				// �Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if (m.getMesType().equals(MessageType.message_comm_mes)
						|| m.getMesType().equals(MessageType.message_shake)
						|| m.getMesType().equals(MessageType.message_addFriend)
						|| m.getMesType().equals(MessageType.message_face)
						) {
					System.out.println(m.getSender() + " �� " + m.getGetter() + " ˵:" + m.getCon());
					// һ�����ת��.
					// ȡ�ý����˵�ͨ���߳�
					// ���߾�ת��

					if (m.getMesType().equals(MessageType.message_fileRequest)) {
						System.out.println("�������յ�" + m.getSender() + "���" + m.getGetter() + "���ļ�������");
					}
					if (ManageClientThread.isOnLine(m.getGetter())) {
						System.out.println(m.getGetter() + "������");
						SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);

					}
					// �����߾ͼ������
					else {
						// ������Ϣ����
						System.out.println(m.getGetter() + "������");
						ManageOnLineUser.msgList.add(new MessageQueen(m.getSender(), m.getGetter(), m));
					}

					for (Iterator iterator = ManageOnLineUser.msgList.iterator(); iterator.hasNext();) {
						MessageQueen msgq = (MessageQueen) iterator.next();

						if (ManageClientThread.isOnLine(msgq.getGeter())) {
							// ת����Ϣ
							System.out.println(msgq.getGeter() + "����������");
							SerConClientThread sc = ManageClientThread.getClientThread(msgq.getGeter());
							ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
							oos.writeObject(msgq.getMsg());

							ManageOnLineUser.msgList.remove(msgq);

						} else {
							System.out.printf(msgq.getGeter() + "���ǲ�����");

						}
					}
				} 
				
				if (m.getMesType().equals(MessageType.message_agreeAddFriend)) {
					System.out.println("ͬ��Ӻ���");

				} 
				
				if (m.getMesType().equals(MessageType.message_agreeFileRequest)) {
					System.out.println(m.getSender() + "ͬ������ļ�");

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
