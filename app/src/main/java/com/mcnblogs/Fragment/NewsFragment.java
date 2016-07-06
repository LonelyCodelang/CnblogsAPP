package com.mcnblogs.Fragment;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mcnblogs.BlogActivity;
import com.mcnblogs.NewActivity;
import com.mcnblogs.R;
import com.mcnblogs.adapter.NewListAdapter;
import com.mcnblogs.core.Config;
import com.mcnblogs.dto.NewDTO;
import com.mcnblogs.utility.APPUtil;
import com.mcnblogs.utility.HttpUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        Load();
        return view;
    }

    /**
     * 加载数据
     */
    private void Load() {
        //列表Url
        String url = Config.ListNewsPage_URL;
        HttpUtil.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (i == 200) {
                    String response = new String(bytes);// 返回字符串
                    JsonAnalysis(response);
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
     * 字符串解析成json
     *
     * @param strJson
     */
    private void JsonAnalysis(String strJson) {
        String resJson = XmlJSON.xml2JSON(strJson);// 将xml转换成字符串
        try {
            Gson gs = new Gson();
            NewDTO dto = gs.fromJson(resJson, NewDTO.class);//序列化json
            final List<NewDTO.FeedBean.EntryBean> list = dto.getFeed().getEntry();

            ListView lv = (ListView) view.findViewById(R.id.lv_newlist);
            NewListAdapter dap = new NewListAdapter(getActivity(), R.layout.new_item, list);
            lv.setAdapter(dap);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    NewDTO.FeedBean.EntryBean dto = list.get(i);

                    Intent intent = new Intent(getActivity(), NewActivity.class);
                    Gson gs=new Gson();
                    String newDtoJson=gs.toJson(dto);
                    // 新建Bundle对象
                    Bundle mBundle = new Bundle();
                    // 放入account对象
                    mBundle.putString("newsdtojson",newDtoJson);
                    intent.putExtras(mBundle);

                    startActivity(intent);
                }
            });

        } catch (Exception ex) {
            String msg = ex.getMessage();
            Log.e("json转换错误", msg);
        }
    }
}
