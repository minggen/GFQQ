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
		// TODO �Զ����ɵķ������

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
							System.out.println("���˫��");
							  System.out.println(((FriendNode) node).getFriend().getNick_name());
					           if(ManageQqChat.isNotExist(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId()))
					           {
					        	 ChatRoom chatRoom = new ChatRoom(user,((FriendNode) node).getFriend());
					           //�����������뵽������
					        	 System.out.println(user.getNick_name()+"��"+((FriendNode) node).getFriend().getNick_name()+"���������뵽������");
					        	 ManageQqChat.addQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId(), chatRoom);
								
					           //��������˵selftofriendQchat
					           }
					           else{
					        	   System.out.println(user.getNick_name()+"��"+((FriendNode) node).getFriend().getNick_name()+"��������Ѵ���ȡ����");
					        	   ChatRoom chatRoom =ManageQqChat.getQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId());
					        	   chatRoom.setVisible(true);
					           }
						}
					}
		    	 }	
		  /*    if(e.getClickCount()==2)//˫���ڵ�
		      {
		    
		       System.out.println("���˫��");
		       TreePath path=jTree.getSelectionPath();//��ȡѡ�нڵ�·��
		       FriendNode node=(FriendNode)path.getLastPathComponent();//ͨ��·����ָ��ָ��ýڵ�
		       if(node.isLeaf())//����ýڵ���Ҷ�ӽڵ�
		       {
//		        //DefaultTreeModel model=(DefaultTreeModel)tree.getModel();//��ȡ������ģ��
//		           //model.removeNodeFromParent(node);//�ӱ���ɾ���ýڵ�     
//		           node.setIcon(new ImageIcon("2.jpg"));//�޸ĸýڵ��ͼƬ
//		           
//		           jTree.repaint();//�ػ������
		           System.out.println(node.getFriend().getNick_name());
		           if(ManageQqChat.isNotExist(user.getUserId()+" "+node.getFriend().getUserId()))
		           {
		        	 ChatRoom chatRoom = new ChatRoom(user,node.getFriend());
		           //�����������뵽������
		        	 System.out.println(user.getNick_name()+"��"+node.getFriend().getNick_name()+"���������뵽������");
		        	 ManageQqChat.addQqChat(user.getUserId()+" "+node.getFriend().getUserId(), chatRoom);
					
		           //��������˵selftofriendQchat
		           }
		           else{
		        	   ChatRoom chatRoom =ManageQqChat.getQqChat(user.getUserId()+" "+node.getFriend().getUserId());
		        	   
		        	   chatRoom.setVisible(true);
		           }
		       }
		       else//����Ҷ�ӽڵ�
		       { 
		    	   System.out.println("����Ҷ�ӽڵ�");
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
								JMenuItem mit = new JMenuItem("ˢ�º����б�");
								mit.setOpaque(false);
								mit.setFont(Constants.BASIC_FONT);
								JMenuItem mit0 = new JMenuItem("��ӷ���");
								mit0.setOpaque(false);
								mit0.setFont(Constants.BASIC_FONT);
								JMenuItem mit1 = new JMenuItem("������");
								mit1.setOpaque(false);
								mit1.setFont(Constants.BASIC_FONT);
								JMenuItem mit2 = new JMenuItem("ɾ������");
								mit2.setOpaque(false);
								mit2.setFont(Constants.BASIC_FONT);
								JMenuItem mit3 = new JMenuItem("���ѹ�����");
								mit3.setOpaque(false);
								mit3.setFont(Constants.BASIC_FONT);
								
								// ˢ�º����б�
								mit.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										//ˢ�º����б�
										System.out.println("ˢ�º����б�");
										
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
										 
										System.out.println("�����б�");
									}
								});
								
								// ��ӷ���
								mit0.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										//��ӷ���
										new addCategory(user.getUserId(), mw).setVisible(true);
										
									}
								});
								
								// ������
								mit1.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										
									}
								});
								
								// ɾ������
								mit2.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO �Զ����ɵķ������
										
									}
									
									
								});
								
								//���ѹ�����
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
								JMenuItem mit1 = new JMenuItem("���ͼ�ʱ��Ϣ");
								mit1.setOpaque(false);
								mit1.setFont(Constants.BASIC_FONT);
								JMenuItem mit2 = new JMenuItem("���͵����ʼ�");
								mit2.setOpaque(false);
								mit2.setFont(Constants.BASIC_FONT);
								JMenuItem mit3 = new JMenuItem("�鿴����");
								mit3.setOpaque(false);
								mit3.setFont(Constants.BASIC_FONT);
								JMenuItem mit4 = new JMenuItem("ɾ������");
								mit4.setOpaque(false);
								mit4.setFont(Constants.BASIC_FONT);
								// ���ͼ�ʱ��Ϣ
								mit1.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										 FriendNode node  = (FriendNode) object;
										System.out.println(((FriendNode) node  ).getFriend().getNick_name());
								           if(ManageQqChat.isNotExist(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId()))
								           {
								        	 ChatRoom chatRoom = new ChatRoom(user,((FriendNode) node).getFriend());
								           //�����������뵽������
								        	 System.out.println(user.getNick_name()+"��"+((FriendNode) node).getFriend().getNick_name()+"���������뵽������");
								        	 ManageQqChat.addQqChat(user.getUserId()+" "+((FriendNode) node).getFriend().getUserId(), chatRoom);
											
								           //��������˵selftofriendQchat
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
		// TODO �Զ����ɵķ������

  		//���ɷ���������
		
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
		 
		CategoryNode cate = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","�ҵ�ͬѧ","�乫��","1"));
		FriendNode fnode = new FriendNode(PictureUtil.getPicture("avatar.png"), new User("С��","3","3","3","tx.gif"));
		cate.add(fnode);
		FriendNode fnode1 = new FriendNode(PictureUtil.getPicture("avatar2.png"), new User("С��","2","2","2","tx.gif"));
		cate.add(fnode1);
		 
		root.add(cate);
		
		CategoryNode cate2 = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","�ҵĺ���","�乫��","1"));
		
		root.add(cate2);
		*/
		 
		model = new DefaultTreeModel(root);
		
		jTree  = new JTree(model);
		

		jTree.setCellRenderer(new FriendNodeRenderer());// �Զ���ڵ���Ⱦ��
		jTree.setRootVisible(false);
		jTree.setToggleClickCount(1);
		jTree.setInvokesStopCellEditing(true);
		//add(jTree);
	}


}
