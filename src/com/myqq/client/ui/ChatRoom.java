package com.myqq.client.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.myqq.client.assistui.ChatRoomPanel;
import com.myqq.common.Message;
import com.myqq.common.User;
import com.myqq.utils.Constants;
import com.myqq.utils.MusicTest2;
import com.myqq.utils.PictureUtil;

/**
 * 
 * Description: 聊天室	<br/>  
 * Date: 2014年11月26日 下午5:35:10    <br/>
 * @author   SongFei
 * @version  
 * @since    JDK 1.7
 * @see
 */
public class ChatRoom extends JFrame {
	
	/** 主面板  */
	private JPanel contentPane;
	/** 最小化按钮  */
	private JLabel minButton;
	/** 最大化按钮  */
	private JLabel exitButton;
	/** 提示信息（与***聊天中） */
	public JLabel titleLabel;
	/** 下方聊天窗体 */
	private JPanel downPanel;
	/** 窗体面板（可合并）  */
	//public WebTabbedPane tabbedPane;
	/** 坐标（用于记录鼠标拖拽时，鼠标按下那一刻的坐标） */
	public ChatRoomPanel sp;
	private Point point = new Point();
	
	
	
	public ChatRoom(User self,User friend) {
		super();
		initGUI(self,friend);
		initListener();
		setLocation(800,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ChatRoom(new User("荆小六", "账号", "String passwd", "个性签名个性签名", "tx.jpg"), new User("荆小六", "账号", "String passwd", "个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名个性签名", "tx.gif"));
	}
	private void initGUI(User self,User friend) {
		try {
			setSize(660, 550);
			setUndecorated(true);
//			TODO 这个API导致输入中文白屏
//			AWTUtilities.setWindowOpaque(this, false);
//			TODO 这个地方注意不要用exit，不然在任务栏关闭room的时候就会导致进程退出
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setIconImage(PictureUtil.getPicture("qq_icon.png").getImage());
			
			// 主面板
			contentPane = new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(PictureUtil.getPicture("chat_bg.png").getImage(), 0, 0, null);
					this.setOpaque(false);
				}
			};
			contentPane.setLayout(null);
			contentPane.setBorder(Constants.LIGHT_GRAY_BORDER);
			setContentPane(contentPane);
			
//			titleLabel = new JLabel("和"+friend.getNick_name()+"聊天中");
//			titleLabel.setFont(Constants.BASIC_FONT);
//			titleLabel.setBounds(10, 0, 619, 30);
//			contentPane.add(titleLabel);
			
			// 聊天窗口合并面板
			downPanel = new JPanel();
			contentPane.add(downPanel);
			downPanel.setOpaque(false);
			downPanel.setBounds(1, 2, 658, 519);
			downPanel.setLayout(new BorderLayout());
			
			minButton = new JLabel();
			contentPane.add(minButton);
			minButton.setBounds(593, 0, 31, 20);
			minButton.setIcon(PictureUtil.getPicture("minimize.png"));
			
			exitButton = new JLabel();
			contentPane.add(exitButton);
			exitButton.setBounds(621, 0, 39, 20);
			exitButton.setIcon(PictureUtil.getPicture("close.png"));

//			tabbedPane = new WebTabbedPane();
//			downPanel.add(tabbedPane, BorderLayout.CENTER);
//			tabbedPane.setOpaque(false);
//			tabbedPane.setTabbedPaneStyle(TabbedPaneStyle.attached);//不高亮边框
////	        tabbedPane.setTabStretchType(TabStretchType.always);//适应宽度
//	        tabbedPane.setTopBg(new Color(240, 240, 240, 60));
//	        tabbedPane.setBottomBg(new Color(255, 255, 255, 160));
//	        tabbedPane.setSelectedTopBg(new Color(240, 240, 255, 50));
//	        tabbedPane.setSelectedBottomBg(new Color(240, 240, 255, 50));
//	        tabbedPane.setBackground(new Color(255, 255, 255, 200));
//			TODO 这个地方为什么自己打开就没问题，消息监听强行打开窗口就报错导致线程阻塞
//			tabbedPane.setUI(new MyTabbedPaneUI(tabbedPane));
	        
	        
	       sp = new ChatRoomPanel(self,friend,this);
	       downPanel.add(sp);
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void initListener() {
		// 主窗体事件
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
		// 任务栏右键关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				
			}
		});
		// 最小化按钮事件
		minButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				minButton.setIcon(PictureUtil.getPicture("minimize.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				minButton.setIcon(PictureUtil.getPicture("minimize_active.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		// 退出按钮事件
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
				// TODO 清空记录窗体数据
				
			}
		});
	}



	public void showMessage(Message m) {
		// TODO 自动生成的方法存根
		try { 
			new MusicTest2("D:/代码/JAVA/MyGFQQ/src/com/myqq/client/sounds/msg.wav").play(); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		
		
			sp.receive(m);
		
	}
	
	public void shake(){
		new Thread(){
			long begin = System.currentTimeMillis();
			long end = System.currentTimeMillis();
			Point p =ChatRoom.this.getLocationOnScreen();
			public void run(){
				int i = 1;
				while((end-begin)/1000<2){
					ChatRoom.this.setLocation(new Point((int)p.getX()-5*i,(int)p.getY()+5*i));
					end = System.currentTimeMillis();
					try {
						Thread.sleep(5);
						i=-i;
						ChatRoom.this.setLocation(p);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
}
