package com.myqq.client.assistui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.myqq.client.Mange.ManageMainWindow;
import com.myqq.client.Mange.ManageQqChat;
import com.myqq.client.ui.ChatRoom;
import com.myqq.client.ui.MainWindow;
import com.myqq.common.User;
import com.myqq.sever.db.Categorydb;
import com.myqq.sever.db.Frienddb;
import com.myqq.utils.Constants;
import com.myqq.utils.PictureUtil;
import com.qq.client.additionalFunction.addCategory;

public class FriendPanel extends JPanel {

	private JTree jTree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private JScrollPane jScrollPane;
	private JTextField textField;
	private static Color inColor = new Color(254, 224, 109); 
	private static Color selectColor = new Color(249, 184, 87);
	
	private MainWindow mw;

	public FriendPanel(User user, MainWindow mw) {
		this.mw = mw;
		
		setLayout(new BorderLayout());
		
		root = new DefaultMutableTreeNode();

		loadTree(user);

		jScrollPane = new JScrollPane(jTree);
		jScrollPane.setBorder(null);
		jScrollPane.setViewportView(jTree);
		jScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());			
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	
		this.add(jScrollPane, BorderLayout.CENTER); 
		    
		initJtreeListener(user);
	}
			
	
	private void initJtreeListener(User user) {
		// TODO 自动生成的方法存根

		jTree.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e)
		     {
		    	 
		    	 if (e.getButton() == MouseEvent.BUTTON1) {
		    	 TreePath path = jTree.getSelectionPath();
					if (null == path) {
						return;
					}
					Object node = path.getLastPathComponent();
					if (e.getClickCount() == 2) {
						if (node instanceof FriendNode) {
							System.out.println("左键双击");
							  System.out.println(((FriendNode) node).getFriend().getNick_name());
					           if(ManageQqChat.isNotExist(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId()))
					           {
					        	 ChatRoom chatRoom = new ChatRoom(user,((FriendNode) node).getFriend());
					           //把聊天界面加入到管理类
					        	 System.out.println(user.getNick_name()+"和"+((FriendNode) node).getFriend().getNick_name()+"聊天界面加入到管理类");
					        	 ManageQqChat.addQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId(), chatRoom);
								
					           //给服务器说selftofriendQchat
					           }
					           else{
					        	   System.out.println(user.getNick_name()+"和"+((FriendNode) node).getFriend().getNick_name()+"聊天界面已存在取出来");
					        	   ChatRoom chatRoom =ManageQqChat.getQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId());
					        	   chatRoom.setVisible(true);
					           }
						}
					}
		    	 }	
		  /*    if(e.getClickCount()==2)//双击节点
		      {
		    
		       System.out.println("左键双击");
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
		    	   System.out.println("不是叶子节点");
		       }
		       
		      }
		     
		      */
		     }
		     
		     public void mousePressed(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						TreePath path = jTree.getPathForLocation(e.getX(), e.getY());
						if (null != path) {
							final Object object = path.getLastPathComponent();
							if (object instanceof CategoryNode) {
								JPopupMenu pm = new JPopupMenu();
								pm.setBackground(Color.WHITE);
								pm.setBorder(Constants.LIGHT_GRAY_BORDER);
								JMenuItem mit = new JMenuItem("刷新好友列表");
								mit.setOpaque(false);
								mit.setFont(Constants.BASIC_FONT);
								JMenuItem mit0 = new JMenuItem("添加分组");
								mit0.setOpaque(false);
								mit0.setFont(Constants.BASIC_FONT);
								JMenuItem mit1 = new JMenuItem("重命名");
								mit1.setOpaque(false);
								mit1.setFont(Constants.BASIC_FONT);
								JMenuItem mit2 = new JMenuItem("删除分组");
								mit2.setOpaque(false);
								mit2.setFont(Constants.BASIC_FONT);
								JMenuItem mit3 = new JMenuItem("好友管理器");
								mit3.setOpaque(false);
								mit3.setFont(Constants.BASIC_FONT);
								
								// 刷新好友列表
								mit.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										//刷新好友列表
										System.out.println("刷新好友列表");
										
										root.removeAllChildren();
										
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
										 
										 model.reload(root);
										 
										System.out.println("好友列表");
									}
								});
								
								// 添加分组
								mit0.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										//添加分组
										new addCategory(user.getUserId(), mw).setVisible(true);
										
									}
								});
								
								// 重命名
								mit1.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										
									}
								});
								
								// 删除分组
								mit2.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO 自动生成的方法存根
										
									}
									
									
								});
								
								//好友管理器
								mit3.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										
									}
								});
								pm.add(mit);
								pm.add(mit0);
								pm.add(mit1);
								pm.add(mit2);
								pm.add(mit3);
								pm.show(jTree, e.getX(), e.getY());
							}
							if (object instanceof FriendNode) {
								JPopupMenu pm = new JPopupMenu();
								pm.setBackground(Color.WHITE);
								pm.setBorder(Constants.LIGHT_GRAY_BORDER);
								JMenuItem mit1 = new JMenuItem("发送即时消息");
								mit1.setOpaque(false);
								mit1.setFont(Constants.BASIC_FONT);
								JMenuItem mit2 = new JMenuItem("发送电子邮件");
								mit2.setOpaque(false);
								mit2.setFont(Constants.BASIC_FONT);
								JMenuItem mit3 = new JMenuItem("查看资料");
								mit3.setOpaque(false);
								mit3.setFont(Constants.BASIC_FONT);
								JMenuItem mit4 = new JMenuItem("删除好友");
								mit4.setOpaque(false);
								mit4.setFont(Constants.BASIC_FONT);
								// 发送即时消息
								mit1.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										 FriendNode node  = (FriendNode) object;
										System.out.println(((FriendNode) node  ).getFriend().getNick_name());
								           if(ManageQqChat.isNotExist(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId()))
								           {
								        	 ChatRoom chatRoom = new ChatRoom(user,((FriendNode) node).getFriend());
								           //把聊天界面加入到管理类
								        	 System.out.println(user.getNick_name()+"和"+((FriendNode) node).getFriend().getNick_name()+"聊天界面加入到管理类");
								        	 ManageQqChat.addQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId(), chatRoom);
											
								           //给服务器说selftofriendQchat
								           }
								           else{
								        	   ChatRoom chatRoom =ManageQqChat.getQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId());
								        	   
								        	   chatRoom.setVisible(true);
								           }
									}
								});
								
								pm.add(mit1);
								pm.add(mit2);
								pm.add(mit3);
								pm.add(mit4);
								pm.show(jTree, e.getX(), e.getY());
							}
						}
					}
				}
			});
		   
		
	}


	public void loadTree(User user) {
		// TODO 自动生成的方法存根

  		//交由服务器返回
		
//		root = new DefaultMutableTreeNode();
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
		

		jTree.setCellRenderer(new FriendNodeRenderer());// 自定义节点渲染器
		jTree.setRootVisible(false);
		jTree.setToggleClickCount(1);
		jTree.setInvokesStopCellEditing(true);
		//add(jTree);
	}


}
