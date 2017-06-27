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
 * Description: ������	<br/>  
 * Date: 2014��11��26�� ����5:35:10    <br/>
 * @author   SongFei
 * @version  
 * @since    JDK 1.7
 * @see
 */
public class ChatRoom extends JFrame {
	
	/** �����  */
	private JPanel contentPane;
	/** ��С����ť  */
	private JLabel minButton;
	/** ��󻯰�ť  */
	private JLabel exitButton;
	/** ��ʾ��Ϣ����***�����У� */
	public JLabel titleLabel;
	/** �·����촰�� */
	private JPanel downPanel;
	/** ������壨�ɺϲ���  */
	//public WebTabbedPane tabbedPane;
	/** ���꣨���ڼ�¼�����קʱ����갴����һ�̵����꣩ */
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
		new ChatRoom(new User("��С��", "�˺�", "String passwd", "����ǩ������ǩ��", "tx.jpg"), new User("��С��", "�˺�", "String passwd", "����ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ������ǩ��", "tx.gif"));
	}
	private void initGUI(User self,User friend) {
		try {
			setSize(660, 550);
			setUndecorated(true);
//			TODO ���API�����������İ���
//			AWTUtilities.setWindowOpaque(this, false);
//			TODO ����ط�ע�ⲻҪ��exit����Ȼ���������ر�room��ʱ��ͻᵼ�½����˳�
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setIconImage(PictureUtil.getPicture("qq_icon.png").getImage());
			
			// �����
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
			
//			titleLabel = new JLabel("��"+friend.getNick_name()+"������");
//			titleLabel.setFont(Constants.BASIC_FONT);
//			titleLabel.setBounds(10, 0, 619, 30);
//			contentPane.add(titleLabel);
			
			// ���촰�ںϲ����
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
//			tabbedPane.setTabbedPaneStyle(TabbedPaneStyle.attached);//�������߿�
////	        tabbedPane.setTabStretchType(TabStretchType.always);//��Ӧ���
//	        tabbedPane.setTopBg(new Color(240, 240, 240, 60));
//	        tabbedPane.setBottomBg(new Color(255, 255, 255, 160));
//	        tabbedPane.setSelectedTopBg(new Color(240, 240, 255, 50));
//	        tabbedPane.setSelectedBottomBg(new Color(240, 240, 255, 50));
//	        tabbedPane.setBackground(new Color(255, 255, 255, 200));
//			TODO ����ط�Ϊʲô�Լ��򿪾�û���⣬��Ϣ����ǿ�д򿪴��ھͱ������߳�����
//			tabbedPane.setUI(new MyTabbedPaneUI(tabbedPane));
	        
	        
	       sp = new ChatRoomPanel(self,friend,this);
	       downPanel.add(sp);
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void initListener() {
		// �������¼�
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
		// �������Ҽ��ر�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				
			}
		});
		// ��С����ť�¼�
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
		// �˳���ť�¼�
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
				// TODO ��ռ�¼��������
				
			}
		});
	}



	public void showMessage(Message m) {
		// TODO �Զ����ɵķ������
		try { 
			new MusicTest2("D:/����/JAVA/MyGFQQ/src/com/myqq/client/sounds/msg.wav").play(); 
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
