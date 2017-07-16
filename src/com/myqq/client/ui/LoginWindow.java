package com.myqq.client.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.myqq.client.Mange.ManageMainWindow;
import com.myqq.client.Mange.QqClientUser;
import com.myqq.client.assistui.alertWindow;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.common.User;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;
import com.myqq.utils.StringUtil;



public class LoginWindow extends JDialog{
	/** 主面板 */
	private JPanel content;
	/** 最小化按钮 */
	private JLabel min_button;
	/** 退出按钮 */
	private JLabel exit_button;
	/** 头像 */
	private JLabel pictureLabel;
	/** 账号 */
	private JTextField userName_Field;
	/** 密码 */
	private JPasswordField passWord_Fiele;
	/** 保存密码Box */
	private JLabel savePassCheckBox;
	/** 保存密码Txt */
	private JLabel savePassLabel;
	/** 自动登录Box */
	private JLabel autoLoginCheckBox;
	/** 自动登录Txt */
	private JLabel autoLoginLabel;
	/** 登陆按钮 */
	private JLabel loginButton;
	/** 注册账号 */
	private JLabel registerLabel;
	/** 找回密码 */
	private JLabel findPassLabel;
	/** 坐标（拖动记录） */
	private Point point = new Point();
	
	private boolean isSavePass;
	private boolean isAutoLogin;
	
	private TrayIcon icon;  //托盘图标
	private SystemTray tray;//桌面的系统托盘
	
	
	
	public LoginWindow() {
		super();	
		initGUI();
		initTrayIcon();
		initListener();
		setLocation(700, 450);
		setVisible(true);
	}

	private void initGUI() {
		try {
			setSize(400, 300);
			setUndecorated(true);

			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			// 主面板
			content = new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(PictureUtil.getPicture("back0.png").getImage(), 0, 0, null);
					this.setOpaque(false);
				}
			};
			
			content.setLayout(null);
			//添加一个边框
			content.setBorder(Constants.GRAY_BORDER);
			getContentPane().add(content, BorderLayout.CENTER);
			
			// 头像区域
			pictureLabel = new JLabel();
			content.add(pictureLabel);
			pictureLabel.setBounds(25, 138, 90, 90);
			pictureLabel.setBorder(Constants.LIGHT_GRAY_BORDER);
			pictureLabel.setIcon(PictureUtil.getPicture("pttx.png"));
		
			// 账号
			userName_Field = new JTextField();
			content.add(userName_Field);
			userName_Field.setBounds(121, 138, 183, 30);
			userName_Field.setBorder(Constants.LIGHT_GRAY_BORDER);
				
			// 密码
			passWord_Fiele = new JPasswordField();
			content.add(passWord_Fiele);
			passWord_Fiele.setBounds(121, 168, 183, 30);
			passWord_Fiele.setBorder(Constants.LIGHT_GRAY_BORDER);
		
			// 最小化按钮
			min_button = new JLabel();
			content.add(min_button);
			min_button.setBounds(330, 0, 30, 20);
			min_button.setIcon(PictureUtil.getPicture("minimize.png"));
		
			// 关闭按钮
			exit_button = new JLabel();
			content.add(exit_button);
			exit_button.setBounds(358, 0, 40, 20);
			exit_button.setIcon(PictureUtil.getPicture("exit.png"));
		
			// 保存密码Box
			savePassCheckBox = new JLabel();
			content.add(savePassCheckBox);
			savePassCheckBox.setBounds(121, 205, 20, 22);
			savePassCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
		
			// 保存密码Txt
			savePassLabel = new JLabel("记住密码");
			content.add(savePassLabel);
			savePassLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
			savePassLabel.setBounds(147, 202, 54, 24);
		
			// 自动登录Box
			autoLoginCheckBox = new JLabel();
			content.add(autoLoginCheckBox);
			autoLoginCheckBox.setBounds(226, 205, 20, 22);
			autoLoginCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
		
			// 自动登录Txt
			autoLoginLabel = new JLabel("自动登录");
			content.add(autoLoginLabel);
			autoLoginLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
			autoLoginLabel.setBounds(255, 202, 52, 24);
		
			// 登陆按钮
			loginButton = new JLabel();
			content.add(loginButton);
			loginButton.setBounds(118, 238, 186, 40);
			loginButton.setIcon(PictureUtil.getPicture("loginbutton.png"));
			
			// 注册账号
			registerLabel = new JLabel("注册账号");
			content.add(registerLabel);
			registerLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
			registerLabel.setBounds(314, 138, 62, 24);
			
