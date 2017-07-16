package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.client.ui.MainWindow;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.common.User;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;
import com.myqq.utils.StringUtil;

public class AddFriendWindow extends JDialog {

	private JPanel contentPanel;
	private JLabel addLabel;
	private JTextField textField;
	private JLabel okButton;
	private JLabel quitButton;
	private JLabel exitButton;
	
	private Point point = new Point();
	
	private User self;

	public AddFriendWindow(User u,int x,int y) {
		initGUI(x+150,y+500);
		initListener();
		this.self = u;
		setAlwaysOnTop(true);
	}
	

	private void initGUI(int x, int y) {
		setSize(300, 180);
		setLocation(x, y);
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		contentPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(PictureUtil.getPicture("back4.jpg").getImage(), 0, 0, null);
				this.setOpaque(false);
			}
		};
		contentPanel.setLayout(null);
		contentPanel.setBorder(Constants.LIGHT_GRAY_BORDER);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		addLabel = new JLabel("对方账号（昵称）");
		addLabel.setBounds(7, 55, 114, 35);
		addLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		contentPanel.add(addLabel);
		
		textField = new JTextField();
		textField.setBounds(112, 58, 180, 30);
		contentPanel.add(textField);
		
		okButton = new JLabel("请求添加为好友", JLabel.CENTER);
		contentPanel.add(okButton);
		okButton.setOpaque(false);
		okButton.setBorder(null);
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		okButton.setBounds(45, 123, 114, 33);
		okButton.setBackground(new Color(240, 245, 240, 60));
		
		quitButton = new JLabel("取消", JLabel.CENTER);
		contentPanel.add(quitButton);
		quitButton.setOpaque(false);
		quitButton.setBorder(null);
		quitButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		quitButton.setBounds(140, 123, 114, 33);
		quitButton.setBackground(new Color(240, 245, 240, 60));
		
		exitButton = new JLabel();
		exitButton.setBounds(261, 0, 40, 20);
		contentPanel.add(exitButton);
		exitButton.setIcon(PictureUtil.getPicture("close.png"));
	}
	
	private void initListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(PictureUtil.getPicture("close.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(PictureUtil.getPicture("close_active.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			
			}
		});
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				textField.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void focusGained(FocusEvent e) {
				textField.setBorder(Constants.ORANGE_BORDER);
			}
		});
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				okButton.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				okButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				String friend = textField.getText();
				if (StringUtil.isEmpty(friend)) {
					alertWindow.showMessageDialog(null, "请输入账号或昵称！", "友情提示");
					return;
				}
				//服务器发送加好友请求
				System.out.println("向服务器请求添加"+friend+"为好友");
				
				Message m=new Message();
				m.setMesType(MessageType.message_addFriend);
				m.setSender(self.getUserId());
				m.setGetter(friend);
//				
//				//发送给服务器.
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setBorder(null);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
	}
	
}
