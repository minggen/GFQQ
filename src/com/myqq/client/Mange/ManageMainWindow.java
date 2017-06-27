package com.myqq.client.Mange;

import java.util.HashMap;

import com.myqq.client.ui.MainWindow;

public class ManageMainWindow {
private static HashMap hm=new HashMap<String, MainWindow>();
	
	//加入
	public static void addWindow(String own,MainWindow mw)
	{
		if(hm.get(own)==null)
			hm.put(own, mw);
	}
	//取出
	public static MainWindow getWindow(String own )
	{
		return (MainWindow)hm.get(own);
	}
	
	public static boolean isNotExist(String own){
		return hm.get(own)==null;
	}
	public static void RemoveWindow(String string) {
		// TODO 自动生成的方法存根
		hm.remove(getWindow(string));
	}
	
	
}
