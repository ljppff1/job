package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.a.ChoiceArea1Activity;
import com.webdesign488.post.a.ChoiceArea4Activity;
import com.webdesign488.post.a.ChoiceArea6Activity;
import com.webdesign488.post.a.PubInfo2Activity;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.AppManager;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.view.ProgressDialog;

public class PubInfo4Activity extends BaseActivity {
	private String AREA1;
	private String AREA11;
	private String AREA2;
	private String AREA21;
	private String AREA3;
	private String AREA31;
	private EditText mRegarea1;
	private EditText mRegarea2;
	private EditText mRegarea3;

	
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.pubinfo4);
    	initDB();
		initView();
		//initData();
    	
    }
	

	private void initDB() {

		SharedPreferences sp = getSharedPreferences("AREAAPPLY", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();
		
	}


	private ProgressDialog dialog;

	private String ID;

	private String NAME;

	private EditText mPubTitle;

	private EditText mPubContent;

	private String AREA5;

	private String AREA51;

	private String userintro1;

	private String userintro2;

	private String username;

	private String usertype;

	private String usermobile;

	private Button mPubsure;

	private String ID1;

	private String ID2;

	private String AREA5a;

	private String AREA5b;

	private String NAME1;

	private String NAME2;

	private TextView mTvPubBe1;

	   

	@Override
	protected void onResume() {
		// 第一级地区
		AREA1 = getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString(
				"AREA1", "");
		AREA11 = getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString(
				"AREA11", "");
		mRegarea1.setText(AREA11);
		// 第二级地区
		AREA2 = getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString(
				"AREA2", "");
		AREA21 = getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString(
				"AREA21", "");
		mRegarea2.setText(AREA21);
		// 第三级地区
		AREA3 = getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString(
				"AREA3", "");
		AREA31 = getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString(
				"AREA31", "");
		mRegarea3.setText(AREA31);
		super.onResume();
	}
	
	private void initView() {
		 IMEI =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("IMEI", "");
		 username =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("username", "");
		 usermobile =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("usermobile", "");
		 userintro1 =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("userintro1", "");
		 userintro2 =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("userintro2", "");

		 mPubsure =(Button)this.findViewById(R.id.mPubsure);
		 mPubsure.setOnClickListener(listener);
			ID =getIntent().getExtras().getString("ID");
			ID1 =getIntent().getExtras().getString("ID1");
			ID2 =getIntent().getExtras().getString("ID2");
			NAME =getIntent().getExtras().getString("NAME");
			NAME1 =getIntent().getExtras().getString("NAME1");
			NAME2 =getIntent().getExtras().getString("NAME2");
			mTvPubBe1 =(TextView)this.findViewById(R.id.mTvPubBe1);
			mTvPubBe1.setText(NAME1+">"+NAME2+">"+NAME);
			
		mPubTitle =(EditText)this.findViewById(R.id.mPubTitle);
		mPubContent =(EditText)this.findViewById(R.id.mPubContent);
		mRegarea1 = (EditText) this.findViewById(R.id.mRegarea1);
		mRegarea1.setOnClickListener(listener);
		mRegarea2 = (EditText) this.findViewById(R.id.mRegarea2);
		mRegarea2.setOnClickListener(listener);
		mRegarea3 = (EditText) this.findViewById(R.id.mRegarea3);
		mRegarea3.setOnClickListener(listener);

		
		dialog =new com.webdesign488.post.view.ProgressDialog(PubInfo4Activity.this);
	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mPubsure:
				makeSure();
				break;
			case R.id.mRegarea1:
				startActivity(new Intent(PubInfo4Activity.this,
						ChoiceArea6Activity.class));
				break;
			case R.id.mRegarea2:
				startActivity(new Intent(PubInfo4Activity.this,
						ChoiceArea4Activity.class));
				break;
			case R.id.mRegarea3:
				startActivity(new Intent(PubInfo4Activity.this,
						ChoiceArea1Activity.class));
				break;

			default:
				break;
			}
		}

	};

	private String IMEI;
	private void makeSure() {
		if(TextUtils.isEmpty(mPubTitle.getText().toString())){
			Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast3), 0).show();
		}else if(TextUtils.isEmpty(mPubContent.getText().toString())){
			Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast4), 0).show();
		}else if(TextUtils.isEmpty(mRegarea1.getText().toString())){
			Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast41), 0).show();
		}/*else if(TextUtils.isEmpty(mRegarea2.getText().toString())){
			Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast42), 0).show();
		}else if(TextUtils.isEmpty(mRegarea3.getText().toString())){
			Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast43), 0).show();
		}*/else{
			initdata();
		}
	}
	private void initdata() {
		  String title =mPubTitle.getText().toString();
		  String content =mPubContent.getText().toString();
			
		 initData(title,content);
	}
	   private void initData(String title,String content) {
		  String  userintro ="";
		   if(!TextUtils.isEmpty(userintro1)){
			   userintro =userintro1;
		   }else if(!TextUtils.isEmpty(userintro2)){
			   userintro =userintro2;
		   }

		     List<String> list =new ArrayList<String>();
		     list.add("IMEI");
		     list.add("username");
		     list.add("usermobile");
		     list.add("userintro");
		     list.add("title");
		     list.add("content");
		     list.add("categoryone");
		     list.add("categorytwo");
		     list.add("categorythree");
		     list.add("locationone");
		     list.add("locationtwo");
		     list.add("locationthree");
		     List<String> list1 =new ArrayList<String>();
		     list1.add(IMEI);
		     list1.add(username);
		     list1.add(usermobile);
		     list1.add(userintro);
			 list1.add(title);
			 list1.add(content);

		     list1.add(ID1);
		     list1.add(ID2);
		     list1.add(ID);
				list1.add(AREA1);
				list1.add(AREA2);
				list1.add(AREA3);
       AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.PUBINFO, PubInfo4Activity.this, 3);

		}
	private Handler handler =new Handler(){
		   public void handleMessage(android.os.Message msg) {
			   switch (msg.what) {
			   case 1:
				   Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast11), 1).show();
				   break;
			case 3:
				String json =(String) msg.obj;
				if(!TextUtils.isEmpty(json)){
					try {
						JSONObject jRoot = new JSONObject(json);
						int code = jRoot.getInt("code");
					
						if(code ==1){
							Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast7), 0).show();
							if(AppManager.getAppManager().hasActivity(PubInfo2Activity.class)){
							AppManager.getAppManager().finishActivityJob3();
							}
						}else{
							Toast.makeText(PubInfo4Activity.this, getResources().getString(R.string.toast6), 0).show();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (dialog.isShow()) {
					dialog.cancel();
				}
				break;

			default:
				break;
			}
		   }  
		   };
	   

}
