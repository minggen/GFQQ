package com.myqq.common;

public interface MessageType {
	String message_succeed="1";//表明是登陆成功
	String message_login_fail="2";//表明登录失败
	String message_comm_mes="3";//普通信息包
	String message_get_onLineFriend="4";//要求在线好友的包
	String message_ret_onLineFriend="5";//返回在线好友的包;
	String message_fridata = "6";
	String message_ret_ip = "7";
	String message_ret_oldMessage ="8";
	String message_sendfile = null;
	String message_shake = "9";//震动
	String message_addFriend="10";
	String message_agreeAddFriend="11";
	String message_face="12";
	String message_fileRequest="13";
	String message_agreeFileRequest="14";
	String message_File="15";
	String message_File_port_ok="16";
}
