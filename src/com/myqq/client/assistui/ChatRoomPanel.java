package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.myqq.client.Mange.ManageClientConServerThread;
import com.myqq.client.ui.ChatRoom;
import com.myqq.common.ChatPic;
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
	public JTextPane historyTextPane;
	/** ������� */
	private JPanel tools;
	/** ������ť */
	private JLabel screen;
	/** ������ť */
	private JLabel shake;
	/** ���鰴ť */
	private JLabel emoticon;
	/** �ļ�����*/
	private JLabel file;
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
	private JButton sendButton;
	
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
	private StringBuffer imgBuffer = new StringBuffer();
	public Emoticon image;
	
	
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
			setSize(500, 456);
			
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
			historyTextPane = new JTextPane();
			historyTextPane.setEditable(false);//������༭
			
			//historyTextPane.setLineWrap(true);//�����Զ����й���
			//historyTextPane.setWrapStyleWord(true);//������в����ֹ���
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
			textFont.setIcon(PictureUtil.getPicture("font.png"));
			
			emoticon = new JLabel();
			tools.add(emoticon);
			emoticon.setBounds(40, 3, 20, 20);
			emoticon.setToolTipText("����");
			emoticon.setIcon(PictureUtil.getPicture("face.png"));
			
			shake = new JLabel();
			tools.add(shake);
			shake.setBounds(70, 3, 20, 20);
			shake.setToolTipText("����");
			shake.setIcon(PictureUtil.getPicture("shake.png"));
			
			screen = new JLabel();
			tools.add(screen);
			screen.setBounds(100, 3, 20, 20);
			screen.setToolTipText("����");
			screen.setIcon(PictureUtil.getPicture("screen.png"));
			
			file = new JLabel();
			tools.add(file);
			file.setBounds(130, 3, 20, 20);
			file.setToolTipText("�����ļ�");
			file.setIcon(PictureUtil.getPicture("file.png"));
			
			// �����
			input = new JPanel();
			add(input);
			input.setBounds(3, 370, 650, 120);
			input.setLayout(new BorderLayout());
			
			inputScroll = new JScrollPane();
			input.add(inputScroll);
			inputTextPane = new JTextPane();
			inputScroll.setViewportView(inputTextPane);
			inputScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
			inputScroll.setBorder(Constants.LIGHT_GRAY_BORDER);
			inputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			// ȡ����ť���رգ�
			quitButton = new JButton();
			add(quitButton);
			quitButton.setBounds(450, 492, 93, 28);
			quitButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			//quitButton.setText("�ر�");
			
			quitButton.setIcon(PictureUtil.getPicture("quitButton.jpg"));
//			quitButton.setIcon(new ImageIcon(PictureUtil.getPicture("quitButton.jpg")
//					.getImage().getScaledInstance(93, 30, Image.SCALE_DEFAULT)));
			// ���Ͱ�ť
			sendButton = new JButton();
			add(sendButton);
			sendButton.setBounds(550, 492, 93, 28);
			sendButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			//sendButton.setText("����");
			sendButton.setIcon(PictureUtil.getPicture("send_btn.jpg"));
//			sendButton.setIcon(new ImageIcon(PictureUtil.getPicture("send_btn.png")
//					.getImage().getScaledInstance(93, 30, Image.SCALE_DEFAULT)));
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
			fontPane.setBounds(3, 310, 450, 25);
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
//			fontBackColor = new JComboBox(str_BackColor); // ������ɫ
//			fontBackColor.setFont(Constants.BASIC_FONT2);
			
			// ��ʼ�����������������
			JLabel jlabel_1 = new JLabel("���壺"); 
			jlabel_1.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_2 = new JLabel("��ʽ��");
			jlabel_2.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_3 = new JLabel("�ֺţ�");
			jlabel_3.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_4 = new JLabel("��ɫ��");
			jlabel_4.setFont(Constants.BASIC_FONT2);
//			JLabel jlabel_5 = new JLabel("������");
//			jlabel_5.setFont(Constants.BASIC_FONT2);
			fontPane.add(jlabel_1); // �����ǩ
			fontPane.add(fontName); // �������
			fontPane.add(jlabel_2);
			fontPane.add(fontStyle);
			fontPane.add(jlabel_3);
			fontPane.add(fontSize);
			fontPane.add(jlabel_4);
			fontPane.add(fontForeColor);
