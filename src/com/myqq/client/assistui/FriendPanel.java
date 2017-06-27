package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.myqq.client.Mange.ManageQqChat;
import com.myqq.client.ui.ChatRoom;
import com.myqq.common.User;
import com.myqq.sever.db.Categorydb;
import com.myqq.sever.db.Frienddb;
import com.myqq.utils.PictureUtil;

public class FriendPanel extends JPanel {

	private JTree jTree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private JScrollPane jScrollPane;
	private JTextField textField;
	private static Color inColor = new Color(254, 224, 109); 
	private static Color selectColor = new Color(249, 184, 87);
	
	
	
	public FriendPanel(User user) {
		
		setLayout(new BorderLayout());
		
		root = new DefaultMutableTreeNode();

		loadTree(user);
		
/*		CategoryNode cate = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","我的同学","张小凡","1"));
		FriendNode fnode = new FriendNode(PictureUtil.getPicture("avatar.png"), user);
		cate.add(fnode);cate.add(fnode);cate.add(fnode);cate.add(fnode);cate.add(fnode);
		root.add(cate);
		
		CategoryNode cate2 = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","我的同学","张小凡","1"));
		
		root.add(cate2);
		
		model = new DefaultTreeModel(root);
		
		jTree  = new JTree(model);
		
//		jTree.setUI(new MyTreeUI()); // 自定义UI
		jTree.setCellRenderer(new FriendNodeRenderer());// 自定义节点渲染器
		jTree.setRootVisible(false);// 隐藏根节点
		jTree.setToggleClickCount(1);// 点击次数
		jTree.setInvokesStopCellEditing(true);// 修改节点文字之后生效
		add(jTree);
	*/	

		textField = new JTextField();
//		textField.setPreferredSize(new Dimension(297, 23));
//		textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//		jTree.setCellEditor(new DefaultCellEditor(textField));
//		
//		
		jScrollPane = new JScrollPane(jTree);
		jScrollPane.setBorder(null);
		jScrollPane.setViewportView(jTree);
		jScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());			
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	
		this.add(jScrollPane, BorderLayout.CENTER); 
		    
		    
	}
			
	
	private void loadTree(User user) {
		// TODO 自动生成的方法存根

  		//交由服务器返回
		 List<Category> clist = new Categorydb().getCategoryList(user.getUserId());
		 
		 for (Category category : clist) {
			 CategoryNode cate = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), category);
			 List<User> ulist =new Frienddb().getUserList(category.getName(), user.getUserId());
			 if (null != ulist && ulist.size() > 0) {					
			 for (User friend : ulist) {
					FriendNode buddy = new FriendNode(friend.getImg(), friend);
					cate.add(buddy);
					
			}
			 		
		}
				
				root.add(cate);
		
}
	/*	 
		 
		CategoryNode cate = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","我的同学","冷公子","1"));
		FriendNode fnode = new FriendNode(PictureUtil.getPicture("avatar.png"), new User("小七","3","3","3","tx.gif"));
		cate.add(fnode);
		FriendNode fnode1 = new FriendNode(PictureUtil.getPicture("avatar2.png"), new User("小八","2","2","2","tx.gif"));
		cate.add(fnode1);
		 
		root.add(cate);
		
		CategoryNode cate2 = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","我的好友","冷公子","1"));
		
		root.add(cate2);
		*/
		 
		model = new DefaultTreeModel(root);
		
		jTree  = new JTree(model);
		
