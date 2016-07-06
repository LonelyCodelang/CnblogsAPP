package com.mcnblogs;

import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.mcnblogs.core.Config;
import com.mcnblogs.dto.BlogListDTO;
import com.mcnblogs.utility.DateUtil;
import com.mcnblogs.utility.NetUtil;
import com.mcnblogs.utility.XmlJSON;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class BlogActivity extends Activity {

	/**
	 * 文章属性
	 */
	private BlogListDTO dto = null;

	/**
	 * 内容显示
	 */
	private WebView wbView;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// 去除title
		// requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 去掉Activity上面的状态栏
		// getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
		// WindowManager.LayoutParams. FLAG_FULLSCREEN);

		// 防止休眠
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
		// WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_blog);

		//返回上一级activity
		ImageView btn_break = (ImageView) findViewById(R.id.blog_btn_break);
		btn_break.setOnClickListener(onclick);


		Parcelable parcelable = this.getIntent().getParcelableExtra("blogdto");
		dto = (BlogListDTO) parcelable;

		wbView = (WebView) findViewById(R.id.blog_body_webview_content);
		String url = Config.Body_URL.replace("{0}", dto.getId());

		wbView.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码
		//wbView.addJavascriptInterface(this, "javatojs");
		wbView.setSelected(true);
		//wbView.setScrollBarStyle(0);
		/**
		 WebSettings webSetting = wbView.getSettings();
		 webSetting.setJavaScriptEnabled(true);

		 // webSetting.setLoadWithOverviewMode(true);
		 // webSetting.setUseWideViewPort(true);
		 //
		 // //支持javascript
		 // webSetting.setJavaScriptEnabled(true);
		 // // 设置可以支持缩放
		 // webSetting.setSupportZoom(true);
		 // // 设置出现缩放工具
		 // webSetting.setBuiltInZoomControls(true);
		 // //扩大比例的缩放
		 // webSetting.setUseWideViewPort(true);
		 // //自适应屏幕
		 // webSetting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		 // webSetting.setLoadWithOverviewMode(true);



		 webSetting.setNeedInitialFocus(false);
		 webSetting.setSupportZoom(true);
		 webSetting.setDefaultFontSize(5);
		 webSetting.setCacheMode(WebSettings.LOAD_DEFAULT
		 | WebSettings.LOAD_CACHE_ELSE_NETWORK);
		 // 双击全屏
		 wbView.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
		return true; // gestureScanner.onTouchEvent(event);
		}
		});
		 **/
		PageTask task = new PageTask();
		task.execute(url);
		// webView.loadUrl(url);
	}

	// 点击事件
	private OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.blog_btn_break:
					BlogActivity.this.finish();
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
				result = jsonObject.getString("string");
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

			String timeStr=DateUtil.ToString(dto.getPublished());
			Log.i("时间日期:",timeStr);
			String blogInfo = "作者: " + dto.getAuthorName() + "   发表时间:"
					+ timeStr + "  查看:"
					+ dto.getViews();

			// 格式化html
			// _blogContent = AppUtil.FormatContent(getApplicationContext(),
			// _blogContent);

			htmlContent = htmlContent.replace("#title#", dto.getTitle())
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
