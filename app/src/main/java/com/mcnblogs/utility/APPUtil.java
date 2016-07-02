package com.mcnblogs.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * 全局类
 *
 * @author Administrator
 *
 */
public class APPUtil {

	/**
	 * 显示消息
	 *
	 * @param context
	 *            显示消息的主对象
	 * @param msg
	 *            消息描述
	 */
	public static void ShowMsg(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示消息
	 *
	 * @param context
	 *            显示消息的主对象
	 * @param msg
	 *            消息描述
	 */
	public static void ShowMsgNetError(Context context) {
		Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
	}

}
