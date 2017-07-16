package com.qq.client.additionalFunction;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.client.assistui.FriendPanel;
import com.myqq.client.assistui.alertWindow;
import com.myqq.client.ui.MainWindow;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.sever.db.Categorydb;
import com.myqq.utils.StringUtil;
import com.myqq.utils.TimeUtil;

public class addCategory extends JDialog {
	private String self;
	
	
	private JTextField textField;
	
	
	public addCategory(String userId, MainWindow mw) {
		// TODO 自动生成的构造函数存根
		this.self = userId;
		initGUI(mw.getX(),mw.getY()+300);
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);		
		textField = new JTextField();
		textField.setBounds(0,0,190, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField.addKeyListener(new KeyAdapter() {
			private String str;
			@Override
			public void keyPressed(KeyEvent e) {
				 str = textField.getText();
				 System.out.println("按键");
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 发送消息
					System.out.println("回车");
					if (!StringUtil.isEmpty(str)) {
						//插入数据库
				String sql = "INSERT INTO gfqq_category(name,owner_id,category_type) VALUES ('"+str+"', '"+self+"', '1')";						
				System.out.println(sql);	
				new Categorydb().operate(sql);
				
				dispose();
					}
				}
			}
			
		});
		
		
		textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				dispose();
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
	}
	

	
	private void initGUI(int d, int e) {
		setSize(190, 20);
		System.out.println(d+","+e);
		setLocation(d+30, e+60);
		setUndecorated(true);
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	
}
