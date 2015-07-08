package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

public class MyInfoActivity extends BaseActivity {
	private EditText mInfoName;
	private EditText mInfoTell;
	private EditText mInfoShow;
	private String username;
	private String usermobile;
	private String userintro1;
	private String userintro2;
	private String userintro;
	private String usertype;
	private String IMEI;
	private ProgressDialog dialog;
	private Button mBTInfosure;
	private TextView mTVInfo1;
	private EditText mRegjob1;
	private EditText mRegjob2;
	private EditText mRegjob3;
	private EditText mRegarea1;
	private EditText mRegarea2;
	private EditText mRegarea3;
	private String JOB2;
	private String JOB21;
	private String JOB3;
	private String JOB31;
	private String JOB1;
	private String JOB11;
	private String AREA1;
	private String AREA11;
	private String AREA2;
	private String AREA21;
	private String AREA3;
	private String AREA31;
	private String website;
	private String wechat;
	private String qqno;
	private String qqzone;
	private String whatapp;
	private EditText mETadd1;
	private EditText mETadd2;
	private EditText mETadd4;
	private EditText mETadd3;
	private EditText mETadd5;
	private String alias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo);
		initView();

        alias =getRandomString(14);
	}
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	private void setAlias() {
	       JPushInterface.setAlias(MyInfoActivity.this,alias, new TagAliasCallback() {
	   		@Override
	   		public void gotResult(int arg0, String arg1, Set<String> arg2) {
	   			String s =arg1;
	   			int a =arg0;
	   			if(a==0){
	   			handler.sendEmptyMessage(4);
	   			}else{
	   		    handler.sendEmptyMessage(5);
	   			}
	   		}
	   	});
	}


	@Override
	protected void onResume() {
		website = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("website", "");
		wechat = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("wechat", "");
		qqno = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("qqno", "");
		qqzone = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("qqzone", "");
		whatapp = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("whatapp", "");

		username = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("username", "");
		usermobile = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("usermobile", "");
		userintro1 = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("userintro1", "");
		userintro2 = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("userintro2", "");
		usertype = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("usertype", "");
		IMEI = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"IMEI", "");
		if (TextUtils.isEmpty(userintro1)) {
			userintro = userintro2;
		} else {
			userintro = userintro1;
		}
		mETadd1 =(EditText)this.findViewById(R.id.mETadd1);
		mETadd2 =(EditText)this.findViewById(R.id.mETadd2);
		mETadd3 =(EditText)this.findViewById(R.id.mETadd3);
		mETadd4 =(EditText)this.findViewById(R.id.mETadd4);
		mETadd5 =(EditText)this.findViewById(R.id.mETadd5);
		mInfoName = (EditText) this.findViewById(R.id.mInfoName);
		mInfoTell = (EditText) this.findViewById(R.id.mInfoTell);
		mInfoShow = (EditText) this.findViewById(R.id.mInfoShow);
		mInfoName.setText(username);
		mInfoTell.setText(usermobile);
		mInfoShow.setText(userintro);
		mETadd1.setText(website);
		mETadd2.setText(wechat);
		mETadd3.setText(qqno);
		mETadd4.setText(qqzone);
		mETadd5.setText(whatapp);
		// 第一级工种
		JOB2 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB2", "");
		JOB21 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB21", "");
		mRegjob1.setText(JOB21);
		// 第二级工种
		JOB3 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB3", "");
		JOB31 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB31", "");
		mRegjob2.setText(JOB31);
		// 第三级工种
		JOB1 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB1", "");
		JOB11 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB11", "");
		mRegjob3.setText(JOB11);
		// 第一级地区
		AREA1 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA1", "");
		AREA11 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA11", "");
		mRegarea1.setText(AREA11);
		// 第二级地区
		AREA2 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA2", "");
		AREA21 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA21", "");
		mRegarea2.setText(AREA21);
		// 第三级地区
		AREA3 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA3", "");
		AREA31 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA31", "");
		mRegarea3.setText(AREA31);


		super.onResume();
	}

	private void initView() {
		dialog = new com.webdesign488.post.view.ProgressDialog(
				MyInfoActivity.this);
		website = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("website", "");
		wechat = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("wechat", "");
		qqno = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("qqno", "");
		qqzone = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("qqzone", "");
		whatapp = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("whatapp", "");


		username = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("username", "");
		usermobile = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("usermobile", "");
		userintro1 = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("userintro1", "");
		userintro2 = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("userintro2", "");
		usertype = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
				.getString("usertype", "");
		IMEI = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"IMEI", "");
		mRegjob1 = (EditText) this.findViewById(R.id.mRegjob1);
		mRegjob1.setOnClickListener(listener);
		mRegjob2 = (EditText) this.findViewById(R.id.mRegjob2);
		mRegjob2.setOnClickListener(listener);
		mRegjob3 = (EditText) this.findViewById(R.id.mRegjob3);
		mRegjob3.setOnClickListener(listener);
		mRegarea1 = (EditText) this.findViewById(R.id.mRegarea1);
		mRegarea1.setOnClickListener(listener);
		mRegarea2 = (EditText) this.findViewById(R.id.mRegarea2);
		mRegarea2.setOnClickListener(listener);
		mRegarea3 = (EditText) this.findViewById(R.id.mRegarea3);
		mRegarea3.setOnClickListener(listener);

		JOB2 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB2", "");
		JOB21 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB21", "");
		mRegjob1.setText(JOB21);
		// 第二级工种
		JOB3 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB3", "");
		JOB31 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB31", "");
		mRegjob2.setText(JOB31);
		// 第三级工种
		JOB1 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB1", "");
		JOB11 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"JOB11", "");
		mRegjob3.setText(JOB11);
		// 第一级地区
		AREA1 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA1", "");
		AREA11 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA11", "");
		mRegarea1.setText(AREA11);
		// 第二级地区
		AREA2 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA2", "");
		AREA21 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA21", "");
		mRegarea2.setText(AREA21);
		// 第三级地区
		AREA3 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA3", "");
		AREA31 = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"AREA31", "");
		mRegarea3.setText(AREA31);

		if (TextUtils.isEmpty(userintro1)) {
			userintro = userintro2;
		} else {
			userintro = userintro1;
		}
		mInfoName = (EditText) this.findViewById(R.id.mInfoName);
		mInfoTell = (EditText) this.findViewById(R.id.mInfoTell);
		mInfoShow = (EditText) this.findViewById(R.id.mInfoShow);
		mETadd1 =(EditText)this.findViewById(R.id.mETadd1);
		mETadd2 =(EditText)this.findViewById(R.id.mETadd2);
		mETadd3 =(EditText)this.findViewById(R.id.mETadd3);
		mETadd4 =(EditText)this.findViewById(R.id.mETadd4);
		mETadd5 =(EditText)this.findViewById(R.id.mETadd5);

		mInfoName.setText(username);
		
		mInfoTell.setText(usermobile);
		mInfoShow.setText(userintro);
		mETadd1.setText(website);
		mETadd2.setText(wechat);
		mETadd3.setText(qqno);
		mETadd4.setText(qqzone);
		mETadd5.setText(whatapp);
		mETadd1.setFocusable(false);
		mETadd2.setFocusable(false);
		mETadd3.setFocusable(false);
		mETadd4.setFocusable(false);
		mETadd5.setFocusable(false);
		mInfoName.setFocusable(false);
		mInfoTell.setFocusable(false);
		mInfoShow.setFocusable(false);
		mBTInfosure = (Button) this.findViewById(R.id.mBTInfosure);
		mBTInfosure.setOnClickListener(listener);
		mTVInfo1 = (TextView) this.findViewById(R.id.mTVInfo1);
		if (usertype.equals("1")) {
			mTVInfo1.setText(getResources().getString(R.string.show2));
		} else if (usertype.equals("2")) {
			mTVInfo1.setText(getResources().getString(R.string.show1));

		} else if (usertype.equals("3")) {
			mTVInfo1.setText(getResources().getString(R.string.show3));
		}
	}

	OnClickListener listener = new OnClickListener() {

		private boolean mFlag =false;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mRegjob1:
				if(mFlag){
				startActivity(new Intent(MyInfoActivity.this,
						ChoiceJob6Activity.class));
				}
				break;
			case R.id.mRegjob2:
				if(mFlag){
				startActivity(new Intent(MyInfoActivity.this,
						ChoiceJob4Activity.class));
				}
				break;
			case R.id.mRegjob3:
				if(mFlag){
				startActivity(new Intent(MyInfoActivity.this,
						ChoiceJob1Activity.class));
				}
				break;
			case R.id.mRegarea1:
				if(mFlag){
				startActivity(new Intent(MyInfoActivity.this,
						ChoiceArea6Activity.class));
				}
				break;
			case R.id.mRegarea2:
				if(mFlag){
				startActivity(new Intent(MyInfoActivity.this,
						ChoiceArea4Activity.class));
				}
				break;
			case R.id.mRegarea3:
				if(mFlag){
				startActivity(new Intent(MyInfoActivity.this,
						ChoiceArea1Activity.class));
				}
				break;

			case R.id.mBTInfosure:
				if (mBTInfosure.getText().toString().equals(getResources().getString(R.string.change))) {
					mFlag =true;
					mInfoName.setFocusableInTouchMode(true);
					mInfoName.setFocusable(true);
					mInfoTell.setFocusable(true);
					mInfoShow.setFocusable(true);
					mETadd1.setFocusable(true);
					mETadd2.setFocusable(true);
					mETadd3.setFocusable(true);
					mETadd4.setFocusable(true);
					mETadd5.setFocusable(true);
					mETadd1.setFocusableInTouchMode(true);
					mETadd2.setFocusableInTouchMode(true);
					mETadd3.setFocusableInTouchMode(true);
					mETadd4.setFocusableInTouchMode(true);
					mETadd5.setFocusableInTouchMode(true);
					mInfoName.requestFocus(3);
					InputMethodManager inputManager =(InputMethodManager)mInfoName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					inputManager.showSoftInput(mInfoName, 0);
					mInfoTell.setFocusableInTouchMode(true);
					mInfoShow.setFocusableInTouchMode(true);
					mBTInfosure
							.setText(getResources().getString(R.string.sure));
				} else {
					mFlag =false;
					
			        new Thread(new Runnable() {
						@Override
						public void run() {
							setAlias();
						}
					}).start();
				}
				break;

			default:
				break;
			}
		}

	};

	private void initdata() {
		String userName = mInfoName.getText().toString();
		String userTell = mInfoTell.getText().toString();
		String infoshow = mInfoShow.getText().toString();
	    String website =mETadd1.getText().toString();
	    String wechat =mETadd2.getText().toString();
	    String qqno =mETadd3.getText().toString();
	    String qqzone =mETadd4.getText().toString();
	    String whatapp =mETadd5.getText().toString();
	
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		list.add("IMEI");
		list1.add(IMEI);
		list.add("alias");
		list1.add(alias);
		if (!TextUtils.isEmpty(website)) {
			list.add("website");
			list1.add(website);
		}
		if (!TextUtils.isEmpty(wechat)) {
			list.add("wechat");
			list1.add(wechat);
		}
		if (!TextUtils.isEmpty(qqno)) {
			list.add("qqno");
			list1.add(qqno);
		}
		if (!TextUtils.isEmpty(qqzone)) {
			list.add("qqzone");
			list1.add(qqzone);
		}
		if (!TextUtils.isEmpty(whatapp)) {
			list.add("whatapp");
			list1.add(whatapp);
		}

		
		if (!TextUtils.isEmpty(username)) {
			list.add("username");
			list1.add(userName);
		}
		if (!TextUtils.isEmpty(userTell)) {
			list.add("usermobile");
			list1.add(usermobile);
		}
		if (usertype.equals("1")) {
			list.add("userintro1");
			list1.add(infoshow);
			list.add("userintro2");
			list1.add(infoshow);

		} else {
			list.add("userintro1");
			list1.add(infoshow);
			list.add("userintro2");
			list1.add(infoshow);
		}
		list.add("categoryone");
		list.add("categorytwo");
		list.add("categorythree");
		list.add("locationone");
		list.add("locationtwo");
		list.add("locationthree");
		list1.add("|" +JOB2+"|");
		list1.add("|" +JOB3+"|");
		list1.add("|" +JOB1+"|");
		list1.add("|" +AREA1+"|");
		list1.add("|" +AREA2+"|");
		list1.add("|" +AREA3+"|");


		AsyncTaskUtil.getJson(list, list1, handler, dialog, Content.UPDATEINFO,
				MyInfoActivity.this, 3);

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.toast11), 1).show();
				break;
			case 3:
				InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imeManager.hideSoftInputFromWindow(getWindow().getDecorView()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

				String json = (String) msg.obj;
				if (!TextUtils.isEmpty(json)) {
					try {
						JSONObject jRoot = new JSONObject(json);
						int code = jRoot.getInt("code");
						if (code == 1) {
							JSONObject data = jRoot.getJSONObject("data");
							String username = data.getString("username");
							Integer usermobile = data.getInt("usermobile");
							String userintro1 = data.getString("userintro1");
							String userintro2 = data.getString("userintro2");
							String website = data.getString("website");
							String wechat = data.getString("wechat");
							String qqno = data.getString("qqno");
							String qqzone = data.getString("qqzone");
							String whatapp = data.getString("whatapp");

							SharedPreferences mySharedPreferences1 = getSharedPreferences(
									"AREA", Activity.MODE_PRIVATE);
							SharedPreferences.Editor editor1 = mySharedPreferences1
									.edit();
							editor1.putString("userintro1", userintro1);
							editor1.putString("userintro2", userintro2);
							editor1.putString("username", username);
							editor1.putString("usermobile", usermobile + "");
							editor1.putString("website", website + "");
							editor1.putString("wechat", wechat + "");
							editor1.putString("qqno", qqno + "");
							editor1.putString("qqzone", qqzone + "");
							editor1.putString("whatapp", whatapp + "");

							editor1.commit();
							mInfoName.setFocusableInTouchMode(false);
							mInfoTell.setFocusableInTouchMode(false);
							mInfoShow.setFocusableInTouchMode(false);
							mInfoName.setFocusable(false);
							mInfoTell.setFocusable(false);
							mInfoShow.setFocusable(false);

							mETadd1.setFocusableInTouchMode(false);
							mETadd2.setFocusableInTouchMode(false);
							mETadd3.setFocusableInTouchMode(false);
							mETadd4.setFocusableInTouchMode(false);
							mETadd5.setFocusableInTouchMode(false);
							mETadd4.setFocusable(false);
							mETadd5.setFocusable(false);
							mETadd1.setFocusable(false);
							mETadd2.setFocusable(false);
							mETadd3.setFocusable(false);

							mBTInfosure.setText(getResources().getString(
									R.string.change));

							Toast.makeText(MyInfoActivity.this,
									getResources().getString(R.string.toast8),
									0).show();
						} else {
							mInfoName.setText(username);
							mInfoTell.setText(usermobile);
							mInfoShow.setText(userintro);
                           mETadd1.setText(website);
                           mETadd2.setText(wechat);
                           mETadd3.setText(qqno);
                           mETadd4.setText(qqzone);
                           mETadd5.setText(whatapp);
							Toast.makeText(MyInfoActivity.this,
									getResources().getString(R.string.toast9),
									0).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
				break;
			case 4:
				initdata();
				break;
			case 5:
				Toast.makeText(getApplicationContext(), R.string.toast15, 1).show();
				break;

			default:
				break;
			}
		}
	};

}
