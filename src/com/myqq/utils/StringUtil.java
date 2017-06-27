package com.myqq.utils;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isEqual(String str1, String str2) {
		if (str1 == str2 || str1.equals(str2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * ������������Ϣ
	 * @param senderName ����������
	 * @return
	 */
	public static String createSenderInfo(String senderName) {
		return senderName + Constants.SPACE + TimeUtil.getCurrentTime() + Constants.NEWLINE;
	}
	
	/**
	 * ������Ϣ
	 * @param text ����
	 * @param enterKey �Ƿ�س�����
	 * @return
	 */
	public static String createMsgInfo(String text, boolean enterKey) {
		// �����
		if (!enterKey) {
			text += Constants.NEWLINE;
		}
		return text;
	}
	
}
