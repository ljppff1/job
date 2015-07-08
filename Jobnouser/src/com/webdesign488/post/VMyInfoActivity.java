package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.uinouser.MainActivityAll1;
import com.webdesign488.post.util.AppManager;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.view.ProgressDialog;

public class VMyInfoActivity extends BaseActivity {
	private RadioGroup mRegRgoup;
	private EditText mRegName;
	private EditText mRegTell;
	private EditText mRegjob1;
	private EditText mRegjob2;
	private EditText mRegjob3;
	private EditText mRegarea1;
	private EditText mRegarea2;
	private EditText mRegarea3;
	private Button mRegsure;
	private String IMEI;
	private ProgressDialog dialog;
	private EditText mRegShow;
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
	private String alias;
	private EditText mETadd1;
	private EditText mETadd2;
	private EditText mETadd4;
	private EditText mETadd3;
	private EditText mETadd5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reginfov);
		clearDB();
		initView();
        alias =getRandomString(14);
        new Thread(new Runnable() {
			@Override
			public void run() {
				setAlias();
			}
		}).start();
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
	       JPushInterface.setAlias(VMyInfoActivity.this,alias, new TagAliasCallback() {
	   		
	   		@Override
	   		public void gotResult(int arg0, String arg1, Set<String> arg2) {
	   			String s =arg1;
	   			int a =arg0;
	   			
	   		}
	   	});

		
	}

	private void clearDB() {
		SharedPreferences sp = getSharedPreferences("AREA", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();

	}

	private void initView() {
		dialog = new com.webdesign488.post.view.ProgressDialog(VMyInfoActivity.this);
		mRegRgoup = (RadioGroup) this.findViewById(R.id.mRegRgoup);
		mRegName = (EditText) this.findViewById(R.id.mRegName);
		mRegTell = (EditText) this.findViewById(R.id.mRegTell);
		mRegShow = (EditText) this.findViewById(R.id.mRegShow);
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
		mRegsure = (Button) this.findViewById(R.id.mRegsure);
		mRegsure.setOnClickListener(listener);
        
		
		mETadd1 =(EditText)this.findViewById(R.id.mETadd1);
		mETadd2 =(EditText)this.findViewById(R.id.mETadd2);
		mETadd3 =(EditText)this.findViewById(R.id.mETadd3);
		mETadd4 =(EditText)this.findViewById(R.id.mETadd4);
		mETadd5 =(EditText)this.findViewById(R.id.mETadd5);
		
		
	}

	/*
	 * protected void onResume() { String JOB2 =getSharedPreferences("AREA",
	 * Activity.MODE_PRIVATE).getString("JOB2", ""); String JOB21
	 * =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB21",
	 * ""); // mRegjob1.setText(JOB21);
	 * 
	 * 
	 * 
	 * };
	 */
	protected void onResume() {
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
	};

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mRegjob1:
				startActivity(new Intent(VMyInfoActivity.this,
						ChoiceJob6Activity.class));
				break;
			case R.id.mRegjob2:
				startActivity(new Intent(VMyInfoActivity.this,
						ChoiceJob4Activity.class));
				break;
			case R.id.mRegjob3:
				startActivity(new Intent(VMyInfoActivity.this,
						ChoiceJob1Activity.class));
				break;
			case R.id.mRegarea1:
				startActivity(new Intent(VMyInfoActivity.this,
						ChoiceArea6Activity.class));
				break;
			case R.id.mRegarea2:
				startActivity(new Intent(VMyInfoActivity.this,
						ChoiceArea4Activity.class));
				break;
			case R.id.mRegarea3:
				startActivity(new Intent(VMyInfoActivity.this,
						ChoiceArea1Activity.class));
				break;
			case R.id.mRegsure:
				makeSure();
				break;

			default:
				break;
			}
		}

	};

	private void makeSure() {
		if (TextUtils.isEmpty(mRegName.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast1), 0).show();
		} else if (TextUtils.isEmpty(mRegTell.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast2), 0).show();
		}else if (TextUtils.isEmpty(mETadd1.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast2a), 0).show();
		}else if (TextUtils.isEmpty(mETadd2.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast2b), 0).show();
		}else if (TextUtils.isEmpty(mETadd3.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast2c), 0).show();
		}else if (TextUtils.isEmpty(mETadd4.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast2d), 0).show();
		}else if (TextUtils.isEmpty(mETadd5.getText().toString())) {
			Toast.makeText(VMyInfoActivity.this,
					getResources().getString(R.string.toast2e), 0).show();
		} else {

			initdata();
		}
	}

	private void initdata() {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		IMEI = tm.getDeviceId();
		//IMEI = System.currentTimeMillis() + "";
		SharedPreferences mySharedPreferences1 = getSharedPreferences("AREA",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();
		editor1.putString("IMEI", IMEI);
		editor1.commit();
		String userName = mRegName.getText().toString();
		String userTell = mRegTell.getText().toString();
		int id = mRegRgoup.getCheckedRadioButtonId();
		String mode = "2";
		switch (id) {
		case R.id.mRegradio1:
			mode = "2";
			break;
		case R.id.mRegradio2:
			mode = "1";
			break;
		case R.id.mRegradio3:
			mode = "3";
			break;
		}
		mode = "3";
		String userShow = mRegShow.getText().toString();

		initData(IMEI, mode, userName, userTell, userShow);
	}

	private void initData(String iMEI2, String usertype, String username,
			String usermobile, String userShow) {

		List<String> list = new ArrayList<String>();
		list.add("IMEI");
		list.add("usertype");
		list.add("username");
		list.add("usermobile");
		list.add("userintro1");
		list.add("userintro2");
		list.add("categoryone");
		list.add("categorytwo");
		list.add("categorythree");
		list.add("locationone");
		list.add("locationtwo");
		list.add("locationthree");
		list.add("alias");
		list.add("website");
		list.add("wechat");
		list.add("qqno");
		list.add("qqzone");
		list.add("whatapp");
		List<String> list1 = new ArrayList<String>();
		list1.add(IMEI);
		list1.add(usertype);
		list1.add(username);
		list1.add(usermobile);
		if (usertype.equals("1")) {
			list1.add(userShow);
			list1.add(userShow);
		} else {
			list1.add(userShow);
			list1.add(userShow);
		}

		list1.add("|" +JOB2+"|");
		list1.add("|" +JOB3+"|");
		list1.add("|" +JOB1+"|");
		list1.add("|" +AREA1+"|");
		list1.add("|" +AREA2+"|");
		list1.add("|" +AREA3+"|");
        list1.add(alias);
        list1.add(mETadd1.getText().toString());
        list1.add(mETadd2.getText().toString());
        list1.add(mETadd3.getText().toString());
        list1.add(mETadd4.getText().toString());
        list1.add(mETadd5.getText().toString());
		AsyncTaskUtil.getJson(list, list1, handler, dialog, Content.REGISTER,
				VMyInfoActivity.this, 3);
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.toast11), 1).show();
				break;
			case 3:
				String json = (String) msg.obj;
				if (!TextUtils.isEmpty(json)) {
					try {
						JSONObject jRoot = new JSONObject(json);
						int code = jRoot.getInt("code");
						if (code == 1) {
							JSONObject data = jRoot.getJSONObject("data");
							Integer usertype = data.getInt("usertype");
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
							editor1.putString("usertype", usertype + "");
							editor1.putString("website", website + "");
							editor1.putString("wechat", wechat + "");
							editor1.putString("qqno", qqno + "");
							editor1.putString("qqzone", qqzone + "");
							editor1.putString("whatapp", whatapp + "");

							editor1.commit();
							
							
/*                            if(!AppManager.getAppManager().hasActivity(MainActivity.class)){
    							startActivity(new Intent(VMyInfoActivity.this,
    									MainActivity.class));
    							AppManager.getAppManager().finishActivity();

                            }
*/					    		startActivity(new Intent(VMyInfoActivity.this,MainActivityAll1.class));
                                 AppManager.getAppManager().finishActivity();

							} else if (code == 0) {
							// 跳转到注册界面
							// startActivity(new Intent(getApplicationContext(),
							// RegActivity.class));
							String msg1 = jRoot.getString("msg");
							Toast.makeText(getApplicationContext(), msg1, 0)
									.show();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				break;

			default:
				break;
			}
		}
  	};

}
