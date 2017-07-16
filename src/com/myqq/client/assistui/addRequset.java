package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.myqq.sever.db.userdb;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;

public class addRequset extends JDialog {

	private JPanel contentPanel;
	private JLabel addLabel;
	private JLabel okButton;
	private JLabel quitButton;
	private JLabel exitButton;
	
	private Point point = new Point();
	
	private String self;
	private String friend;


	public addRequset( String firend, String string,int x,int y) {
		this.self = string;
		this.friend = firend;
		initGUI(x+150,y+500);
		initListener();
		setAlwaysOnTop(true);
	}
	
	public static void main(String[] args) {
		new addRequset("1", "2",100,200).setVisible(true);
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

		addLabel = new JLabel(this.friend+"想加你("+this.self+")为好友");
		addLabel.setBounds(89, 60, 200, 35);
		addLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		contentPanel.add(addLabel);
		
		okButton = new JLabel("同意", JLabel.CENTER);
		contentPanel.add(okButton);
		okButton.setOpaque(false);
		okButton.setBorder(null);
		okButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		okButton.setBounds(22, 123, 114, 33);
		okButton.setBackground(new Color(240, 245, 240, 60));
		
		quitButton = new JLabel("不同意", JLabel.CENTER);
		contentPanel.add(quitButton);
		quitButton.setOpaque(false);
		quitButton.setBorder(null);
		quitButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
		quitButton.setBounds(156, 123, 114, 33);
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
				//同意，sqlinsert
				userdb udb =new userdb();
				String sql="INSERT INTO gfqq_category_member(category_name,owner_id,member_id) VALUES ('我的好友','"+self+"','"+friend+"')";
				//执行sql
				System.out.println(sql);
				udb.operate(sql);
				sql="INSERT INTO gfqq_category_member(category_name,owner_id,member_id) VALUES ('我的好友','"+friend+"','"+self+"')";
				System.out.println(sql);
				udb.operate(sql);
				
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
