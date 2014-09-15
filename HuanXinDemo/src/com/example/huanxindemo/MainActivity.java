package com.example.huanxindemo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.EMMessage.ChatType;
import com.example.chat.EaseMobChat;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends Activity {
	@ViewInject(R.id.text)
	private TextView tView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		// EaseMobChat.getInstance().registerAccount("test2", "123456");
		EaseMobChat.getInstance().login("test2", "123456");
		EMConversation conversation = EMChatManager.getInstance()
				.getConversation("test2");
		conversation.clear();
		for (int i = 0; i < 10; i++) {
			EaseMobChat.getInstance().sendTxtMessage("test2", ChatType.Chat,
					"人生不纠结" + i, new EMCallBack() {

						@Override
						public void onSuccess() {
							Log.e("test", "发送已经成功");

						}

						@Override
						public void onProgress(int arg0, String arg1) {

						}

						@Override
						public void onError(int arg0, String arg1) {

						}
					});
		}
		StringBuilder builder = new StringBuilder();
		builder.append(conversation.getUnreadMsgCount() + "\n");
		List<EMMessage> datas = conversation.getAllMessages();
		for (int i = 0; i < datas.size(); i++)
			builder.append(datas.get(i).getBody() + "\n");
		tView.setText(builder.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
