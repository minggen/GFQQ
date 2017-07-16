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
	/** ����� */
	private JPanel content;
	/** ��С����ť */
	private JLabel min_button;
	/** �˳���ť */
	private JLabel exit_button;
	/** ͷ�� */
	private JLabel pictureLabel;
	/** �˺� */
	private JTextField userName_Field;
	/** ���� */
	private JPasswordField passWord_Fiele;
	/** ��������Box */
	private JLabel savePassCheckBox;
	/** ��������Txt */
	private JLabel savePassLabel;
	/** �Զ���¼Box */
	private JLabel autoLoginCheckBox;
	/** �Զ���¼Txt */
	private JLabel autoLoginLabel;
	/** ��½��ť */
	private JLabel loginButton;
	/** ע���˺� */
	private JLabel registerLabel;
	/** �һ����� */
	private JLabel findPassLabel;
	/** ���꣨�϶���¼�� */
	private Point point = new Point();
	
	private boolean isSavePass;
	private boolean isAutoLogin;
	
	private TrayIcon icon;  //����ͼ��
	private SystemTray tray;//�����ϵͳ����
	
	
	
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
			
			// �����
			content = new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(PictureUtil.getPicture("back0.png").getImage(), 0, 0, null);
					this.setOpaque(false);
				}
			};
			
			content.setLayout(null);
			//���һ���߿�
			content.setBorder(Constants.GRAY_BORDER);
			getContentPane().add(content, BorderLayout.CENTER);
			
			// ͷ������
			pictureLabel = new JLabel();
			content.add(pictureLabel);
			pictureLabel.setBounds(25, 138, 90, 90);
			pictureLabel.setBorder(Constants.LIGHT_GRAY_BORDER);
			pictureLabel.setIcon(PictureUtil.getPicture("pttx.png"));
		
			// �˺�
			userName_Field = new JTextField();
			content.add(userName_Field);
			userName_Field.setBounds(121, 138, 183, 30);
			userName_Field.setBorder(Constants.LIGHT_GRAY_BORDER);
				
			// ����
			passWord_Fiele = new JPasswordField();
			content.add(passWord_Fiele);
			passWord_Fiele.setBounds(121, 168, 183, 30);
			passWord_Fiele.setBorder(Constants.LIGHT_GRAY_BORDER);
		
			// ��С����ť
			min_button = new JLabel();
			content.add(min_button);
			min_button.setBounds(330, 0, 30, 20);
			min_button.setIcon(PictureUtil.getPicture("minimize.png"));
		
			// �رհ�ť
			exit_button = new JLabel();
			content.add(exit_button);
			exit_button.setBounds(358, 0, 40, 20);
			exit_button.setIcon(PictureUtil.getPicture("exit.png"));
		
			// ��������Box
			savePassCheckBox = new JLabel();
			content.add(savePassCheckBox);
			savePassCheckBox.setBounds(121, 205, 20, 22);
			savePassCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
		
			// ��������Txt
			savePassLabel = new JLabel("��ס����");
			content.add(savePassLabel);
			savePassLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
			savePassLabel.setBounds(147, 202, 54, 24);
		
			// �Զ���¼Box
			autoLoginCheckBox = new JLabel();
			content.add(autoLoginCheckBox);
			autoLoginCheckBox.setBounds(226, 205, 20, 22);
			autoLoginCheckBox.setIcon(PictureUtil.getPicture("buxuanzhong.png"));
		
			// �Զ���¼Txt
			autoLoginLabel = new JLabel("�Զ���¼");
			content.add(autoLoginLabel);
			autoLoginLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
			autoLoginLabel.setBounds(255, 202, 52, 24);
		
			// ��½��ť
			loginButton = new JLabel();
			content.add(loginButton);
			loginButton.setBounds(118, 238, 186, 40);
			loginButton.setIcon(PictureUtil.getPicture("loginbutton.png"));
			
			// ע���˺�
			registerLabel = new JLabel("ע���˺�");
			content.add(registerLabel);
			registerLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
			registerLabel.setBounds(314, 138, 62, 24);
			
			// �һ�����
			findPassLabel = new JLabel("�һ�����");
			content.add(findPassLabel);
			findPassLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
			findPassLabel.setBounds(314, 169, 62, 27);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//��ʼ��ϵͳ����
	private void initTrayIcon() {
		if (SystemTray.isSupported()) {
			try {
				tray = SystemTray.getSystemTray();
				icon = new TrayIcon(PictureUtil.getPicture("qq_icon.png").getImage(), "GFQQ");
				icon.setImageAutoSize(true); //�Զ���Ӧ��С
				icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							setVisible(true);
							// ��ȡ����
							requestFocus();
						}
					}
				});
				
				PopupMenu pm = new PopupMenu();
				MenuItem mit = new MenuItem("�˳�");
				mit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tray.remove(icon);
						System.exit(0);
					}
				});
				
				MenuItem mainPanel = new MenuItem("�������");
				mainPanel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO �Զ����ɵķ������
						setVisible(true);
					}
				});
				
				pm.add(mainPanel);
				pm.add(mit);
				icon.setPopupMenu(pm);
				tray.add(icon);
				// �ŵ�client����
				
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
		// ��С����ť�¼�
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
		// �˳���ť�¼�
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
		// �˺�����򽹵��¼�
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
//					��¼
				}
			}
		});
		// ��������򽹵��¼�
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
		// �������븴ѡ���¼�
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
		// �������������¼�
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
		// �Զ���¼��ѡ���¼�
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
		// �Զ���¼�����¼�
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
		// ��½��ť�¼�
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
		// ע���˺�����¼�
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				registerLabel.setText("ע���˺�");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				registerLabel.setText("<html><u>ע���˺�</u><html>");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("ע���˺�ģ��");
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://118.89.243.102/zcqq");
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
			}
		});
		// �һ���������¼�
		findPassLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				findPassLabel.setText("�һ�����");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				findPassLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				findPassLabel.setText("<html><u>�һ�����<html>");
			}
			public void mouseClicked(MouseEvent e) {
				System.out.println("�һ�����ģ��");
				
					try {
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://118.89.243.102/zcqq/zhmm.php");
					} catch (IOException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					
			}
		});
	}
	private void login() {
		// TODO �Զ����ɵķ������
		String name = userName_Field.getText();
		String pass = String.valueOf(passWord_Fiele.getPassword());
		QqClientUser qqClientUser=new QqClientUser();
		
		User u=new User();
		u.setUserId(userName_Field.getText());
		u.setPasswd(String.valueOf(passWord_Fiele.getPassword()));
		
		if (StringUtil.isEmpty(name)) {
			alertWindow.showMessageDialog(null, "�������˺ţ�", "������ʾ");
			return;
		}
		if (StringUtil.isEmpty(pass)) {
			alertWindow.showMessageDialog(null, "���������룡", "������ʾ");
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
			//�رյ���¼����
			this.dispose();
			tray.remove(icon);
		}else{
			alertWindow.showMessageDialog(null,"�û����������","error");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginWindow loginWindow=new LoginWindow();
	}
	// ��¼
	
}
