package com.myqq.client.Mange;
import java.util.HashMap;
public class ManageClientConServerThread {

	@SuppressWarnings("rawtypes")
	private static HashMap hm=new HashMap<String, ClientConServerThread>();
	
	//把创建好的ClientConServerThread放入到hm
	@SuppressWarnings("unchecked")
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//可以通过qqId取得该线程 
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
}
