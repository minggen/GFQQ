package com.myqq.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Constants {
	// ������IP��һ����˵���Ǳ���
		public static String SERVER_IP = "127.0.0.1";
		// ������port
		public static int SERVER_PORT = 5555;

		// ��Ϣ����
		/** ��ͨ */
		public static String GENRAL_MSG = "0";
		/** ���� */
		public static String SHAKE_MSG = "1";
		/** ���� */
		public static String PALIND_MSG = "2";
		/** ͼƬ */
//		�о��ò���
//		public static String PICTURE_MSG = "3";
		/** ��¼ */
		public static String LOGIN_MSG = "4";
		/** �˳� */
		public static String EXIT_MSG = "5";
		/** ע�� */
		public static String REGISTER_MSG = "6";
		/** �û���Ϣ(�޸ġ��鿴) */
		public static String INFO_MSG = "7";
		/** ������Ӻ��� */
		public static String REQUEST_ADD_MSG = "8";
		/** ��Ӧ��Ӻ��� */
		public static String ECHO_ADD_MSG = "9";
		/** ɾ�����飨user�� */
		public static String DELETE_USER_CATE_MSG = "10";
		/** ɾ����Ա��user�� */
		public static String DELETE_USER_MEMBER_MSG = "11";
		/** ��ӷ��飨user�� */
		public static String ADD_USER_CATE_MSG = "12";
		/** �޸ķ��飨user�� */
		public static String EDIT_USER_CATE_MSG = "13";

		public static String USER = "user";
		public static String GROUP = "group";
		public static String SUCCESS = "success";
		public static String FAILURE = "failure";
		public static String YES = "yes";
		public static String NO = "no";

		/** �ո���� */
		public static String SPACE = "\u0008";
		/** ���д��� */
		public static String NEWLINE = "\n";
		/** ��б�� */
		public static String LEFT_SLASH = "/";

		public static String SEARCH_TXT = "��������ϵ�ˡ�Ⱥ��";
		public static String DEFAULT_CATE = "�ҵĺ���";
		public static String NONAME_CATE = "δ����";
		
		// ΢���ź�
		//public static Font BASIC_FONT  = new Font("΢���ź�", Font.BOLD, 12);
		public static Font BASIC_FONT2 = new Font("΢���ź�", Font.TYPE1_FONT, 12);
		public static Font BASIC_FONT3 =new Font("ʵ��", Font.PLAIN, 12);
		// ����
		public static Font DIALOG_FONT = new Font("����", Font.PLAIN, 16);
		
		public static Border GRAY_BORDER = BorderFactory.createLineBorder(Color.GRAY);
		public static Border ORANGE_BORDER = BorderFactory.createLineBorder(Color.ORANGE);
		public static Border LIGHT_GRAY_BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

		public static int NO_OPTION = 1;
		public static int YES_OPTION = 0;
}
