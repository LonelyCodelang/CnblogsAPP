package com.mcnblogs.Fragment;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mcnblogs.BlogActivity;
import com.mcnblogs.R;
import com.mcnblogs.adapter.BlogListAdapter;
import com.mcnblogs.core.BlogJsonHelper;
import com.mcnblogs.core.Config;
import com.mcnblogs.db.BloginfoDao;
import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.dto.myenum.BlogType;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.HttpUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class ReadFragment extends Fragment {

	private View vi;
	private ArrayList<BlogListDTO> list;
	private int pageIndex=1;
	private int pageSize=20;
	private SwipeRefreshLayout swip;//loading加载控件
	private BlogListAdapter dap;//适配类
	private boolean IsLoadEnd=false;//是否加载到底部
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		vi = inflater.inflate(R.layout.fragment_read, container, false);

		//设置下拉刷新
		setRefresh();

		LoadSource();//加载本地数据
		return vi;
	}

	/**
	 * 设置下拉刷新
	 */
	private  void setRefresh(){
		swip = (SwipeRefreshLayout) vi.findViewById(R.id.swipload);
		//swip.setOnRefreshListener();
		// 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
		swip.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		swip.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
		//swip.setProgressBackgroundColor(R.color.red); // 设定下拉圆圈的背景
		swip.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小
		swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				IsLoadEnd=false;
				//下拉刷新
				LoadNetSource();
			}
		});
	}

	/**
	 * 加载网络数据
	 */
	private void LoadNetSource(){
		String url= Config.List48HoursTop_URL.replace("{num}",Integer.toString(30));
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
					//loading停止刷新
					swip.setRefreshing(false);
				}
			}

			@Override
			public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
				// 数据返回异常
				APPUtil.ShowMsgNetError(getActivity());

				//loading停止刷新
				swip.setRefreshing(false);
			}
		});
	}

	/**
	 * 解析博客列表
	 */
	private void AnalyBlogList(String json) {
		final List<BlogListDTO> listSource = BlogJsonHelper.JsonToList(json);
		int insertCount=0;
		for(BlogListDTO item : listSource){
			int count=BloginfoDao.GetCountByTitle(item.getTitle(),BlogType.page2.toString());
			if(count==0){
				BloginfoDao.insert2(item,BlogType.page2.toString());
				insertCount++;
			}
		}
		if(insertCount==0){
			APPUtil.ShowMsg(getActivity(),"目前没有新的文章更新");
		}
		else{
			APPUtil.ShowMsg(getActivity(),"为您更新了"+insertCount+"篇文章");
		}

		//加载本地数据
		LoadSource();

		//loading停止刷新
		swip.setRefreshing(false);
	}

	/**
	 * 加载本地数据
	 */
	private  void  LoadSource(){

		pageIndex=1;
		list = BloginfoDao.getList(BlogType.page2.toString(),pageIndex,pageSize);

		ListView lv=(ListView)vi.findViewById(R.id.listViewBlog);
		dap=new BlogListAdapter(getActivity(),R.layout.homepage_item,list);
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

		//设置滚动事件
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView absListView, int i) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount == totalItemCount && !IsLoadEnd) {//判断是不是最后一个
					pageIndex=pageIndex+1;
					APPUtil.ShowMsg(getActivity(),"到底部了"+pageIndex);
					ArrayList<BlogListDTO> listSouece = BloginfoDao.getList(BlogType.page2.toString(),pageIndex,pageSize);
					if(listSouece.size()>0){
						list.addAll(listSouece);
						dap.notifyDataSetChanged();
					}
					else {
						IsLoadEnd=true;
					}
				}
			}
		});
	}

}
