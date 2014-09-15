package com.example.db;

/**
 * 这个类仅仅临时存放聊天的数据（本次显示用），在应用退出时，本地的消息数据将会删除,使用网络的数据
 * 
 * @author lixiaosong
 * 
 */
public class SingleChatInfo {
	public int id;
	public String from;
	/**
	 * 聊天类型
	 */
	public String type;
	/**
	 * 聊天数据类型
	 */
	public String dataType;
	/**
	 * 聊天内容
	 */
	public String content;
}
