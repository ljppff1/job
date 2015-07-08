package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.a.SMainActivity;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.uinouser.MainActivityAll1;
import com.webdesign488.post.uinouser.MainActivityAllNoReg;
import com.webdesign488.post.util.AppManager;
import com.webdesign488.post.util.Content;

public class SpendActivity extends BaseActivity {
	private RelativeLayout mRlSpendLayout;
	private ImageView mIvSpendIcon;
	private TextView mTvSpendText;
	private com.webdesign488.post.view.ProgressDialog dialog;

	// 定义动画效果
	private TranslateAnimation translate; // 平移动画
	private AlphaAnimation aa; // 渐变动画
	private String IMEI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spend);
		initView();
		initdata();

	}

	private void initdata() {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		IMEI = tm.getDeviceId();
		//IMEI = System.currentTimeMillis() + "";

		SharedPreferences mySharedPreferences1 = getSharedPreferences("AREA",Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();
		editor1.putString("IMEI", IMEI);
		editor1.commit();
		List<String> list = new ArrayList<String>();
		list.add("IMEI");
		List<String> list1 = new ArrayList<String>();
		list1.add(IMEI);
		AsyncTaskUtil.getJson(list, list1, handler, dialog,
				Content.IS_REGISTER, SpendActivity.this, 3);

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.toast11), 1).show();
				  AlertDialog.Builder builder = new Builder(SpendActivity.this);
				  builder.setMessage(R.string.toast11);

				  builder.setTitle("提示");

				  builder.setPositiveButton("確定", new OnClickListener() {

				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				    dialog.dismiss();
				    initdata();
				   
				   }
				  });
				  
			/*	startActivity(new Intent(getApplicationContext(),
						MainActivityAll1.class));
				AppManager.getAppManager().finishActivity();*/

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

							SharedPreferences mySharedPreferences1 = getSharedPreferences(
									"AREA", Activity.MODE_PRIVATE);
							SharedPreferences.Editor editor1 = mySharedPreferences1
									.edit();
							editor1.putString("userintro1", userintro1);
							editor1.putString("userintro2", userintro2);
							editor1.putString("username", username);
							editor1.putString("usermobile", usermobile + "");
							editor1.putString("usertype", usertype + "");
							editor1.commit();

							startActivity(new Intent(getApplicationContext(),MainActivityAll1.class));
							AppManager.getAppManager().finishActivity();

						} else {
							// 跳转到注册界面
							startActivity(new Intent(getApplicationContext(),
									MainActivityAllNoReg.class));
							AppManager.getAppManager().finishActivity();
						}
					} catch (JSONException e) {
						  AlertDialog.Builder builder1 = new Builder(SpendActivity.this);
						  builder1.setMessage(R.string.toast15);
						  builder1.setTitle("提示");
						  builder1.setPositiveButton("確定", new OnClickListener() {

						   @Override
						   public void onClick(DialogInterface dialog, int which) {
						    dialog.dismiss();
						    initdata();
						   }
						  });

					//	startActivity(new Intent(getApplicationContext(),SMainActivity.class));
					//	AppManager.getAppManager().finishActivity();

					}

				}else{
					
					  AlertDialog.Builder builder1 = new Builder(SpendActivity.this);
					  builder1.setMessage(R.string.toast15);

					  builder1.setTitle("提示");

					  builder1.setPositiveButton("確定", new OnClickListener() {

					   @Override
					   public void onClick(DialogInterface dialog, int which) {
					    dialog.dismiss();
					    initdata();
					   
					   }
					  });
				//	startActivity(new Intent(getApplicationContext(),SMainActivity.class));
				//	AppManager.getAppManager().finishActivity();

				}
				break;

			default:
				break;
			}
		}
	};

	private void initView() {
		dialog = new com.webdesign488.post.view.ProgressDialog(
				SpendActivity.this);
		mRlSpendLayout = (RelativeLayout) this
				.findViewById(R.id.mRlSpendLayout);
		mIvSpendIcon = (ImageView) this.findViewById(R.id.mIvSpendIcon);
		mTvSpendText = (TextView) this.findViewById(R.id.mTvSpendText);

		translate = new TranslateAnimation(0, 0, 0, -30);
		translate.setDuration(1500);
		translate.setFillAfter(true);
		aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(1500);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mRlSpendLayout.setAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mTvSpendText.setVisibility(View.VISIBLE);
				mIvSpendIcon.setAnimation(translate);
				translate.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {

					}
				});
			}
		});

	}

}
