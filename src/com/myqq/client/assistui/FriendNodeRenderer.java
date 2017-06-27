package com.myqq.client.assistui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.myqq.utils.PictureUtil;



public class FriendNodeRenderer extends DefaultTreeCellRenderer {
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (value instanceof FriendNode) {
			return ((FriendNode)value).getView();
		}
		if (value instanceof CategoryNode) {
			if (expanded) {
				((CategoryNode)value).picture.setIcon(PictureUtil.getPicture("arrow_down.png"));
			} else {
				((CategoryNode)value).picture.setIcon(PictureUtil.getPicture("arrow_left.png"));
			}
			return ((CategoryNode)value).getView();
		}
		return this;
	}

}