//		jTree.setUI(new MyTreeUI()); // 自定义UI
		jTree.setCellRenderer(new FriendNodeRenderer());// 自定义节点渲染器
		jTree.setRootVisible(false);// 隐藏根节点
		jTree.setToggleClickCount(1);// 点击次数
		jTree.setInvokesStopCellEditing(true);// 修改节点文字之后生效
		//add(jTree);
		 jTree.addMouseListener(new MouseAdapter()
		    {
		     public void mouseClicked(MouseEvent e)
		     {
		      if(e.getClickCount()==2)//双击节点
		      {
		       TreePath path=jTree.getSelectionPath();//获取选中节点路径
		       FriendNode node=(FriendNode)path.getLastPathComponent();//通过路径将指针指向该节点
		       if(node.isLeaf())//如果该节点是叶子节点
		       {
//		        //DefaultTreeModel model=(DefaultTreeModel)tree.getModel();//获取该树的模型
//		           //model.removeNodeFromParent(node);//从本树删除该节点     
//		           node.setIcon(new ImageIcon("2.jpg"));//修改该节点的图片
//		           
//		           jTree.repaint();//重绘更新树
		           System.out.println(node.getFriend().getNick_name());
		           if(ManageQqChat.isNotExist(user.getUserId()+" "+node.getFriend().getUserId()))
		           {
		        	 ChatRoom chatRoom = new ChatRoom(user,node.getFriend());
		           //把聊天界面加入到管理类
		        	 System.out.println(user.getNick_name()+"和"+node.getFriend().getNick_name()+"聊天界面加入到管理类");
		        	 ManageQqChat.addQqChat(user.getUserId()+" "+node.getFriend().getUserId(), chatRoom);
					
		           //给服务器说selftofriendQchat
		           }
		           else{
		        	   ChatRoom chatRoom =ManageQqChat.getQqChat(user.getUserId()+" "+node.getFriend().getUserId());
		        	   
		        	   chatRoom.setVisible(true);
		           }
		       }
		       else//不是叶子节点
		       {       
		       }
		       
		      }
		     }
		    });
		   
		 
	}


	/*private SimpleAttributeSet getAttributeSet(boolean isDefault, Message message) {
		SimpleAttributeSet set = new SimpleAttributeSet();
		if (isDefault) {
			StyleConstants.setBold(set, false);
			StyleConstants.setItalic(set, false);
			StyleConstants.setFontSize(set, 15);
			StyleConstants.setFontFamily(set, "宋体");
			StyleConstants.setForeground(set, Color.RED);
		} else {
			// 字体名称
			StyleConstants.setFontFamily(set, message.getFamily());
			// 字号
			StyleConstants.setFontSize(set, message.getSize());
			// 样式
			int styleIndex = message.getStyle();
			if (styleIndex == 0) {// 常规
				StyleConstants.setBold(set, false);
				StyleConstants.setItalic(set, false);
			}
			if (styleIndex == 1) {// 斜体
				StyleConstants.setBold(set, false);
				StyleConstants.setItalic(set, true);
			}
			if (styleIndex == 2) {// 粗体
				StyleConstants.setBold(set, true);
				StyleConstants.setItalic(set, false);
			}
			if (styleIndex == 3) {// 粗斜体
				StyleConstants.setBold(set, true);
				StyleConstants.setItalic(set, true);
			}
			// 字体颜色
			int foreIndex = message.getFore();
			if (foreIndex == 0) {// 黑色
				StyleConstants.setForeground(set, Color.BLACK);
			}
			if (foreIndex == 1) {// 橙色
				StyleConstants.setForeground(set, Color.ORANGE);
			}
			if (foreIndex == 2) {// 黄色
				StyleConstants.setForeground(set, Color.YELLOW);
			}
			if (foreIndex == 3) {// 绿色
				StyleConstants.setForeground(set, Color.GREEN);
			}
			// 背景颜色
			int backIndex = message.getBack();
			if (backIndex == 0) {// 白色
				StyleConstants.setBackground(set, Color.WHITE);
			}
			if (backIndex == 1) {// 灰色
				StyleConstants.setBackground(set, new Color(200, 200, 200));
			}
			if (backIndex == 2) {// 淡红
				StyleConstants.setBackground(set, new Color(255, 200, 200));
			}
			if (backIndex == 3) {// 淡蓝
				StyleConstants.setBackground(set, new Color(200, 200, 255));
			}
			if (backIndex == 4) {// 淡黄
				StyleConstants.setBackground(set, new Color(255, 255, 200));
			}
			if (backIndex == 5) {// 淡绿
				StyleConstants.setBackground(set, new Color(200, 255, 200));
			}
		}
		return set;
	}
	*/
}
