package com.myqq.sever;

import java.util.HashMap;
import java.util.Iterator;
public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	//���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid()
	{
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}

	public static boolean isOnLine(String getter) {
		// TODO �Զ����ɵķ������
		Iterator it=hm.keySet().iterator();
		
		
		while(it.hasNext())
		{
			
			if(getter.equals(it.next().toString()))
				return true;
		
		}
		return false;
	}
}
