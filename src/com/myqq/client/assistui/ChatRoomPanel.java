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

	/** 好友信息面板 */
	private JPanel friendInfoPane;
	/** 好友头像 */
	private JLabel picture;
	/** 好友昵称 */
	private JLabel nickName;
	/** 好友签名 */
	private JLabel descript;
	/** 历史消息面板 */
	private JPanel history;
	/** 历史消息滚动条 */
	private JScrollPane historyScroll;
	/** 历史消息区域 */
	public JTextPane historyTextPane;
	/** 工具面板 */
	private JPanel tools;
	/** 截屏按钮 */
	private JLabel screen;
	/** 抖动按钮 */
	private JLabel shake;
	/** 表情按钮 */
	private JLabel emoticon;
	/** 文件传送*/
	private JLabel file;
	/** 字体按钮 */
	private JLabel textFont;
	/** 输入消息面板 */
	private JPanel input;
	/** 输入消息滚动条 */
	private JScrollPane inputScroll;
	/** 输入消息区域 */
	private JTextPane inputTextPane;
	/** 取消按钮 */
	private JButton quitButton;
	/** 发送按钮 */
	private JButton sendButton;
	
	private JPanel fontPane;
	private JComboBox fontName = null;// 字体名称
	private JComboBox fontSize = null;// 字号大小
	private JComboBox fontStyle = null;// 文字样式
	private JComboBox fontForeColor = null;// 文字颜色
	private JComboBox fontBackColor = null;// 文字背景颜色
	private String[] str_name = { "宋体", "黑体", "Dialog", "Gulim" };
	private String[] str_Size = { "15", "17", "19", "21", "23" };
	private String[] str_Style = { "常规", "斜体", "粗体", "粗斜体" };
	private String[] str_Color = { "黑色", "橙色", "黄色", "绿色" };
	private String[] str_BackColor = { "白色", "灰色", "淡红", "淡蓝", "淡黄", "淡绿" };
	private ChatRoom chat;
	private boolean isFonting;// 是否正在编辑字体
	
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
			
			// 好友信息面板
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
			nickName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			nickName.setText(friend.getNick_name());
			nickName.setBounds(70, 10, 402, 25);
			
			descript = new JLabel();
			friendInfoPane.add(descript);
			descript.setFont(Constants.BASIC_FONT2);
			descript.setBounds(70, 35, 402, 25);
			descript.setText(friend.getUser_signature());
			
			// 显示消息
			history = new JPanel();
			add(history);
			history.setBounds(3, 70, 650, 270);
			history.setOpaque(false);
			history.setLayout(new BorderLayout());
			
			historyScroll = new JScrollPane();
			history.add(historyScroll, BorderLayout.CENTER);
			historyTextPane = new JTextPane();
			historyTextPane.setEditable(false);//不允许编辑
			
			//historyTextPane.setLineWrap(true);//激活自动换行功能
			//historyTextPane.setWrapStyleWord(true);//激活断行不断字功能
			historyScroll.setViewportView(historyTextPane);
			historyScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
			historyScroll.setBorder(Constants.LIGHT_GRAY_BORDER);
			
			// 工具栏
			tools = new JPanel();
			add(tools);
			tools.setLayout(null);
			tools.setOpaque(false);
			tools.setBounds(0, 340, 650, 27);
			
			textFont = new JLabel();
			tools.add(textFont);
			textFont.setBounds(10, 3, 20, 20);
			textFont.setToolTipText("字体");
			textFont.setIcon(PictureUtil.getPicture("font.png"));
			
			emoticon = new JLabel();
			tools.add(emoticon);
			emoticon.setBounds(40, 3, 20, 20);
			emoticon.setToolTipText("表情");
			emoticon.setIcon(PictureUtil.getPicture("face.png"));
			
			shake = new JLabel();
			tools.add(shake);
			shake.setBounds(70, 3, 20, 20);
			shake.setToolTipText("抖动");
			shake.setIcon(PictureUtil.getPicture("shake.png"));
			
			screen = new JLabel();
			tools.add(screen);
			screen.setBounds(100, 3, 20, 20);
			screen.setToolTipText("截屏");
			screen.setIcon(PictureUtil.getPicture("screen.png"));
			
			file = new JLabel();
			tools.add(file);
			file.setBounds(130, 3, 20, 20);
			file.setToolTipText("发送文件");
			file.setIcon(PictureUtil.getPicture("file.png"));
			
			// 输入框
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
			
			// 取消按钮（关闭）
			quitButton = new JButton();
			add(quitButton);
			quitButton.setBounds(450, 492, 93, 28);
			quitButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			//quitButton.setText("关闭");
			
			quitButton.setIcon(PictureUtil.getPicture("quitButton.jpg"));
