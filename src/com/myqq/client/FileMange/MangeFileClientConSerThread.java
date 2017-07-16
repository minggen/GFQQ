package com.myqq.client.FileMange;

import java.util.HashMap;

import com.myqq.client.FileMange.FileClientConSerThread;

	public class MangeFileClientConSerThread {

	private static HashMap hm=new HashMap<String, FileClientConSerThread>();
	
	//把创建好的ClientConServerThread放入到hm
	public static void addFileClientConSerThread(String qqId,FileClientConSerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//可以通过qqId取得该线程 
	public static FileClientConSerThread FileClientConSerThread(String qqId)
	{
		return (FileClientConSerThread)hm.get(qqId);
	}
}
