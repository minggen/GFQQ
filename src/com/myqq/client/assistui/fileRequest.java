package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.myqq.client.FileMange.receiveFileFromServer;
import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;

public class fileRequest extends JDialog {

	private JPanel contentPanel;
	private JLabel addLabel;
	private JLabel okButton;
	private JLabel quitButton;
	private JLabel exitButton;
	
	private Point point = new Point();
	
	private String self;
	private String friend;


	public fileRequest( String firend, String slef,int x,int y) {
		System.out.println("发送文件需要"+self+"同意");
		this.self = slef;
		this.friend = firend;
		initGUI(x+660,y);
		initListener();
		setAlwaysOnTop(true);
	}
	
	public static void main(String[] args) {
		new fileRequest("1", "2",0,0).setVisible(true);
	}
	private void initGUI(int x, int y) {
		setSize(220, 550);
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

		addLabel = new JLabel(this.friend+"给你发送了一个文件");
		addLabel.setBounds(33, 77, 177, 35);
		addLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		contentPanel.add(addLabel);
		
		okButton = new JLabel("接受", JLabel.CENTER);
		okButton.setForeground(Color.RED);
		contentPanel.add(okButton);
		okButton.setOpaque(false);
		okButton.setBorder(null);
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		okButton.setBounds(10, 149, 63, 33);
		okButton.setBackground(Color.WHITE);
		
		quitButton = new JLabel("拒绝接受", JLabel.CENTER);
		quitButton.setForeground(Color.DARK_GRAY);
		contentPanel.add(quitButton);
		quitButton.setOpaque(false);
		quitButton.setBorder(null);
		quitButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		quitButton.setBounds(123, 149, 63, 33);
		quitButton.setBackground(new Color(240, 245, 240, 60));
		
		
		exitButton = new JLabel();
		
		exitButton.setBounds(181, 0, 40, 20);
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
				//同意，发送同意

				System.out.println("客户端收到文件请求,弹出是否同意窗口");
				Message m=new Message();
				m.setMesType(MessageType.message_agreeFileRequest);
				m.setSender(self);
				
				m.setGetter(friend);
				m.setSendTime(new java.util.Date().toString());
				
				
				System.out.println("创建一个"+self+"--->socket");
								System.out.println(self+"同意接受文件");
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(self).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e1) {
					e1.printStackTrace();		
				}
				
				System.out.println("同意，发送给服务器");
				
				receiveFileFromServer.receivefile(m.getSender());
				
				
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
				//不同意
				dispose();
			}
		});
	}
	
}
