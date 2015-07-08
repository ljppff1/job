package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.a.PubInfo2Activity;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.AppManager;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

public class PubInfo3Activity extends BaseActivity {

	
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.pubinfo1);
    	initDB();
		initView();
		//initData();
    	
    }
	

	private void initDB() {

		   SharedPreferences mySharedPreferences1=getSharedPreferences("AREA", Activity.MODE_PRIVATE); 
			SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
		     editor1.putString("AREA5",""); 
		     editor1.putString("AREA51","");
		     editor1.putString("AREA5a", "");
		     editor1.putString("AREA5b", "");

		     editor1.commit(); 
		
	}


	private ProgressDialog dialog;

	private String ID;

	private String NAME;

	private EditText mPubTitle;

	private EditText mPubContent;

	private EditText mPubarea;

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
		 AREA5 =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("AREA5", "");
		 AREA5a =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("AREA5a", "");
		 AREA5b =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("AREA5b", "");
		 AREA51 =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("AREA51", "");
		mPubarea.setText(AREA51);
		super.onResume();
	}
	
	private void initView() {
		 IMEI =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("IMEI", "");
		 userintro1 =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("userintro1", "");
		 userintro2 =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("userintro2", "");
		 username =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("username", "");
		 usertype =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("usertype", "");
		 usermobile =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("usermobile", "");
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
		mPubarea =(EditText)this.findViewById(R.id.mPubarea);
		mPubarea.setOnClickListener(listener);
		dialog =new com.webdesign488.post.view.ProgressDialog(PubInfo3Activity.this);
	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mPubsure:
				makeSure();
				break;
			case R.id.mPubarea:
				startActivity(new Intent(PubInfo3Activity.this, ChoiceArea7Activity.class));
				break;
			default:
				break;
			}
		}

	};

	private String IMEI;
	private void makeSure() {
		if(TextUtils.isEmpty(mPubTitle.getText().toString())){
			Toast.makeText(PubInfo3Activity.this, getResources().getString(R.string.toast3), 0).show();
		}else if(TextUtils.isEmpty(mPubContent.getText().toString())){
			Toast.makeText(PubInfo3Activity.this, getResources().getString(R.string.toast4), 0).show();
		}else if(TextUtils.isEmpty(mPubarea.getText().toString())){
			Toast.makeText(PubInfo3Activity.this, getResources().getString(R.string.toast5), 0).show();
		}else{
			initdata();
			
		}
	}
	private void initdata() {

		  String title =mPubTitle.getText().toString();
		  String content =mPubContent.getText().toString();
			String area =mPubarea.getText().toString();
			
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
		     list1.add(ID1);
		     list1.add(AREA5a);
		     list1.add(AREA5b);
		     list1.add(AREA5);
       AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.PUBINFO, PubInfo3Activity.this, 3);

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
							Toast.makeText(PubInfo3Activity.this, getResources().getString(R.string.toast7), 0).show();
							if(AppManager.getAppManager().hasActivity(PubInfo2Activity.class)){
							AppManager.getAppManager().finishActivityJob3();
							}
						}else{
							Toast.makeText(PubInfo3Activity.this, getResources().getString(R.string.toast6), 0).show();
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
