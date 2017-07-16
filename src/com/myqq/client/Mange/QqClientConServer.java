package com.myqq.client.Mange;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.myqq.common.Message;
import com.myqq.common.User;
import com.myqq.utils.Constants;
public class QqClientConServer {

	
	public  Socket s;
	
	//���͵�һ������
	public Message sendLoginInfoToServer(Object o)
	{
		Message ms = null;
		
		
		try {
			//System.out.println("kk");
			//s=new Socket("118.89.243.102",9999);
			s=new Socket(Constants.SERVER_IP,9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			
			ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))
			{
				System.out.println("���������ص�½�ɹ�"+ms.getCon());
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUserId(), ccst);
				
			}else{
				//�ر�Scoket
				s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			return ms;
		}
		
	}
	
	
}
