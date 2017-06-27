package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.client.ui.ChatRoom;
import com.myqq.common.Message;
import com.myqq.common.MessageType;
import com.myqq.common.User;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;
import com.myqq.utils.StringUtil;
import com.myqq.utils.TimeUtil;

public class ChatRoomPanel extends JPanel {

	/** ������Ϣ��� */
	private JPanel friendInfoPane;
	/** ����ͷ�� */
	private JLabel picture;
	/** �����ǳ� */
	private JLabel nickName;
	/** ����ǩ�� */
	private JLabel descript;
	/** ��ʷ��Ϣ��� */
	private JPanel history;
	/** ��ʷ��Ϣ������ */
	private JScrollPane historyScroll;
	/** ��ʷ��Ϣ���� */
	public JTextArea historyTextPane;
	/** ������� */
	private JPanel tools;
	/** ������ť */
	private JLabel screen;
	/** ������ť */
	private JLabel shake;
	/** ���鰴ť */
	private JLabel emoticon;
	/** ���尴ť */
	private JLabel textFont;
	/** ������Ϣ��� */
	private JPanel input;
	/** ������Ϣ������ */
	private JScrollPane inputScroll;
	/** ������Ϣ���� */
	private JTextPane inputTextPane;
	/** ȡ����ť */
	private JButton quitButton;
	/** ���Ͱ�ť */
	private JLabel sendButton;
	
	private JPanel fontPane;
	private JComboBox fontName = null;// ��������
	private JComboBox fontSize = null;// �ֺŴ�С
	private JComboBox fontStyle = null;// ������ʽ
	private JComboBox fontForeColor = null;// ������ɫ
	private JComboBox fontBackColor = null;// ���ֱ�����ɫ
	private String[] str_name = { "����", "����", "Dialog", "Gulim" };
	private String[] str_Size = { "15", "17", "19", "21", "23" };
	private String[] str_Style = { "����", "б��", "����", "��б��" };
	private String[] str_Color = { "��ɫ", "��ɫ", "��ɫ", "��ɫ" };
	private String[] str_BackColor = { "��ɫ", "��ɫ", "����", "����", "����", "����" };
	private ChatRoom chat;
	private boolean isFonting;// �Ƿ����ڱ༭����
	
	public User self;
	public User friend;
	
	
	private String msg;
	public int position;
	
	public ChatRoomPanel( User self, User friend,ChatRoom chat) {
		super();
		this.self = self;		
		this.friend = friend;
		initGUI(friend);
		initListener();
		this.chat = chat; 
	}
	