//			fontPane.add(jlabel_5);
//			fontPane.add(fontBackColor);
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
				if (null == image) {
					image = new Emoticon(ChatRoomPanel.this);
					image.setVisible(true);
					// ���ÿؼ�����ڸ������λ��
					Point loc = getLocationOnScreen();
					image.setBounds(loc.x + 10, loc.y + 30, 350, 300);
				}
				image.requestFocus();
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
		
		file.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				file.setBorder(BorderFactory.createEmptyBorder());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				file.setBorder(Constants.LIGHT_GRAY_BORDER);
			}
			@Override
			public void mouseReleased(MouseEvent e) {

				System.out.println("����������ͷ����ļ�����");
				//��������˵��Ҫ��˭���ļ�
				System.out.println("����������ͷ����ļ�����1111:"+self.getUserId()+"   "+friend.getUserId());
				Message m=new Message();
				m.setMesType(MessageType.message_fileRequest);
				m.setSender(self.getUserId());
				m.setGetter(friend.getUserId());
				m.setCon("�ļ�����");
				m.setSendTime(new java.util.Date().toString());
				
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e1) {
					//e1.printStackTrace();
					System.out.println("ChatRoomPanel�����ļ������쳣");
				}
				
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
						//alertWindow.showMessageDialog(null, "�������ݲ���Ϊ�գ����������룡", "������ʾ");
						System.out.println("����Ϊ��");
						inputTextPane.setText("");
					} else {
						SimpleAttributeSet attrset = new SimpleAttributeSet();
				        StyleConstants.setFontSize(attrset,24);
				        StyleConstants.setForeground(attrset, Color.BLUE);
					    Document docs = historyTextPane.getDocument();//����ı�����
					        try {
					            docs.insertString(docs.getLength(), TimeUtil.getCurrentTime()+"  "+ "���"+friend.getNick_name()+"��\n    "+msg+"\n", attrset);//���ı�����׷��
					        } catch (BadLocationException e1) {
					            e1.printStackTrace();
					        }
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
						historyTextPane.setCaretPosition(historyTextPane.getStyledDocument().getLength());
						
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
					//alertWindow.showMessageDialog(null, "�������ݲ���Ϊ�գ����������룡", "������ʾ");
					inputTextPane.setText("");
				} else {
					SimpleAttributeSet attrset = new SimpleAttributeSet();
			        StyleConstants.setFontSize(attrset,24);
			        StyleConstants.setForeground(attrset, Color.BLUE);
				        Document docs = historyTextPane.getDocument();//����ı�����
				        try {
				            docs.insertString(docs.getLength(), TimeUtil.getCurrentTime()+"  "+ "���"+friend.getNick_name()+"��\n    "+msg+"\n", attrset);//���ı�����׷��
				        } catch (BadLocationException e1) {
				            e1.printStackTrace();
				        }
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
						
					}
					
					historyTextPane.setCaretPosition(historyTextPane.getStyledDocument().getLength());
				
				}
				
				
			}
		});

	}
	
	public void receive(Message m) {
		// TODO �Զ����ɵķ������
		System.out.println("receive�յ�����������Ϣ");
		if(m.getMesType().equals(MessageType.message_face))
			historyTextPaneappend(TimeUtil.getCurrentTime()+"  "+friend.getNick_name()+"����˵��\n    ", Integer.valueOf(m.getCon()));
		else{
		SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,24);
        StyleConstants.setForeground(attrset, Color.RED);
        Document docs = historyTextPane.getDocument();//����ı�����
	    try {
	           docs.insertString(docs.getLength(), TimeUtil.getCurrentTime()+"  "+friend.getNick_name()+"����˵��\n    "+m.getCon()+"\n", attrset);//���ı�����׷��
	    } catch (BadLocationException e1) {
	            e1.printStackTrace();
	    }
		
		}
		
		historyTextPane.setCaretPosition(historyTextPane.getStyledDocument().getLength());
	}

	public void insertIcon(ChatPic icon) {
		
		//����ͼƬ
		historyTextPaneappend(TimeUtil.getCurrentTime()+"   ���"+friend.getNick_name()+"˵��\n  ", icon.getNumber());
	    
		//���鷢��������
		Message m=new Message();
		m.setMesType(MessageType.message_face);
		m.setSender(self.getUserId());
		m.setSendnickname(self.getNick_name());
		m.setGetter(friend.getUserId());
		m.setCon(icon.getNumber()+"");
		m.setSendTime(new java.util.Date().toString());
		
		try {
			ObjectOutputStream oos=new ObjectOutputStream
			(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
			oos.writeObject(m);
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		
		
	}
	
	private void historyTextPaneappend(String usertofriendandtime,int iconNumber){
		
		SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,24);
        
        Document docs = historyTextPane.getDocument();//����ı�����
	    StyledDocument doc = (StyledDocument) historyTextPane.getDocument();
	    Style style = doc.addStyle("StyleName", null);
	    StyleConstants.setIcon(style, PictureUtil.getPicture("face/"+iconNumber+".gif"));
	 
		try {
		     docs.insertString(docs.getLength(), usertofriendandtime, attrset);//���ı�����׷��
		     doc.insertString(doc.getLength(), "ignored text", style);
		     docs.insertString(docs.getLength(), "\n", attrset);//���ı�����׷��		           
		    } catch (BadLocationException e1) {
		            e1.printStackTrace();
		    }
		
		
		
	}
	
}
