package com.mcnblogs;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mcnblogs.core.Config;
import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.dto.NewDTO;
import com.mcnblogs.utility.DateUtil;
import com.mcnblogs.utility.NetUtil;
import com.mcnblogs.utility.XmlJSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class NewActivity extends Activity {


    /**
     * 文章属性
     */
    private  NewDTO.FeedBean.EntryBean dto = null;

    /**
     * 内容显示
     */
    private WebView wbView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new);

        //返回上一级activity
        ImageView btn_break = (ImageView) findViewById(R.id.blog_btn_break);
        btn_break.setOnClickListener(onclick);


        String newsdtojson = this.getIntent().getStringExtra("newsdtojson");
        dto=new Gson().fromJson(newsdtojson,NewDTO.FeedBean.EntryBean.class);

        wbView = (WebView) findViewById(R.id.blog_body_webview_content);
        String url = Config.NewItem_URL.replace("{0}",String.valueOf(dto.getId()));

        wbView.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码

        wbView.setSelected(true);

        PageTask task = new PageTask();
        task.execute(url);
        // webView.loadUrl(url);
    }

    // 点击事件
    private View.OnClickListener onclick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.blog_btn_break:
                    NewActivity.this.finish();
                    break;
            }
        }
    };

    public class PageTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            String Url = params[0];
            result = NetUtil.GetContentFromUrl(Url);
            String resJson = XmlJSON.xml2JSON(result);// 将xml转换成字符串
            try {
                JSONObject jsonObject = new JSONObject(resJson);
                result =jsonObject.getJSONObject("NewsBody").getString("Content"); //jsonObject.getString("string");
            } catch (JSONException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }

            return result;
        }

        /**
         * 加载内容12
         */
        @Override
        protected void onPostExecute(String _blogContent) {
            String htmlContent = "";
            try {
                InputStream in = getAssets().open("content.html");
                byte[] temp = NetUtil.readInputStream(in);
                htmlContent = new String(temp);
            } catch (Exception e) {
                Log.e("error", e.toString());
            }

            String timeStr=DateUtil.toString3(dto.getPublished());
            Log.i("时间日期:",timeStr);
            String blogInfo = "   发表时间:"
                    + timeStr + "  查看:"
                    + dto.getViews();

            // 格式化html
            // _blogContent = AppUtil.FormatContent(getApplicationContext(),
            // _blogContent);

            htmlContent = htmlContent.replace("#title#", dto.getTitle().getContent())
                    .replace("#time#", blogInfo)
                    .replace("#content#", _blogContent);

            htmlContent = htmlContent.replace("{csses}", "style");
            // if (!SPHelper.getTheme(ctx)){
            // main = main.replace("{csses}", "style");
            // }else{
            // main = main.replace("{csses}", "style-night");
            // }

            LoadWebViewContent(wbView, htmlContent);
        }

    }

    /**
     * 加载内容
     *
     * @param webView
     * @param content
     */
    private void LoadWebViewContent(WebView webView, String content) {
        webView.loadDataWithBaseURL(Config.LOCAL_PATH, content, "text/html",
                Config.ENCODE_TYPE, null);
    }
}
