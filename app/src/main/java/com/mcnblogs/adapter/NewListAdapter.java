package com.mcnblogs.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcnblogs.Extends.CircleTransform;
import com.mcnblogs.R;
import com.mcnblogs.dto.NewDTO;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * 作者：hl
 * 时间:2016/7/6 17:55
 * 说明:新闻列表类型
 */
public class NewListAdapter extends ArrayAdapter<NewDTO.FeedBean.EntryBean> {

    private int resourceid;
    private View view;
    public NewListAdapter(Context context, int resource, List<NewDTO.FeedBean.EntryBean> objects) {
        super(context, resource, objects);
        resourceid=resource;
    }

    public  View getView(int position, View convertView, ViewGroup parent){
        NewDTO.FeedBean.EntryBean dto=getItem(position);
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceid, null);
        }
        else{
            view=convertView;
        }
        TextView tv=(TextView)view.findViewById(R.id.tv_title);
        ImageView img=(ImageView)view.findViewById(R.id.img_ioc);

        tv.setText(dto.getTitle().getContent());
        SetImg(dto.getTopicIcon());

        return view;
    }

    /**
     * 加载图片
     * @param url
     */
    private void SetImg(String url){

            try{
                if(!url.isEmpty()){
                    ImageView imgv=(ImageView)view.findViewById(R.id.img_ioc);
                    if(url.contains(".gif")){
                        //如果是gif的图片则不能处理，会出错误
                        Picasso.with(getContext())
                                .load(url)
                                .placeholder(R.drawable.ic_launcher)
                                .error(R.drawable.ic_launcher)
                                .into(imgv);
                    }else{
                        Picasso.with(getContext())
                                .load(url)
                                .transform(new CircleTransform())
                                .placeholder(R.drawable.ic_launcher)
                                .error(R.drawable.ic_launcher)
                                .into(imgv);
                    }

                }
            }catch (Exception ex){
                Log.e("图片加载错误",ex.getMessage());
            }
    }
}
