package com.myqq.client.Mange;

import java.util.HashMap;

import com.myqq.client.ui.MainWindow;

public class ManageMainWindow {
private static HashMap hm=new HashMap<String, MainWindow>();
	
	//����
	public static void addWindow(String own,MainWindow mw)
	{
		if(hm.get(own)==null)
			hm.put(own, mw);
	}
	//ȡ��
	public static MainWindow getWindow(String own )
	{
		return (MainWindow)hm.get(own);
	}
	
	public static boolean isNotExist(String own){
		return hm.get(own)==null;
	}
	public static void RemoveWindow(String string) {
		// TODO �Զ����ɵķ������
		hm.remove(getWindow(string));
	}
	
	
}
