package com.myqq.client.assistui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.common.User;

public class AddFriend	extends JFrame {
	public JPanel contJpanel;
	public JLabel jbable = new JLabel("输入账号：");
	public JTextField text =new JTextField(10);
	public JButton btn =new JButton("添加好友");
	public String self="1";
	public String friend;
	
	public AddFriend(User u){
		contJpanel=new JPanel();
		this.setContentPane(contJpanel);
		self=u.getUserId();
				
		contJpanel.add(jbable);
		text.setSize(100, 50);
		contJpanel.add(text);
		contJpanel.add(btn);
		setBounds(800,600,400,100);
		setVisible(true);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				friend = text.getText();
				text.setText("");
				if(friend.equals("")){
					System.out.println("输入QQ号");
				}else{
					System.out.println("向服务器请求添加"+friend+"为好友");
					
					Message m=new Message();
					m.setMesType(MessageType.message_addFriend);
					m.setSender(self);
					m.setGetter(friend);
					
					//发送给服务器.
					try {
						ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServerThread(self).getS().getOutputStream());
						oos.writeObject(m);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
				}
			}
		});
	}
	public static void main(String[] args) {
		//new AddFriend();
	}
	
	
}
