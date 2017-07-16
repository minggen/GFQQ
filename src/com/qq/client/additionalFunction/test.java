package com.qq.client.additionalFunction;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

import com.myqq.client.ui.MainWindow;
import com.myqq.utils.StringUtil;

public class test extends JDialog {
	private String self;
	
	private MainWindow mainWindow;
	private JTextField textField;
	
	
	public test(String userId, MainWindow mainWindow) {
		// TODO 自动生成的构造函数存根
		this.self = self;
		this.mainWindow =mainWindow;
		initGUI(mainWindow.getX(),mainWindow.getY());
		//initGUI(0, 0);
		initListener();
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);		
		textField = new JTextField();
		textField.setBounds(0, 0, 180, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
	}

	public static void main(String[] args) {
		new test( "2",null).setVisible(true);
	}
	private void initGUI(int x, int y) {
		setSize(180, 30);
		//setLocation(x, y);
		setUndecorated(true);
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void initListener() {
		textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				String str = textField.getText();
				if(StringUtil.isEmpty(str)){
					mainWindow.setSignature(str);
					//修改数据库
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
	}
}