	private void initGUI(User friend) {
		try {
			setLayout(null);
			setOpaque(false);
			setSize(500, 450);
			
			// ������Ϣ���
			friendInfoPane = new JPanel();
			add(friendInfoPane);
			friendInfoPane.setBounds(0, 0, 635, 70);
			friendInfoPane.setLayout(null);
			friendInfoPane.setOpaque(false);
			
			picture = new JLabel();
			friendInfoPane.add(picture);
			picture.setBounds(3, 10, 55, 55);
			picture.setBorder(Constants.LIGHT_GRAY_BORDER);
			picture.setIcon(new ImageIcon(PictureUtil.getPicture(friend.getImg())
					.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
			
			nickName = new JLabel();
			friendInfoPane.add(nickName);
			nickName.setFont(new Font("΢���ź�", Font.PLAIN, 12));
			nickName.setText(friend.getNick_name());
			nickName.setBounds(70, 10, 402, 25);
			
			descript = new JLabel();
			friendInfoPane.add(descript);
			descript.setFont(Constants.BASIC_FONT2);
			descript.setBounds(70, 35, 402, 25);
			descript.setText(friend.getUser_signature());
			
			// ��ʾ��Ϣ
			history = new JPanel();
			add(history);
			history.setBounds(3, 70, 650, 270);
			history.setOpaque(false);
			history.setLayout(new BorderLayout());
			
			historyScroll = new JScrollPane();
			history.add(historyScroll, BorderLayout.CENTER);
			historyTextPane = new JTextArea();
			historyTextPane.setEditable(false);//������༭
			// �ӵ�������ô�õ����ԣ�jtextpane�о�Ȼ��jdk1.6���ϲ��ṩ������̫��ˬ��
			historyTextPane.setLineWrap(true);//�����Զ����й���
			historyTextPane.setWrapStyleWord(true);//������в����ֹ���
			historyScroll.setViewportView(historyTextPane);
			historyScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
			historyScroll.setBorder(Constants.LIGHT_GRAY_BORDER);
			
			// ������
			tools = new JPanel();
			add(tools);
			tools.setLayout(null);
			tools.setOpaque(false);
			tools.setBounds(0, 340, 650, 27);
			
			textFont = new JLabel();
			tools.add(textFont);
			textFont.setBounds(10, 3, 20, 20);
			textFont.setToolTipText("����");
			textFont.setIcon(PictureUtil.getPicture("text.png"));
			
			emoticon = new JLabel();
			tools.add(emoticon);
			emoticon.setBounds(40, 3, 20, 20);
			emoticon.setToolTipText("����");
			emoticon.setIcon(PictureUtil.getPicture("emoticon.png"));
			
			shake = new JLabel();
			tools.add(shake);
			shake.setBounds(70, 3, 20, 20);
			shake.setToolTipText("����");
			shake.setIcon(PictureUtil.getPicture("shake.png"));
			
			screen = new JLabel();
			tools.add(screen);
			screen.setBounds(100, 3, 20, 20);
			screen.setToolTipText("����");
			screen.setIcon(PictureUtil.getPicture("screenCapture.png"));
			
			// �����
			input = new JPanel();
			add(input);
			input.setBounds(3, 370, 650, 120);
			input.setLayout(new BorderLayout());
			
			inputScroll = new JScrollPane();
			input.add(inputScroll);
			inputTextPane = new JTextPane();
//			inputTextPane.setTabSize(2);//TAB������λ 
//			inputTextPane.setLineWrap(true);//�����Զ����й���
//			inputTextPane.setWrapStyleWord(true);//������в����ֹ���
			inputScroll.setViewportView(inputTextPane);
			inputScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
			inputScroll.setBorder(Constants.LIGHT_GRAY_BORDER);
			inputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			// ȡ����ť���رգ�
			quitButton = new JButton();
			add(quitButton);
			quitButton.setBounds(460, 496, 93, 30);
			quitButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			quitButton.setIcon(PictureUtil.getPicture("quitButton.png"));
			
			// ���Ͱ�ť
			sendButton = new JLabel();
			add(sendButton);
			sendButton.setBounds(560, 496, 93, 30);
			sendButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			sendButton.setIcon(PictureUtil.getPicture("sendButton.png"));
			
			// �༭���壨ֻ���˼���ʾ����
			fontPane = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					try { // ʹ��Windows�Ľ�����
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			add(fontPane);
			fontPane.setBounds(3, 310, 650, 25);
			fontPane.setBorder(Constants.LIGHT_GRAY_BORDER);
			fontPane.setLayout(new BoxLayout(fontPane, BoxLayout.X_AXIS));
			
			fontName = new JComboBox(str_name); // ��������
			fontName.setFont(Constants.BASIC_FONT2);
			fontSize = new JComboBox(str_Size); // �ֺ�
			fontSize.setFont(Constants.BASIC_FONT2);
			fontStyle = new JComboBox(str_Style); // ��ʽ
			fontStyle.setFont(Constants.BASIC_FONT2);
			fontForeColor = new JComboBox(str_Color); // ��ɫ
			fontForeColor.setFont(Constants.BASIC_FONT2);
			fontBackColor = new JComboBox(str_BackColor); // ������ɫ
			fontBackColor.setFont(Constants.BASIC_FONT2);
			
			// ��ʼ�����������������
			JLabel jlabel_1 = new JLabel("���壺"); 
			jlabel_1.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_2 = new JLabel("��ʽ��");
			jlabel_2.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_3 = new JLabel("�ֺţ�");
			jlabel_3.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_4 = new JLabel("��ɫ��");
			jlabel_4.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_5 = new JLabel("������");
			jlabel_5.setFont(Constants.BASIC_FONT2);
			fontPane.add(jlabel_1); // �����ǩ
			fontPane.add(fontName); // �������
			fontPane.add(jlabel_2);
			fontPane.add(fontStyle);
			fontPane.add(jlabel_3);
			fontPane.add(fontSize);
			fontPane.add(jlabel_4);
			fontPane.add(fontForeColor);
			fontPane.add(jlabel_5);
			fontPane.add(fontBackColor);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void initListener() {
		// ������-����
		textFont.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (!isFonting) {
					textFont.setBorder(BorderFactory.createEmptyBorder());
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				textFont.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//3, 70, 650, 240
				if (!isFonting) {
					isFonting = true;
					history.setBounds(3, 70, 650, 240);
					textFont.setBorder(Constants.LIGHT_GRAY_BORDER);
				} else {
					isFonting = false;
					textFont.setBorder(BorderFactory.createEmptyBorder());
					history.setBounds(3, 70, 650, 270);
				}
			}
		});
		// ������-����
		emoticon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				emoticon.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				emoticon.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		// ������-����
		shake.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				shake.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				shake.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//client.sendMsg(createMsg(Constants.SHAKE_MSG, null));
				//�Լ�����
				chat.shake();
				//�����������һ��������Ϣ
				Message m=new Message();
				m.setMesType(MessageType.message_shake);
				m.setSender(self.getUserId());
				m.setSendnickname(self.getNick_name());
				m.setGetter(friend.getUserId());
				m.setCon(msg);
				m.setSendTime(new java.util.Date().toString());
				
				//���͸�������.
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e1) {
					e1.printStackTrace();
					// TODO: handle exception
				}
				System.out.println("ssaf");
			}
		});
		// ������-����
		screen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				screen.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				screen.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
		});
		// �س�����
		inputTextPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				msg = inputTextPane.getText();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// ������Ϣ
					if (StringUtil.isEmpty(msg)) {
						alertWindow.showMessageDialog(null, "�������ݲ���Ϊ�գ����������룡", "������ʾ");
					} else {
						historyTextPane.append(TimeUtil.getCurrentTime()+"  "+ "���"+friend.getNick_name()+"��\n    "+msg+"\n");
						System.out.println("�����������"+msg);
						
						Message m=new Message();
						m.setMesType(MessageType.message_comm_mes);
						m.setSender(self.getUserId());
						m.setSendnickname(self.getNick_name());
						m.setGetter(friend.getUserId());
						m.setCon(msg);
						m.setSendTime(new java.util.Date().toString());
						inputTextPane.setText("");
						//���͸�������.
						try {
							ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
							oos.writeObject(m);
						} catch (Exception e1) {
							e1.printStackTrace();
							// TODO: handle exception
						}
						
					}
				}
			}
		});
		// ���Ͱ�ť�¼�
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// ������Ϣ
				msg = inputTextPane.getText();
				if (StringUtil.isEmpty(inputTextPane.getText())) {
					alertWindow.showMessageDialog(null, "�������ݲ���Ϊ�գ����������룡", "������ʾ");
				} else {
					historyTextPane.append(TimeUtil.getCurrentTime()+"  "+"���"+friend.getNick_name()+"��\n    "+msg+"\n");
					System.out.println("�����������"+msg);
					inputTextPane.setText("");
					Message m=new Message();
					m.setMesType(MessageType.message_comm_mes);
					m.setSender(self.getUserId());
					m.setSendnickname(self.getNick_name());
					m.setGetter(friend.getUserId());
					m.setCon(msg);
					m.setSendTime(new java.util.Date().toString());
					
					try {
						ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
						oos.writeObject(m);
					} catch (Exception e1) {
						e1.printStackTrace();
						// TODO: handle exception
					}
					
				}
				
				
			}
		});

	}

	public void receive(Message m) {
		// TODO �Զ����ɵķ������
		System.out.println("receive�յ�����������Ϣ");
		
		historyTextPane.append(TimeUtil.getCurrentTime()+"  "+m.getSendnickname()+"����˵��\n    "+m.getCon()+"\n");
	}


}