//			quitButton.setIcon(new ImageIcon(PictureUtil.getPicture("quitButton.jpg")
//					.getImage().getScaledInstance(93, 30, Image.SCALE_DEFAULT)));
			// 发送按钮
			sendButton = new JButton();
			add(sendButton);
			sendButton.setBounds(550, 492, 93, 28);
			sendButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			//sendButton.setText("发送");
			sendButton.setIcon(PictureUtil.getPicture("send_btn.jpg"));
//			sendButton.setIcon(new ImageIcon(PictureUtil.getPicture("send_btn.png")
//					.getImage().getScaledInstance(93, 30, Image.SCALE_DEFAULT)));
			// 编辑字体（只做了几个示例）
			fontPane = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					try { // 使用Windows的界面风格
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
			
			fontName = new JComboBox(str_name); // 字体名称
			fontName.setFont(Constants.BASIC_FONT2);
			fontSize = new JComboBox(str_Size); // 字号
			fontSize.setFont(Constants.BASIC_FONT2);
			fontStyle = new JComboBox(str_Style); // 样式
			fontStyle.setFont(Constants.BASIC_FONT2);
			fontForeColor = new JComboBox(str_Color); // 颜色
			fontForeColor.setFont(Constants.BASIC_FONT2);
//			fontBackColor = new JComboBox(str_BackColor); // 背景颜色
//			fontBackColor.setFont(Constants.BASIC_FONT2);
			
			// 开始将所需组件加入容器
			JLabel jlabel_1 = new JLabel("字体："); 
			jlabel_1.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_2 = new JLabel("样式：");
			jlabel_2.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_3 = new JLabel("字号：");
			jlabel_3.setFont(Constants.BASIC_FONT2);
			JLabel jlabel_4 = new JLabel("颜色：");
			jlabel_4.setFont(Constants.BASIC_FONT2);
//			JLabel jlabel_5 = new JLabel("背景：");
//			jlabel_5.setFont(Constants.BASIC_FONT2);
			fontPane.add(jlabel_1); // 加入标签
			fontPane.add(fontName); // 加入组件
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
		// 工具栏-字体
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
		// 工具栏-表情
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
					// 设置控件相对于父窗体的位置
					Point loc = getLocationOnScreen();
					image.setBounds(loc.x + 10, loc.y + 30, 350, 300);
				}
				image.requestFocus();
			}
		});
		// 工具栏-抖动
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
				//自己抖动
				chat.shake();
				//向服务器发送一个抖动消息
				Message m=new Message();
				m.setMesType(MessageType.message_shake);
				m.setSender(self.getUserId());
				m.setSendnickname(self.getNick_name());
				m.setGetter(friend.getUserId());
				m.setCon(msg);
				m.setSendTime(new java.util.Date().toString());
				
				//发送给服务器.
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
		// 工具栏-截屏
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

				System.out.println("向服务器发送发送文件请求");
				//给服务器说我要给谁传文件
				System.out.println("向服务器发送发送文件请求1111:"+self.getUserId()+"   "+friend.getUserId());
				Message m=new Message();
				m.setMesType(MessageType.message_fileRequest);
				m.setSender(self.getUserId());
				m.setGetter(friend.getUserId());
				m.setCon("文件请求");
				m.setSendTime(new java.util.Date().toString());
				
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(self.getUserId()).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e1) {
					//e1.printStackTrace();
					System.out.println("ChatRoomPanel发送文件请求异常");
				}
				
			}
		});
		
		
		// 回车发送
		inputTextPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				msg = inputTextPane.getText();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 发送消息
					if (StringUtil.isEmpty(msg)) {
						//alertWindow.showMessageDialog(null, "发送内容不能为空，请重新输入！", "友情提示");
						System.out.println("输入为空");
						inputTextPane.setText("");
					} else {
						SimpleAttributeSet attrset = new SimpleAttributeSet();
				        StyleConstants.setFontSize(attrset,24);
				        StyleConstants.setForeground(attrset, Color.BLUE);
					    Document docs = historyTextPane.getDocument();//获得文本对象
					        try {
					            docs.insertString(docs.getLength(), TimeUtil.getCurrentTime()+"  "+ "你对"+friend.getNick_name()+"：\n    "+msg+"\n", attrset);//对文本进行追加
					        } catch (BadLocationException e1) {
					            e1.printStackTrace();
					        }
						System.out.println("向服务器发送"+msg);
						
						Message m=new Message();
						m.setMesType(MessageType.message_comm_mes);
						m.setSender(self.getUserId());
						m.setSendnickname(self.getNick_name());
						m.setGetter(friend.getUserId());
						m.setCon(msg);
						m.setSendTime(new java.util.Date().toString());
						inputTextPane.setText("");
						//发送给服务器.
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
		// 发送按钮事件
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 发送消息
				msg = inputTextPane.getText();
				if (StringUtil.isEmpty(inputTextPane.getText())) {
					//alertWindow.showMessageDialog(null, "发送内容不能为空，请重新输入！", "友情提示");
					inputTextPane.setText("");
				} else {
					SimpleAttributeSet attrset = new SimpleAttributeSet();
			        StyleConstants.setFontSize(attrset,24);
			        StyleConstants.setForeground(attrset, Color.BLUE);
				        Document docs = historyTextPane.getDocument();//获得文本对象
				        try {
				            docs.insertString(docs.getLength(), TimeUtil.getCurrentTime()+"  "+ "你对"+friend.getNick_name()+"：\n    "+msg+"\n", attrset);//对文本进行追加
				        } catch (BadLocationException e1) {
				            e1.printStackTrace();
				        }
					System.out.println("向服务器发送"+msg);
					
					
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
		// TODO 自动生成的方法存根
		System.out.println("receive收到服务器的消息");
		if(m.getMesType().equals(MessageType.message_face))
			historyTextPaneappend(TimeUtil.getCurrentTime()+"  "+friend.getNick_name()+"对你说：\n    ", Integer.valueOf(m.getCon()));
		else{
		SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,24);
        StyleConstants.setForeground(attrset, Color.RED);
        Document docs = historyTextPane.getDocument();//获得文本对象
	    try {
	           docs.insertString(docs.getLength(), TimeUtil.getCurrentTime()+"  "+friend.getNick_name()+"对你说：\n    "+m.getCon()+"\n", attrset);//对文本进行追加
	    } catch (BadLocationException e1) {
	            e1.printStackTrace();
	    }
		
		}
		
		historyTextPane.setCaretPosition(historyTextPane.getStyledDocument().getLength());
	}

	public void insertIcon(ChatPic icon) {
		
		//插入图片
		historyTextPaneappend(TimeUtil.getCurrentTime()+"   你对"+friend.getNick_name()+"说：\n  ", icon.getNumber());
	    
		//表情发给服务器
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
        
        Document docs = historyTextPane.getDocument();//获得文本对象
	    StyledDocument doc = (StyledDocument) historyTextPane.getDocument();
	    Style style = doc.addStyle("StyleName", null);
	    StyleConstants.setIcon(style, PictureUtil.getPicture("face/"+iconNumber+".gif"));
	 
		try {
		     docs.insertString(docs.getLength(), usertofriendandtime, attrset);//对文本进行追加
		     doc.insertString(doc.getLength(), "ignored text", style);
		     docs.insertString(docs.getLength(), "\n", attrset);//对文本进行追加		           
		    } catch (BadLocationException e1) {
		            e1.printStackTrace();
		    }
		
		
		
	}
	
}
