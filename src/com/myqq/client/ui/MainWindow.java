package com.myqq.client.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.alee.laf.tabbedpane.TabStretchType;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.client.assistui.AddFriendWindow;
import com.myqq.client.assistui.FriendPanel;
import com.myqq.client.assistui.addRequset;
import com.myqq.client.assistui.fileRequest;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.common.User;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;
import com.qq.client.additionalFunction.changeSignature;

public class MainWindow extends JDialog  {
	/** ����� */
	private JPanel content;
	/** ������Ϣ��� */
	private JPanel baseInfo;
	/** ������Ϣ��� */
	private JPanel userInfo;
	/** ��������� */
	private JPanel searchInfo;
	
	/** ��ǣ����Ͻǣ� */
	private JLabel productInfo;
	/** ͷ�� */
	private JLabel picture;
	/** ǩ�� */
	private JLabel signature;
	/** �ǳ� */
	private JLabel nickName;
	
	/** ������壨��ϵ�ˡ�Ⱥ�顢�Ự�� */
	private WebTabbedPane typeInfo;
	
	/** ������ */
	private JTextField searchText;
	/** ������ť */
	private JLabel searchButton;
	/** ��ϵ����� */
	private FriendPanel friendPanel;
//	/** Ⱥ����� */
//	private GroupPanel groupPanel;
//	/** �Ự��� */
//	private RecentPanel recentPanel;
	/** ��С����ť */
	private JLabel minButton;
	/** Ƥ����ť */
	private JLabel skinButton;
	/** �˳���ť */
	private JLabel exitButton;
	private JLabel qqZone;
	private JLabel qqMail;
	/** ���Ĭ�ϱ���ɫ */
	private static Color defaultBgColor = Color.WHITE;
	/**�ײ�������*/
	private JLabel bottom_con;
	/** ���꣨���ڼ�¼�����קʱ����갴����һ�̵����꣩ */
	private Point point = new Point();
	
	private User user;

	private TrayIcon icon;
	private SystemTray tray;
	


	public MainWindow(User user) {
		super();
		
		this.user = user;
		initGUI();
		initTrayIcon();
		initListener();
		setLocation(900, 0);
		setVisible(true);
		requestFocus();
	}
public static void main(String[] args) {
	User u =new User("�Ƕ�", "1", "123", "����٣��ۼ�����.����٣��ۼ���������٣��ۼ���������٣��ۼ�����");
	u.setImg("tx/010.jpg");
	new MainWindow(u);
}
	private void initGUI() {
		try {

			setSize(300, 750);
			setAlwaysOnTop(true);
			setUndecorated(true);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
			// �����
			content = new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					//Ƥ��
					g.drawImage(PictureUtil.getPicture("bg.jpg").getImage(), 0, 0, null);
					this.setOpaque(false);
				}
			};
			content.setLayout(null);
			getContentPane().add(content, BorderLayout.CENTER);
			
			// ������Ϣ���
			baseInfo = new JPanel();
			content.add(baseInfo);
			baseInfo.setLayout(null);
			baseInfo.setOpaque(false);
			baseInfo.setBounds(0, 0, 300, 118);
			baseInfo.setBorder(Constants.GRAY_BORDER);
			
			productInfo = new JLabel();
			baseInfo.add(productInfo);
			productInfo.setBounds(24, 0, 45, 30);
			productInfo.setText(" GFQQ");
		
			picture = new JLabel();
			baseInfo.add(picture);
			picture.setBounds(7, 33, 66, 66);
			picture.setBorder(Constants.GRAY_BORDER);
			
			//ͷ��user.getImg()
			System.out.println("user"+user.getImg());
			
