package com.myqq.client.FileMange;


import java.util.HashMap;

public class ManageFileServerConClientThread {

	public static HashMap hm=new HashMap<String, FileServerConClientThread>();
	
	//向hm中添加一个客户端通讯线程
	public static  void addFileServerConClientThread(String uid,FileServerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static FileServerConClientThread getFileServerConClientThread(String uid)
	{
		return (FileServerConClientThread)hm.get(uid);
	}
	
	

}
