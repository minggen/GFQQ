package com.myqq.client.assistui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

public class CategoryNode extends DefaultMutableTreeNode {
	
	public Icon icon;
	public JLabel picture;
	public JLabel nickName;
	public Category category;

	public JPanel categoryContent = new JPanel();

	public CategoryNode(Icon icon, Category category) {
		super();
		this.icon = icon;
		this.category = category;

		categoryContent.setLayout(null);
		categoryContent.setBackground(Color.WHITE);
		categoryContent.setPreferredSize(new Dimension(300, 25));

		picture = new JLabel();
		categoryContent.add(picture);
		picture.setIcon(icon);
		picture.setBounds(6, 5, 20, 16);

		nickName = new JLabel();
		categoryContent.add(nickName);
		nickName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		nickName.setText(category.getName());
		nickName.setBounds(19, 0, 132, 28);
	}

	public Component getView() {
		return categoryContent;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
