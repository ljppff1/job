package com.webdesign488.post;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign488.post.a.ChoiceArea1Activity;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.view.ProgressDialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class ChatInfoActivity extends BaseActivity {
     private WebView wb_condition;
	private ProgressDialog dialog;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.chatinfo);
    	initView();
    	initData();
    }

	private void initData() {
		dialog =new com.webdesign488.post.view.ProgressDialog(ChatInfoActivity.this);
	     List<String> list =new ArrayList<String>();
	     List<String> list1 =new ArrayList<String>();
         AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.Contact, ChatInfoActivity.this, 3);

		
	}
	private Handler handler =new Handler(){
		   

		

		private WebView mWebView;

		@SuppressLint("NewApi")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			   
			   case 1:
				   Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast11), 1).show();
				   break;

			   case 3:
					String json =(String) msg.obj;
					if(!TextUtils.isEmpty(json)){
						 JSONObject gen;
						try {
							gen = new JSONObject(json);
		      	    		 String string_Content = gen.getString("data");
		      	    		 
		      	    		 //show content
		      	    		mWebView = (WebView) findViewById(R.id.wb_condition);
		      	    		mWebView.getSettings().setUseWideViewPort(false);
		        			  mWebView.getSettings().setBuiltInZoomControls(true);
		        			  WebSettings setting = mWebView.getSettings();
		        					setting.setJavaScriptEnabled(true);
		        					setting.setBuiltInZoomControls(false);
		        					setting.setDisplayZoomControls(false);
		        					setting.setSupportZoom(false);
		        					setting.setDomStorageEnabled(true);
		        					setting.setDatabaseEnabled(true);
		      			  String resultss = string_Content.replace("\"", "'");
		      			  String resultss2 = resultss.replace("\\", "");
		      			  Log.e("resultss", resultss);
		      			  Log.e("resultss2", resultss2);
		      		      //�@ʾ����
		      			  mWebView.loadDataWithBaseURL(null, resultss2, null, "UTF-8", null);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(dialog.isShow()){
							dialog.cancel();
						}


					}
						break;

			default:
				break;
			}
		}
	};
	private void initView() {
		wb_condition =(WebView) findViewById(R.id.wb_condition);

		
	}
}
