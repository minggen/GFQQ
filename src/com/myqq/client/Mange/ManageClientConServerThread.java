package com.myqq.client.Mange;
import java.util.HashMap;
public class ManageClientConServerThread {

	private static HashMap hm=new HashMap<String, ClientConServerThread>();
	
	//�Ѵ����õ�ClientConServerThread���뵽hm
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//����ͨ��qqIdȡ�ø��߳� 
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
}
