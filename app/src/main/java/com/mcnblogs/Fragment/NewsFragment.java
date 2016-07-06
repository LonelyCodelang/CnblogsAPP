package com.mcnblogs.Fragment;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mcnblogs.R;
import com.mcnblogs.core.Config;
import com.mcnblogs.dto.NewDTO;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.HttpUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		Load();
		return view;
	}

	/**
	 * 加载数据
	 */
	private  void  Load(){
		//列表Url
		String url= Config.ListNewsPage_URL;
		HttpUtil.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int i, Header[] headers, byte[] bytes) {
				if(i==200){
					String response = new String(bytes);// 返回字符串
					JsonAnalysis(response);
				}else{
					// 数据返回异常
					APPUtil.ShowMsgNetError(getActivity());
				}
			}

			@Override
			public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
				// 数据返回异常
				APPUtil.ShowMsgNetError(getActivity());
			}
		});
	}

	/**
	 * 字符串解析成json
	 * @param strJson
	 */
	private  void JsonAnalysis(String strJson){
		String resJson = XmlJSON.xml2JSON(strJson);// 将xml转换成字符串
		try {
			Gson gs=new Gson();
			NewDTO dto=gs.fromJson(resJson,NewDTO.class);//序列化json
			List<NewDTO.FeedBean.EntryBean> list= dto.getFeed().getEntry();
			

		}catch (Exception ex){
			String msg=ex.getMessage();
			Log.e("json转换错误",msg);
		}
	}
}
