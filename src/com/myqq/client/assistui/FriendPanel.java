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
		
/*		CategoryNode cate = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","�ҵ�ͬѧ","��С��","1"));
		FriendNode fnode = new FriendNode(PictureUtil.getPicture("avatar.png"), user);
		cate.add(fnode);cate.add(fnode);cate.add(fnode);cate.add(fnode);cate.add(fnode);
		root.add(cate);
		
		CategoryNode cate2 = new CategoryNode(PictureUtil.getPicture("arrow_left.png"), new Category("1","�ҵ�ͬѧ","��С��","1"));
		
		root.add(cate2);
		
		model = new DefaultTreeModel(root);
		
		jTree  = new JTree(model);
		
//		jTree.setUI(new MyTreeUI()); // �Զ���UI
		jTree.setCellRenderer(new FriendNodeRenderer());// �Զ���ڵ���Ⱦ��
		jTree.setRootVisible(false);// ���ظ��ڵ�
		jTree.setToggleClickCount(1);// �������
		jTree.setInvokesStopCellEditing(true);// �޸Ľڵ�����֮����Ч
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
		// TODO �Զ����ɵķ������

  		//���ɷ���������
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
		
//		jTree.setUI(new MyTreeUI()); // �Զ���UI
		jTree.setCellRenderer(new FriendNodeRenderer());// �Զ���ڵ���Ⱦ��
		jTree.setRootVisible(false);// ���ظ��ڵ�
		jTree.setToggleClickCount(1);// �������
		jTree.setInvokesStopCellEditing(true);// �޸Ľڵ�����֮����Ч
		//add(jTree);
		 jTree.addMouseListener(new MouseAdapter()
		    {
		     public void mouseClicked(MouseEvent e)
		     {
		      if(e.getClickCount()==2)//˫���ڵ�
		      {
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
			StyleConstants.setFontFamily(set, "����");
			StyleConstants.setForeground(set, Color.RED);
		} else {
			// ��������
			StyleConstants.setFontFamily(set, message.getFamily());
			// �ֺ�
			StyleConstants.setFontSize(set, message.getSize());
			// ��ʽ
			int styleIndex = message.getStyle();
			if (styleIndex == 0) {// ����
				StyleConstants.setBold(set, false);
				StyleConstants.setItalic(set, false);
			}
			if (styleIndex == 1) {// б��
				StyleConstants.setBold(set, false);
				StyleConstants.setItalic(set, true);
			}
			if (styleIndex == 2) {// ����
				StyleConstants.setBold(set, true);
				StyleConstants.setItalic(set, false);
			}
			if (styleIndex == 3) {// ��б��
				StyleConstants.setBold(set, true);
				StyleConstants.setItalic(set, true);
			}
			// ������ɫ
			int foreIndex = message.getFore();
			if (foreIndex == 0) {// ��ɫ
				StyleConstants.setForeground(set, Color.BLACK);
			}
			if (foreIndex == 1) {// ��ɫ
				StyleConstants.setForeground(set, Color.ORANGE);
			}
			if (foreIndex == 2) {// ��ɫ
				StyleConstants.setForeground(set, Color.YELLOW);
			}
			if (foreIndex == 3) {// ��ɫ
				StyleConstants.setForeground(set, Color.GREEN);
			}
			// ������ɫ
			int backIndex = message.getBack();
			if (backIndex == 0) {// ��ɫ
				StyleConstants.setBackground(set, Color.WHITE);
			}
			if (backIndex == 1) {// ��ɫ
				StyleConstants.setBackground(set, new Color(200, 200, 200));
			}
			if (backIndex == 2) {// ����
				StyleConstants.setBackground(set, new Color(255, 200, 200));
			}
			if (backIndex == 3) {// ����
				StyleConstants.setBackground(set, new Color(200, 200, 255));
			}
			if (backIndex == 4) {// ����
				StyleConstants.setBackground(set, new Color(255, 255, 200));
			}
			if (backIndex == 5) {// ����
				StyleConstants.setBackground(set, new Color(200, 255, 200));
			}
		}
		return set;
	}
	*/
}
