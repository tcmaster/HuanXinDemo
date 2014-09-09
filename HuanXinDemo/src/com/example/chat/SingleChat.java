package com.example.chat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.EMMessage.ChatType;
import com.easemob.chat.EMMessage.Type;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.MessageBody;
import com.easemob.chat.NormalFileMessageBody;
import com.easemob.chat.TextMessageBody;

/**
 * ������Ϣ��(�������������ָ��id�Ϳ��ԣ����Ե��˺�Ⱥ�鷢��Ϣ������һ������У�
 * 
 * @author lixiaosong
 * 
 */
public class SingleChat {
	/**
	 * ���ڱ��浱ǰ����ľ��
	 */
	public Map<String, EMConversation> conversations;

	public SingleChat() {
		conversations = new HashMap<String, EMConversation>();
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param userName
	 *            Ҫ�����û�/Ⱥ�ĵ�id
	 * @param chatType
	 *            ���ͣ����Ļ���Ⱥ��
	 * @param content
	 *            ���͵���Ϣ�ļ�����/�ļ���·��
	 * @param callBack
	 *            ��Ϣ����״̬�Ļص�
	 */
	public void sendTextMessage(String userName, ChatType chatType,
			String content, final EMCallBack callBack) {
		sendMessage(userName, chatType, Type.TXT, content, callBack);
	}

	/**
	 * ����ͼƬ��Ϣ
	 * 
	 * @param userName
	 *            Ҫ�����û�/Ⱥ�ĵ�id
	 * @param chatType
	 *            ���ͣ����Ļ���Ⱥ��
	 * @param content
	 *            ���͵���Ϣ�ļ�����/�ļ���·��
	 * @param callBack
	 *            ��Ϣ����״̬�Ļص�
	 */
	public void sendImageMessage(String userName, ChatType chatType,
			String content, final EMCallBack callBack) {
		sendMessage(userName, chatType, Type.IMAGE, content, callBack);
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param userName
	 *            Ҫ�����û�/Ⱥ�ĵ�id
	 * @param chatType
	 *            ���ͣ����Ļ���Ⱥ��
	 * @param content
	 *            ���͵���Ϣ�ļ�����/�ļ���·��
	 * @param callBack
	 *            ��Ϣ����״̬�Ļص�
	 */
	public void sendVoiceMessage(String userName, ChatType chatType,
			String content, final EMCallBack callBack) {
		sendMessage(userName, chatType, Type.VOICE, content, callBack);
	}

	/**
	 * ���͵���λ����Ϣ
	 * 
	 * @param userName
	 *            Ҫ�����û�/Ⱥ�ĵ�id
	 * @param chatType
	 *            ���ͣ����Ļ���Ⱥ��
	 * @param content
	 *            ���͵���Ϣ�ļ�����/�ļ���·��
	 * @param callBack
	 *            ��Ϣ����״̬�Ļص�
	 */
	public void sendLocationMessage(String userName, ChatType chatType,
			String content, final EMCallBack callBack) {
		sendMessage(userName, chatType, Type.LOCATION, content, callBack);
	}

	/**
	 * �����ļ���Ϣ
	 * 
	 * @param userName
	 *            Ҫ�����û�/Ⱥ�ĵ�id
	 * @param chatType
	 *            ���ͣ����Ļ���Ⱥ��
	 * @param content
	 *            ���͵���Ϣ�ļ�����/�ļ���·��
	 * @param callBack
	 *            ��Ϣ����״̬�Ļص�
	 */
	public void sendFileMessage(String userName, ChatType chatType,
			String content, final EMCallBack callBack) {
		sendMessage(userName, chatType, Type.FILE, content, callBack);
	}

	/**
	 * ������Ϣ
	 * 
	 * @param userName
	 *            Ҫ�����û�/Ⱥ�ĵ�id
	 * @param chatType
	 *            ���ͣ����Ļ���Ⱥ��
	 * @param type
	 *            ���͵���Ϣ����
	 * @param content
	 *            ���͵���Ϣ�ļ�����/�ļ���·��
	 * @param callBack
	 *            ��Ϣ����״̬�Ļص�
	 */
	private void sendMessage(String userName, ChatType chatType, Type type,
			String content, final EMCallBack callBack) {
		EMConversation conversation = getConversation(userName);
		EMMessage message = EMMessage.createSendMessage(type);
		message.setChatType(chatType);
		MessageBody body = null;
		switch (type) {
		case TXT:
			body = new TextMessageBody(content);
			break;
		case IMAGE:
			body = new ImageMessageBody(new File(content));
			break;
		case LOCATION:
			// ��ʱ�ò���
			// body = new LocationMessageBody(arg0, arg1, arg2)
			break;
		case FILE:
			body = new NormalFileMessageBody(new File(content));
			break;
		case VOICE:
			// ��ʱ�ò���
			// body = new VoiceMessageBody(arg0, arg1)
			break;
		default:
			break;
		}
		message.addBody(body);
		message.setReceipt(userName);
		conversation.addMessage(message);
		EMChatManager.getInstance().sendMessage(message, callBack);
	}

	/**
	 * �õ���ǰ�Ự
	 * 
	 * @param userName
	 *            ��ǰ�Ự���û�id/Ⱥ��id
	 * @return
	 */
	private EMConversation getConversation(String userName) {
		EMConversation conversation = conversations.get(userName);
		if (conversation == null) {
			conversation = EMChatManager.getInstance()
					.getConversation(userName);
			conversations.put(userName, conversation);
		}
		return conversation;
	}
}
