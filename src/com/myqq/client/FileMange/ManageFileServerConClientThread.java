package com.myqq.client.FileMange;


import java.util.HashMap;

public class ManageFileServerConClientThread {

	public static HashMap hm=new HashMap<String, FileServerConClientThread>();
	
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static  void addFileServerConClientThread(String uid,FileServerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static FileServerConClientThread getFileServerConClientThread(String uid)
	{
		return (FileServerConClientThread)hm.get(uid);
	}
	
	

}
