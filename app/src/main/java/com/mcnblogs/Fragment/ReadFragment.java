package com.mcnblogs.Fragment;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mcnblogs.BlogActivity;
import com.mcnblogs.R;
import com.mcnblogs.adapter.BlogListAdapter;
import com.mcnblogs.core.BlogJsonHelper;
import com.mcnblogs.core.Config;
import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.HttpUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.Header;

import java.util.List;

public class ReadFragment extends Fragment {

	private View vi;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		vi = inflater.inflate(R.layout.fragment_read, container, false);
		Load();
		return vi;
	}

	/**
	 * 数据加载
	 */
	private void Load(){

		String url= Config.List48HoursTop_URL;
		HttpUtil.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
				if (statusCode == 200) {
					String response = new String(bytes);// 返回字符串
					String resJson = XmlJSON.xml2JSON(response);// 将xml转换成字符串
					AnalyBlogList(resJson);
				} else {
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
	 * 解析博客列表
	 */
	private void AnalyBlogList(String json) {
		final List<BlogListDTO> list = BlogJsonHelper.JsonToList(json);

		ListView lv=(ListView)vi.findViewById(R.id.listViewBlog);
		BlogListAdapter dap=new BlogListAdapter(getActivity(),R.layout.homepage_item,list);
		lv.setAdapter(dap);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

				BlogListDTO dto=list.get(position);

				Intent intent = new Intent(getActivity(), BlogActivity.class);

				// 新建Bundle对象
				Bundle mBundle = new Bundle();
				// 放入account对象
				mBundle.putParcelable("blogdto",dto);
				intent.putExtras(mBundle);


				startActivity(intent);
			}
		});

	}
}
