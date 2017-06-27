package com.myqq.client.assistui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.myqq.common.User;
import com.myqq.utils.PictureUtil;


public class FriendNode extends DefaultMutableTreeNode {

	public Icon icon;
	public User friend;
	public JLabel picture;
	public JLabel nickName;
	public JLabel descript;
	public JPanel userContent = new JPanel();

	public FriendNode(String path, User friend) {
		super();

		ImageIcon imageIcon = PictureUtil.getPicture(path);    // Icon由图片文件形成
		Image image = imageIcon.getImage();                         // 但这个图片太大不适合做Icon
		//    为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
		Image smallImage = image.getScaledInstance(40,40,Image.SCALE_FAST);
		//    再由修改后的Image来生成合适的Icon
		ImageIcon smallIcon = new ImageIcon(smallImage);
		
		this.icon = smallIcon;
		this.friend = friend;

		userContent.setLayout(null);
		userContent.setBackground(Color.WHITE);
		userContent.setPreferredSize(new Dimension(300, 50));

		picture = new JLabel();
		userContent.add(picture);
		picture.setIcon(icon);
		picture.setBounds(8, 4, 39, 42);

		nickName = new JLabel();
		userContent.add(nickName);
		nickName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		nickName.setText(friend.getNick_name());
		nickName.setBounds(59, 5, 132, 19);

		descript = new JLabel();
		userContent.add(descript);
		descript.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		descript.setText(friend.getUser_signature());
		descript.setBounds(59, 28, 132, 17);
	}

	public Component getView() {
		return userContent;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

}