			// 找回密码
			findPassLabel = new JLabel("找回密码");
			content.add(findPassLabel);
			findPassLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
			findPassLabel.setBounds(314, 169, 62, 27);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//初始化系统托盘
	private void initTrayIcon() {
		if (SystemTray.isSupported()) {
			try {
				tray = SystemTray.getSystemTray();
				icon = new TrayIcon(PictureUtil.getPicture("qq_icon.png").getImage(), "GFQQ");
				icon.setImageAutoSize(true); //自动适应大小
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							setVisible(true);
							// 获取焦点
							requestFocus();
						}
					}
				});
				
				PopupMenu pm = new PopupMenu();
				MenuItem mit = new MenuItem("退出");
				mit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tray.remove(icon);
						System.exit(0);
					}
				});
				
				MenuItem mainPanel = new MenuItem("打开主面板");
				mainPanel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO 自动生成的方法存根
						setVisible(true);
					}
				});
				
				pm.add(mainPanel);
				pm.add(mit);
				icon.setPopupMenu(pm);
				tray.add(icon);
				// 放到client类中
				
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
		// 最小化按钮事件
		min_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				min_button.setIcon(PictureUtil.getPicture("minimize.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				min_button.setIcon(PictureUtil.getPicture("minimize_active.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setVisible(false);
			}
		});
		// 退出按钮事件
		exit_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				exit_button.setIcon(PictureUtil.getPicture("exit.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit_button.setIcon(PictureUtil.getPicture("exit_active.png"));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				tray.remove(icon);
				System.exit(0);
			}
		});
		// 账号输入框焦点事件
		userName_Field.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				userName_Field.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void focusGained(FocusEvent e) {
				userName_Field.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			}
		});
		userName_Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					登录
				}
			}
		});
		// 密码输入框焦点事件
		passWord_Fiele.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				passWord_Fiele.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void focusGained(FocusEvent e) {
				passWord_Fiele.setBorder(Constants.ORANGE_BORDER);
			}
		});
		passWord_Fiele.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					//login();
				}
			}
		});
		// 保存密码复选框事件
		savePassCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isSavePass) {
					savePassCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
					isSavePass = false;
					autoLoginCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
					isAutoLogin = false;
				} else {
					savePassCheckBox.setIcon(PictureUtil.getPicture("xuanzhong.png"));
					isSavePass = true;
				}
			}
		});
		// 保存密码字体事件
		savePassLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isSavePass) {
					savePassCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
					isSavePass = false;
					autoLoginCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
					isAutoLogin = false;
				} else {
					savePassCheckBox.setIcon(PictureUtil.getPicture("xuanzhong.png"));
					isSavePass = true;
				}
			}
		});
		// 自动登录复选框事件
		autoLoginCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isAutoLogin) {
					autoLoginCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
					isAutoLogin = false;
				} else {
					autoLoginCheckBox.setIcon(PictureUtil.getPicture("xuanzhong.png"));
					isAutoLogin = true;
					savePassCheckBox.setIcon(PictureUtil.getPicture("xuanzhong.png"));
					isSavePass = true;
				}
			}
		});
		// 自动登录字体事件
		autoLoginLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isAutoLogin) {
					autoLoginCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
					isAutoLogin = false;
				} else {
					autoLoginCheckBox.setIcon(PictureUtil.getPicture("xuanzhong.png"));
					isAutoLogin = true;
					savePassCheckBox.setIcon(PictureUtil.getPicture("xuanzhong.png"));
					isSavePass = true;
				}
			}
		});
		// 登陆按钮事件
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setIcon(PictureUtil.getPicture("loginbutton.png"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setIcon(PictureUtil.getPicture("loginbutton.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
			
		});
		// 注册账号鼠标事件
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				registerLabel.setText("注册账号");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				registerLabel.setText("<html><u>注册账号</u><html>");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("注册账号模块");
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://118.89.243.102/zcqq");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		// 找回密码鼠标事件
		findPassLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				findPassLabel.setText("找回密码");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				findPassLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				findPassLabel.setText("<html><u>找回密码<html>");
			}
			public void mouseClicked(MouseEvent e) {
				System.out.println("找回密码模块");
				
					try {
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://118.89.243.102/zcqq/zhmm.php");
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
			}
		});
	}
	private void login() {
		// TODO 自动生成的方法存根
		String name = userName_Field.getText();
		String pass = String.valueOf(passWord_Fiele.getPassword());
		QqClientUser qqClientUser=new QqClientUser();
		
		User u=new User();
		u.setUserId(userName_Field.getText());
		u.setPasswd(String.valueOf(passWord_Fiele.getPassword()));
		
		if (StringUtil.isEmpty(name)) {
			alertWindow.showMessageDialog(null, "请输入账号！", "友情提示");
			return;
		}
		if (StringUtil.isEmpty(pass)) {
			alertWindow.showMessageDialog(null, "请输入密码！", "友情提示");
			return;
		}
		Message m  =qqClientUser.checkUser(u);
		
		
		if(m.getMesType().equals(MessageType.message_succeed))
		{			
			System.out.println("succeed");
			String []str = m.getCon().split(" ");
			u.setNick_name(str[0]);
			u.setUser_signature(str[1]);
			u.setImg(str[2]);
			MainWindow mw = new MainWindow(u);
			ManageMainWindow.addWindow(u.getUserId(), mw);
			//关闭掉登录界面
			this.dispose();
			tray.remove(icon);
		}else{
			alertWindow.showMessageDialog(null,"用户名密码错误","error");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginWindow loginWindow=new LoginWindow();
	}
	// 登录
	
}
