package com.myqq.client.Mange;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.myqq.common.Message;
import com.myqq.common.User;
import com.myqq.utils.Constants;
public class QqClientConServer {

	
	public  Socket s;
	
	//发送第一次请求
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
			//这里就是验证用户登录的地方
			if(ms.getMesType().equals("1"))
			{
				System.out.println("服务器传回登陆成功"+ms.getCon());
				//就创建一个该qq号和服务器端保持通讯连接得线程
				ClientConServerThread ccst=new ClientConServerThread(s);
				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUserId(), ccst);
				
			}else{
				//关闭Scoket
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
