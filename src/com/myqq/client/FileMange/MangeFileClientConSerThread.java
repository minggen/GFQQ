package com.myqq.client.FileMange;

import java.util.HashMap;

import com.myqq.client.FileMange.FileClientConSerThread;

	public class MangeFileClientConSerThread {

	private static HashMap hm=new HashMap<String, FileClientConSerThread>();
	
	//�Ѵ����õ�ClientConServerThread���뵽hm
	public static void addFileClientConSerThread(String qqId,FileClientConSerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//����ͨ��qqIdȡ�ø��߳� 
	public static FileClientConSerThread FileClientConSerThread(String qqId)
	{
		return (FileClientConSerThread)hm.get(qqId);
	}
}
