package com.myqq.client.assistui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;
/**
 * 
 * @author Hello
 *��ʾ��
 */
public class alertWindow {

	private static int result = 0;

	/**
	 * ��Ϣ��
	 * @param owner ������
	 * @param title ����
	 * @param content ����
	 */
	public static void showMessageDialog(Component owner, String content, String title) {
		JDialog dialog = createMessageDialog(owner, title, content);
		if (null == owner) {
			dialog.setLocationRelativeTo(null);
		} else {
			dialog.setLocationRelativeTo(owner);
		}
		dialog.setVisible(true);
	}

	/**
	 * ȷ�Ͽ�
	 * @param owner ������
	 * @param title ����
	 * @param content ����
	 * @return 0��ȷ�� 1��ȡ��
	 */
	public static int showConfirmDialog(Component owner, String title, 
			String content, String okButtonStr, String quitButtonStr) {
		JDialog dialog = createConfirmDialog(owner, title, content, okButtonStr, quitButtonStr);
		if (null == owner) {
			dialog.setLocationRelativeTo(null);
		} else {
			dialog.setLocationRelativeTo(owner);
		}
		dialog.setVisible(true);
		return result;
	}

	private static JDialog createConfirmDialog(Component owner, String title, 
			String content, String okButtonStr, String quitButtonStr) {
		final Point point = new Point();

	
		final JDialog dialog = getDialog(owner, title);
		dialog.setUndecorated(true);
		dialog.setSize(320, 170);
		dialog.getContentPane().setLayout(null);

		JPanel topPane = new JPanel();
		topPane.setLayout(null);
		dialog.getContentPane().add(topPane);
		topPane.setBounds(0, 0, 320, 31);
		topPane.setBackground(Color.CYAN);
		topPane.setBorder(Constants.LIGHT_GRAY_BORDER);

		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(10, 0, 281, 25);
		topPane.add(titleLabel);
		titleLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));

		final JLabel exitButton = new JLabel();
		exitButton.setBounds(281, 0, 40, 20);
		exitButton.setIcon(PictureUtil.getPicture("close.png"));
		topPane.add(exitButton);

		JPanel downPane = new JPanel();
		downPane.setLayout(null);
		downPane.setBackground(Color.WHITE);
		dialog.getContentPane().add(downPane);
		downPane.setBounds(0, 30, 320, 140);
		downPane.setBorder(Constants.LIGHT_GRAY_BORDER);

		
		JTextArea text = new JTextArea(content);
		text.setFont(Constants.DIALOG_FONT);// ����
		text.setEditable(false);// ������༭
		text.setLineWrap(true);// �����Զ����й���
		text.setWrapStyleWord(true);// ������в����ֹ���
		text.setBounds(48, 25, 220, 70);
		text.setOpaque(false);
		downPane.add(text);

		JPanel interiorPane = new JPanel();
		interiorPane.setBounds(1, 100, 318, 37);
		downPane.add(interiorPane);
		interiorPane.setBackground(Color.WHITE);
		interiorPane.setLayout(null);

		final JLabel okButton = new JLabel(okButtonStr, JLabel.CENTER);
		okButton.setBounds(130, 10, 93, 25);
		okButton.setFont(new Font("΢���ź�", Font.BOLD, 12));
		interiorPane.add(okButton);

		final JLabel quitButton = new JLabel(quitButtonStr, JLabel.CENTER);
		quitButton.setBounds(217, 10, 93, 25);
		quitButton.setFont(new Font("΢���ź�", Font.BOLD, 12));
		interiorPane.add(quitButton);

		dialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		dialog.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = dialog.getLocation();
				dialog.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
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
				dialog.dispose();
				result = 1;
			}
		});
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				okButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				okButton.setBorder(null);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dialog.dispose();
				result = 0;
			}
		});
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setBorder(null);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dialog.dispose();
				result = 1;
			}
		});

		return dialog;
	}

	private static JDialog createMessageDialog(Component owner, String title, String content) {
		final Point point = new Point();
		
		final JDialog dialog = getDialog(owner, title);
		dialog.setUndecorated(true);
		dialog.setSize(320, 170);
		dialog.setLayout(null);

		JPanel topPane = new JPanel();
		topPane.setLayout(null);
		dialog.add(topPane);
		topPane.setBounds(0, 0, 320, 31);
		topPane.setBackground(Color.LIGHT_GRAY);
		topPane.setBorder(Constants.LIGHT_GRAY_BORDER);

		final JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(10, 0, 281, 25);
		topPane.add(titleLabel);
		titleLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));

		final JLabel exitButton = new JLabel();
		exitButton.setBounds(281, 0, 40, 20);
		exitButton.setIcon(PictureUtil.getPicture("close.png"));
		topPane.add(exitButton);

		JPanel downPane = new JPanel();
		downPane.setLayout(null);
		downPane.setBackground(Color.WHITE);
		dialog.add(downPane);
		downPane.setBounds(0, 30, 320, 139);
		downPane.setBorder(Constants.LIGHT_GRAY_BORDER);

		JTextArea text = new JTextArea(content);
		text.setFont(Constants.DIALOG_FONT);// ����
		text.setEditable(false);// ������༭
		text.setLineWrap(true);// �����Զ����й���
		text.setWrapStyleWord(true);// ������в����ֹ���
		text.setBounds(51, 47, 215, 42);
		text.setOpaque(false);
		downPane.add(text);
		
		final JLabel okButton = new JLabel("ȷ��", JLabel.CENTER);
		okButton.setBounds(115, 106, 93, 25);
		okButton.setFont(new Font("΢���ź�", Font.BOLD, 12));
		downPane.add(okButton);

		dialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		dialog.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = dialog.getLocation();
				dialog.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
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
				dialog.dispose();
			}
		});
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				okButton.setBorder(Constants.LIGHT_GRAY_BORDER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				okButton.setBorder(null);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dialog.dispose();
			}
		});

		return dialog;
	}
	
	private static JDialog getDialog(Component owner, String title) {
		JDialog dialog = null;
		if (owner instanceof JFrame) {
			dialog = new JDialog((JFrame)owner, title, true);
		}
		if (owner instanceof JDialog) {
			dialog = new JDialog((JDialog)owner, title, true);
		} 
		if (null == owner) {
			dialog = new JDialog();
			dialog.setTitle(title);
			dialog.setLocationRelativeTo(null);
		}
		return dialog;
	}

	public static void main(String[] args) {
	new	alertWindow().showMessageDialog(null, "dfsdg", "gdf");
	}
}
