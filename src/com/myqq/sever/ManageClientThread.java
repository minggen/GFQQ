package com.myqq.sever;

import java.util.HashMap;
import java.util.Iterator;
public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//向hm中添加一个客户端通讯线程
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	//返回当前在线的人的情况
	public static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}

	public static boolean isOnLine(String getter) {
		// TODO 自动生成的方法存根
		Iterator it=hm.keySet().iterator();
		
		
		while(it.hasNext())
		{
			
			if(getter.equals(it.next().toString()))
				return true;
		
		}
		return false;
	}
}
