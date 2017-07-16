package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.myqq.common.ChatPic;

public class Emoticon extends JDialog implements FocusListener, WindowFocusListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JScrollPane jScrollPane1;

	private JLabel label[] = new JLabel[143];
	private String marks[] = new String[label.length];
	
	private ChatRoomPanel chatPane;
	
	public Emoticon(ChatRoomPanel chatPane) {
		this.chatPane = chatPane;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		setUndecorated(true);
		setAlwaysOnTop(true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new GridLayout(12, 0));
		
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(contentPane);
		getContentPane().add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getVerticalScrollBar().setUI(new MyScrollBarUI());
		// ���κ��������
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		String fileName = "";
		for (int i = 0; i < label.length; i++) {
			fileName = "D:/����/JAVA/MyGFQQ/src/com/myqq/client/imgs/face/" + i + ".gif";
			label[i] = new JLabel(new ChatPic(fileName, i), SwingConstants.CENTER);
			label[i].setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
			label[i].setToolTipText(":)" + i);
			marks[i] = ":)" + i;

			label[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel temp = (JLabel) e.getSource();
					temp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel temp = (JLabel) e.getSource();
					temp.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel temp = (JLabel) e.getSource();
					ChatPic pic = (ChatPic) temp.getIcon();
					chatPane.insertIcon(pic);
					dispose();
					chatPane.image = null;
				}
			});

			contentPane.add(label[i]);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		setVisible(false);
		dispose();
		chatPane.image = null;
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		setVisible(false);
		dispose();
		chatPane.image = null;
	}

}
