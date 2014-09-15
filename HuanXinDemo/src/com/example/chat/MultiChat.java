package com.example.chat;

import java.util.HashMap;
import java.util.Map;

import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;

/**
 * ����������
 * 
 * @author lixiaosong
 */
public class MultiChat {
	public Map<String, EMConversation> conversations;

	public MultiChat() {
		conversations = new HashMap<String, EMConversation>();
	}

	/**
	 * 创建一个群组
	 * 
	 * @param name
	 *            群组名
	 * @param des
	 *            群组描述
	 * @param initMems
	 *            成员
	 */
	public void createGroup(String name, String des, String[] initMems) {
		try {
			EMGroupManager.getInstance().createPublicGroup(name, des, initMems,
					false, -1);
		} catch (EaseMobException e) {
			e.printStackTrace();
		}
	}
}
