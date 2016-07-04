package com.mcnblogs.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mcnblogs.adapter.BlogListAdapter;
import com.mcnblogs.core.BlogJsonHelper;
import com.mcnblogs.core.Config;
import com.mcnblogs.dto.*;
import com.mcnblogs.utility.*;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mcnblogs.BlogActivity;
import com.mcnblogs.R;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.DateUtil;
import com.mcnblogs.utility.HttpUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HomePageFragment extends Fragment {

	private View view;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_homepage, container, false);
		Load();
		return view;
	}

	/**
	 * 加载数据
	 */
	private void Load() {
		String url =Config.List10DayTop_URL;
		HttpUtil.get(url, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
								  byte[] responseBody) {
				if (statusCode == 200) {
					String response = new String(responseBody);// 返回字符串
					String resJson = XmlJSON.xml2JSON(response);// 将xml转换成字符串
					AnalyBlogList(resJson);
				} else {
					// 数据返回异常
					APPUtil.ShowMsgNetError(getActivity());
				}
			}

			/*
			 * 网络异常情况
			 *
			 * @see
			 * com.loopj.android.http.AsyncHttpResponseHandler#onFailure(int,
			 * org.apache.http.Header[], byte[], java.lang.Throwable)
			 */
			@Override
			public void onFailure(int statusCode, Header[] headers,
								  byte[] responseBody, Throwable error) {
				// TODO 自动生成的方法存根
				String errorStr = error.getMessage();// 把错误信息打印出轨迹来
				APPUtil.ShowMsg(getActivity(), " 网络异常情况");
			}
		});
	}

	/**
	 * 解析博客列表
	 */
	private void AnalyBlogList(String json) {
		final List<BlogListDTO> list = BlogJsonHelper.JsonToList(json);

		ListView lv = (ListView) view.findViewById(R.id.listView1);
		BlogListAdapter adapter = new BlogListAdapter(getActivity(),
				R.layout.homepage_item, list);
		lv.setAdapter(adapter);
		//lv.setDividerHeight(0);//隐藏分割线
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				BlogListDTO item = list.get(position);

				Intent intent = new Intent(getActivity(), BlogActivity.class);

				// 新建Bundle对象
				Bundle mBundle = new Bundle();
				// 放入account对象
				mBundle.putParcelable("blogdto",item);
				intent.putExtras(mBundle);


				startActivity(intent);
			}

		});

	}

}
