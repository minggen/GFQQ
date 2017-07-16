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
	/** 主面板 */
	private JPanel content;
	/** 基本信息面板 */
	private JPanel baseInfo;
	/** 好友信息面板 */
	private JPanel userInfo;
	/** 搜索框面板 */
	private JPanel searchInfo;
	
	/** 标记（左上角） */
	private JLabel productInfo;
	/** 头像 */
	private JLabel picture;
	/** 签名 */
	private JLabel signature;
	/** 昵称 */
	private JLabel nickName;
	
	/** 分类面板（联系人、群组、会话） */
	private WebTabbedPane typeInfo;
	
	/** 搜索框 */
	private JTextField searchText;
	/** 搜索按钮 */
	private JLabel searchButton;
	/** 联系人面板 */
	private FriendPanel friendPanel;
//	/** 群组面板 */
//	private GroupPanel groupPanel;
//	/** 会话面板 */
//	private RecentPanel recentPanel;
	/** 最小化按钮 */
	private JLabel minButton;
	/** 皮肤按钮 */
	private JLabel skinButton;
	/** 退出按钮 */
	private JLabel exitButton;
	private JLabel qqZone;
	private JLabel qqMail;
	/** 面板默认背景色 */
	private static Color defaultBgColor = Color.WHITE;
	/**底部工具栏*/
	private JLabel bottom_con;
	/** 坐标（用于记录鼠标拖拽时，鼠标按下那一刻的坐标） */
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
	User u =new User("星儿", "1", "123", "情深不寿，慧极必伤.情深不寿，慧极必伤情深不寿，慧极必伤情深不寿，慧极必伤");
	u.setImg("tx/010.jpg");
	new MainWindow(u);
}
	private void initGUI() {
		try {

			setSize(300, 750);
			setAlwaysOnTop(true);
			setUndecorated(true);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
			// 主面板
			content = new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					//皮肤
					g.drawImage(PictureUtil.getPicture("bg.jpg").getImage(), 0, 0, null);
					this.setOpaque(false);
				}
			};
			content.setLayout(null);
			getContentPane().add(content, BorderLayout.CENTER);
			
			// 基本信息面板
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
			
			//头像user.getImg()
			System.out.println("user"+user.getImg());
			
			picture.setIcon(new ImageIcon(PictureUtil.getPicture(user.getImg())
					.getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
		
			nickName = new JLabel();
			baseInfo.add(nickName);
			nickName.setFont(new Font("微软雅黑", Font.BOLD, 15));
			//昵称
			nickName.setText(user.getNick_name());
			nickName.setBounds(80, 30, 156, 32);
		
			signature = new JLabel();
			baseInfo.add(signature);
			signature.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			signature.setForeground(Color.WHITE);
			//个签
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
			
			//QQ空间，QQ邮箱
			qqZone = new JLabel();
			baseInfo.add(qqZone);
			qqZone.setBounds(80, 80, 200, 30);
			qqZone.setIcon(PictureUtil.getPicture("qqkj2.png"));
			
			
			
			
			
			// 搜索面板
			searchInfo = new JPanel();
			content.add(searchInfo);
			searchInfo.setLayout(null);
			searchInfo.setOpaque(false);
			searchInfo.setBounds(0, 117, 300, 32);
			searchInfo.setBorder(Constants.GRAY_BORDER);
			
			searchText = new JTextField();
			searchText.setText("   搜索：联系人、群组");
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
			
			// 好友、联系人、会话面板
			userInfo = new JPanel();
			content.add(userInfo);
			userInfo.setLayout(new BorderLayout());
			userInfo.setOpaque(false);
			userInfo.setBounds(0, 148, 300, 550);
			userInfo.setBackground(defaultBgColor);
		
			// 类型面板（好友、联系人、会话）
			typeInfo = new WebTabbedPane();
			userInfo.add(typeInfo, BorderLayout.CENTER);
			typeInfo.setOpaque(false);
			typeInfo.setTabbedPaneStyle(TabbedPaneStyle.attached);//不高亮边框
			typeInfo.setTabStretchType(TabStretchType.always);//适应宽度
			typeInfo.setTopBg(new Color(240, 240, 240, 60));
	        typeInfo.setBottomBg(new Color(255, 255, 255, 160));
	        typeInfo.setSelectedTopBg(new Color(240, 240, 255, 50));
	        typeInfo.setSelectedBottomBg(new Color(240, 240, 255, 50));
	        typeInfo.setBackground(new Color(255, 255, 255, 200));
			typeInfo.setBorder(Constants.GRAY_BORDER);
			
			// 好友列表
			friendPanel = new FriendPanel(user,this);
			typeInfo.addTab(null, new ImageIcon(PictureUtil.getPicture("temp.png")
					.getImage().getScaledInstance(300, 35, Image.SCALE_DEFAULT)), friendPanel);

			 //群组列表
	//		groupPanel = new GroupPanel();
//			typeInfo.addTab(null, new ImageIcon(PictureUtil.getPicture("tab_group.png")
//					.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)), groupPanel);
//
//			// 会话列表（最近联系人）
//			recentPanel = new RecentPanel();
//			typeInfo.addTab(null, new ImageIcon(PictureUtil.getPicture("tab_recent.png")
//					.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)), recentPanel);
			
			
			
			//底部工具栏
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
			
//			bottom_con.setText("添加好友");
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
				icon.setImageAutoSize(true); //自动适应大小
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							setVisible(true);
							// 获取焦点
							requestFocus();
							icon.setImage(PictureUtil.getPicture("/qq_icon.png").getImage());
						}
					}
				});
				
				PopupMenu pm = new PopupMenu();
				//系统菜单项
				MenuItem mit = new MenuItem("退出QQ");
				mit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tray.remove(icon);
						System.exit(0);
					}
				});
				
				
				MenuItem mit1 = new MenuItem("打开主界面");
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
		// 头像区域事件（边框变色）
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
		// 换肤按钮事件
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
				setVisible(false);
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
				tray.remove(icon);
				ManageClientConServerThread.getClientConServerThread(user.getUserId()).close();
				ManageClientConServerThread.getClientConServerThread(user.getUserId()).stop();
				System.exit(0);
				
			}
		});
		// 搜索框鼠标事件
		searchText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
					searchText.setOpaque(true);
					searchText.setFocusable(true);
					searchText.requestFocus();
					searchText.setText("");
					
			
			}
		});
		// 搜索框焦点事件
		searchText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				searchText.setOpaque(false);
				searchText.setFocusable(false);
				searchText.setText("   搜索：联系人、群组");
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
		// TODO 自动生成的方法存根	
		if(m.getMesType().equals(MessageType.message_addFriend))
			new addRequset(m.getGetter(), m.getSender(),MainWindow.this.getX(),MainWindow.this.getY()).setVisible(true);
		
		//friendPanel.loadTree(user);
	}
	public void setSignature(String str) {
		// TODO 自动生成的方法存根
		signature.setText(str);
		signature.setToolTipText(str);
	//	user.setUser_signature(str);
	}
	
	

	

}
		