			picture.setIcon(new ImageIcon(PictureUtil.getPicture(user.getImg())
					.getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
		
			nickName = new JLabel();
			baseInfo.add(nickName);
			nickName.setFont(new Font("΢���ź�", Font.BOLD, 15));
			//�ǳ�
			nickName.setText(user.getNick_name());
			nickName.setBounds(80, 30, 156, 32);
		
			signature = new JLabel();
			baseInfo.add(signature);
			signature.setFont(new Font("΢���ź�", Font.PLAIN, 12));
			signature.setForeground(Color.WHITE);
			//��ǩ
			signature.setText(user.getUser_signature());
			signature.setToolTipText(user.getUser_signature());
			signature.setBounds(82, 56, 156, 32);
		
			skinButton = new JLabel();
			baseInfo.add(skinButton);
			skinButton.setBounds(205, 2, 33, 18);
			skinButton.setIcon(PictureUtil.getPicture("skin.png"));
		
			minButton = new JLabel();
			baseInfo.add(minButton);
			minButton.setBounds(233, 0, 31, 20);
			minButton.setIcon(PictureUtil.getPicture("minimize.png"));
		
			exitButton = new JLabel();
			baseInfo.add(exitButton);
			exitButton.setBounds(261, 0, 39, 20);
			exitButton.setIcon(PictureUtil.getPicture("close.png"));
			
			//QQ�ռ䣬QQ����
			qqZone = new JLabel();
			baseInfo.add(qqZone);
			qqZone.setBounds(80, 80, 200, 30);
			qqZone.setIcon(PictureUtil.getPicture("qqkj2.png"));
			
			
			
			
			
			// �������
			searchInfo = new JPanel();
			content.add(searchInfo);
			searchInfo.setLayout(null);
			searchInfo.setOpaque(false);
			searchInfo.setBounds(0, 117, 300, 32);
			searchInfo.setBorder(Constants.GRAY_BORDER);
			
			searchText = new JTextField();
			searchText.setText("   ��������ϵ�ˡ�Ⱥ��");
			searchInfo.add(searchText);
			searchText.setOpaque(false);
			searchText.setFocusable(false);
			searchText.setBounds(1, 5, 253, 30);
			searchText.setBorder(BorderFactory.createEmptyBorder());
		
			searchButton = new JLabel();
			searchInfo.add(searchButton);
			searchButton.setBounds(260, 0, 45, 30);
			searchButton.setOpaque(false);
			searchButton.setBackground(defaultBgColor);
			searchButton.setIcon(PictureUtil.getPicture("search.png"));
			
			// ���ѡ���ϵ�ˡ��Ự���
			userInfo = new JPanel();
			content.add(userInfo);
			userInfo.setLayout(new BorderLayout());
			userInfo.setOpaque(false);
			userInfo.setBounds(0, 148, 300, 550);
			userInfo.setBackground(defaultBgColor);
		
			// ������壨���ѡ���ϵ�ˡ��Ự��
			typeInfo = new WebTabbedPane();
			userInfo.add(typeInfo, BorderLayout.CENTER);
			typeInfo.setOpaque(false);
			typeInfo.setTabbedPaneStyle(TabbedPaneStyle.attached);//�������߿�
			typeInfo.setTabStretchType(TabStretchType.always);//��Ӧ���
			typeInfo.setTopBg(new Color(240, 240, 240, 60));
	        typeInfo.setBottomBg(new Color(255, 255, 255, 160));
	        typeInfo.setSelectedTopBg(new Color(240, 240, 255, 50));
	        typeInfo.setSelectedBottomBg(new Color(240, 240, 255, 50));
	        typeInfo.setBackground(new Color(255, 255, 255, 200));
			typeInfo.setBorder(Constants.GRAY_BORDER);
			
			// �����б�
			friendPanel = new FriendPanel(user,this);
			typeInfo.addTab(null, new ImageIcon(PictureUtil.getPicture("temp.png")
					.getImage().getScaledInstance(300, 35, Image.SCALE_DEFAULT)), friendPanel);

			 //Ⱥ���б�
	//		groupPanel = new GroupPanel();
//			typeInfo.addTab(null, new ImageIcon(PictureUtil.getPicture("tab_group.png")
//					.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)), groupPanel);
//
//			// �Ự�б������ϵ�ˣ�
//			recentPanel = new RecentPanel();
//			typeInfo.addTab(null, new ImageIcon(PictureUtil.getPicture("tab_recent.png")
//					.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)), recentPanel);
			
			
			
			//�ײ�������
			JPanel bottom = new JPanel();
			content.add(bottom);
			bottom.setLayout(new BorderLayout());
			bottom.setOpaque(false);
			bottom.setBounds(0,698, 300, 56);
			bottom.setBorder(Constants.GRAY_BORDER);
			
			bottom_con = new JLabel();
			bottom.add(bottom_con);
			bottom_con.setBounds(0, 0, 300, 54);
		//	bottom_con.setBorder(Constants.GRAY_BORDER);
			
//			bottom_con.setText("��Ӻ���");
			bottom_con.setIcon(new ImageIcon(PictureUtil.getPicture("bottom_con.png")
					.getImage().getScaledInstance(300, 54, Image.SCALE_DEFAULT)));
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void initTrayIcon() {
		if (SystemTray.isSupported()) {
			try {
				tray = SystemTray.getSystemTray();
				icon = new TrayIcon(PictureUtil.getPicture("/qq_icon.png").getImage(), user.getNick_name());
				icon.setImageAutoSize(true); //�Զ���Ӧ��С
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							setVisible(true);
							// ��ȡ����
							requestFocus();
							icon.setImage(PictureUtil.getPicture("/qq_icon.png").getImage());
						}
					}
				});
				
				PopupMenu pm = new PopupMenu();
				//ϵͳ�˵���
				MenuItem mit = new MenuItem("�˳�QQ");
				mit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tray.remove(icon);
						System.exit(0);
					}
				});
				
				
				MenuItem mit1 = new MenuItem("��������");
				pm.add(mit1);
				pm.add(mit);
				
				icon.setPopupMenu(pm);
				tray.add(icon);

			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		// ͷ�������¼����߿��ɫ��
		picture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				picture.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				picture.setBorder(Constants.ORANGE_BORDER);
			}
		});
		
		signature.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				new changeSignature(user.getUserId(), MainWindow.this).setVisible(true);;
				
			}
		});
		// ������ť�¼�
		skinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				skinButton.setIcon(PictureUtil.getPicture("skin.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				skinButton.setIcon(PictureUtil.getPicture("skin_active.png"));
				
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
				setVisible(false);
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
				tray.remove(icon);
				ManageClientConServerThread.getClientConServerThread(user.getUserId()).close();
				ManageClientConServerThread.getClientConServerThread(user.getUserId()).stop();
				System.exit(0);
				
			}
		});
		// ����������¼�
		searchText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					searchText.setOpaque(true);
					searchText.setFocusable(true);
					searchText.requestFocus();
					searchText.setText("");
					
			
			}
		});
		// �����򽹵��¼�
		searchText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				searchText.setOpaque(false);
				searchText.setFocusable(false);
				searchText.setText("   ��������ϵ�ˡ�Ⱥ��");
				searchText.updateUI();				
			}
		});
		
		bottom_con.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				new AddFriendWindow(user,MainWindow.this.getX(),MainWindow.this.getY()).setVisible(true);
				//new AddFriend(user);
				//friendPanel.loadTree(user);
			}
		});
	}
	public void showAddRequest(Message m) {
		// TODO �Զ����ɵķ������	
		if(m.getMesType().equals(MessageType.message_addFriend))
			new addRequset(m.getGetter(), m.getSender(),MainWindow.this.getX(),MainWindow.this.getY()).setVisible(true);
		
		//friendPanel.loadTree(user);
	}
	public void setSignature(String str) {
		// TODO �Զ����ɵķ������
		signature.setText(str);
		signature.setToolTipText(str);
	//	user.setUser_signature(str);
	}
	
	

	

}
		