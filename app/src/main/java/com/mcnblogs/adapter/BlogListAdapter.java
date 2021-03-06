package com.mcnblogs.adapter;

import java.text.NumberFormat;
import java.util.List;

import org.apache.http.Header;
import org.w3c.dom.Text;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.mcnblogs.Extends.CircleTransform;
import com.mcnblogs.R;
import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.DateUtil;
import com.mcnblogs.utility.HttpUtil;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BlogListAdapter extends ArrayAdapter<BlogListDTO> {

	private int resourceid;
	private View view;

	public BlogListAdapter(Context context, int resource,
						   List<BlogListDTO> listobjs) {
		super(context, resource, listobjs);
		resourceid = resource;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		BlogListDTO dto = getItem(position);
		if(convertView==null){
			view = LayoutInflater.from(getContext()).inflate(resourceid, null);
		}
		else{
			view=convertView;
		}

		TextView title = (TextView) view.findViewById(R.id.title);
		TextView context = (TextView) view.findViewById(R.id.tv_context);
		TextView authorName = (TextView) view.findViewById(R.id.authorName);
		TextView published = (TextView) view.findViewById(R.id.published);

		title.setText(dto.getTitle());
		context.setText(dto.getContent());
		authorName.setText(dto.getAuthorName());
		if (dto.getPublished() != null) {
			published.setText(DateUtil.ToString2(dto.getPublished()));
		}

		if(dto.getAvatarUrl()!=null && dto.getAvatarUrl()!=""){
			Log.d("图片路径",dto.getAvatarUrl());
			SetAvatarImg(dto.getAvatarUrl());//设置图片
		}
		String strViewNum=dto.getViews();
		if(!strViewNum.isEmpty()){
			double viewNum=Double.parseDouble(strViewNum);
			if(viewNum>=1000){
				viewNum=viewNum/1000;
				NumberFormat ddf1=NumberFormat.getNumberInstance() ;
				ddf1.setMaximumFractionDigits(1);//保留1位小数
				strViewNum=ddf1.format(viewNum)+"k";
			}
		}
		TextView tv_viewNum=(TextView)view.findViewById(R.id.tv_viewnum);
		tv_viewNum.setText(strViewNum);


		return view;
	}

	/**
	 * 设置坐着图像
	 * @param ImgUrl
	 */
	private void SetAvatarImg(String ImgUrl){
		// 设置图片
		// 主线程不能直接下载图片，需要用线程操作
//				HttpUtil.get(ImgUrl, new BinaryHttpResponseHandler() {
//					@Override
//					public void onSuccess(int statusCode, Header[] headers,
//							byte[] responseBody) {
//						Bitmap bitmap = BitmapFactory.decodeByteArray(responseBody, 0,
//								responseBody.length); // 生成
//						ImageView imgv = (ImageView) view.findViewById(R.id.avatarUrl);
//						imgv.setImageBitmap(bitmap);
//					}
//
//					@Override
//					public void onFailure(int statusCode, Header[] headers,
//							byte[] responseBody, Throwable arg3) {
//						APPUtil.ShowMsg(getContext(), arg3.getMessage());
//					}
//				});
		if(!ImgUrl.contains(".gif") && !ImgUrl.isEmpty()){
			ImageView imgv = (ImageView) view.findViewById(R.id.avatarUrl);
			Picasso.with(getContext())
					.load(ImgUrl)
					.placeholder(R.drawable.ic_launcher)
					.error(R.drawable.ic_launcher)
					.transform(new CircleTransform())
					.into(imgv);
		}

	}

}
