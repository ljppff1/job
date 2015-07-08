package com.webdesign488.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.model.ForInfo;
import com.webdesign488.post.model.Info;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;
import com.webdesign488.post.view.XListView;
import com.webdesign488.post.view.XListView.IXListViewListener;

/*
 * 以招聘者身份进入
 */
public class NewInfoActivity extends BaseActivity implements IXListViewListener {
     private XListView mLvnew1;
     private List<Info> listInfo =new ArrayList<Info>();
	private String IMEI;
     private boolean flag =false;
     private String ID;
 	private View layout;
 	private Button mBTW1success;
 	private Button mBTW1next;
 	private PopupWindow mPop;
 	private PopupWindow mPop1;
 	private TextView mTVw1name;
 	private TextView mTVw1show;
 	private TextView mTVw1tell;
 	private TextView mTVw1Id;
 	private int mPosition =0;
    private List<ForInfo> listInfo1 =new ArrayList<ForInfo>();
    private List<ForInfo> listInfo2 =new ArrayList<ForInfo>();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.newinfo);
    	initView();
    	initData();
    }
   @Override
protected void onResume() {
	if(flag){
		flag =false;
		initData();
	}
	super.onResume();
}
   @Override
protected void onPause() {
	   flag =true;
	super.onPause();
}
 private void initData() {
		IMEI =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("IMEI", "");
	     List<String> list =new ArrayList<String>();
	     List<String> list1 =new ArrayList<String>();
	     list.add("IMEI");
	     list1.add(IMEI);
	     mPosition =0;
	      AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.PUBLIST, NewInfoActivity.this, 3);
	}
 private InfoAdapter adapter;

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
						JSONArray data =jRoot.getJSONArray("data");
						listInfo.clear();
						for(int i=0;i<data.length();i++){
							Integer id = data.getJSONObject(i).getInt("id");
      						String title =  data.getJSONObject(i).getString("title");
      						String status =  data.getJSONObject(i).getString("status");
      						
                         Info info =new Info();
                         info.setId(id+"");
                         info.setTitle(title);
                         info.setStatus(status);
                         listInfo.add(info);
						}
					adapter =new InfoAdapter();
					mLvnew1.setAdapter(adapter);
					}else{
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			if (dialog.isShow()) {
				dialog.cancel();
			}
			break;
		case 4:
			String json1 =(String) msg.obj;
			if(!TextUtils.isEmpty(json1)){
				try {
					JSONObject jRoot = new JSONObject(json1);
					int code = jRoot.getInt("code");
					if(code ==1){
						JSONArray data =jRoot.getJSONArray("data");
						listInfo1.clear();
						for(int i=0;i<data.length();i++){
						ForInfo info =new ForInfo();
						info.setAid( data.getJSONObject(i).getInt("aid")+"");
						info.setAstatus( data.getJSONObject(i).getInt("astatus")+"");
						info.setAmobile(data.getJSONObject(i).getString("usermobile")+"");
						info.setAname(data.getJSONObject(i).getString("username"));
						info.setAdate(data.getJSONObject(i).getString("adate"));
						info.setUserintro(data.getJSONObject(i).getString("userintro"));
						listInfo1.add(info);
						}
						boolean b =false;
						for(int i=0;i<listInfo1.size();i++){
						if(listInfo1.get(i).getAstatus().equals("2")){
							b =true;
						}
						}
						    if(b){
								for(int i=0;i<listInfo1.size();i++){
									if(listInfo1.get(i).getAstatus().equals("2")){
										listInfo2.clear();
										listInfo2.add(listInfo1.get(i));
									}
									}

						    	 showDialog2();
						    }else{
							 showDialog1();
						    }
					}else{
					/*	 Ifaid =22;
						 Ifusername ="dd";
						 Ifusermobile="cc";
						 Ifuserintro ="bb";
						 */
						// showDialog1();
                          Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast10), 0).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			if (dialog.isShow()) {
				dialog.cancel();
			}
			break;
		case 5:
			String json2 =(String) msg.obj;
			if(!TextUtils.isEmpty(json2)){
				try {
					JSONObject jRoot = new JSONObject(json2);
					int code = jRoot.getInt("code");
					if(code ==1){
						listInfo.get(mPosition).setStatus("2");
						if (mPop != null && mPop.isShowing()) {
							mPop.dismiss();
							mPop = null;
						}
						mLvnew1.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					//	mLvnew1.setSelection(mPosition);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.psuccess), 0).show();
					}else if(code==0){
						String msg1 =jRoot.getString("msg");
		                Toast.makeText(getApplicationContext(), msg1, 0).show();
					}
				} catch (JSONException e) {
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
private ProgressDialog dialog;

	private void initView() {
		dialog =new com.webdesign488.post.view.ProgressDialog(NewInfoActivity.this);

		mLvnew1 =(XListView)this.findViewById(R.id.mLvnew1);
		mLvnew1.setCacheColorHint(0);
		mLvnew1.setPullLoadEnable(true);
		mLvnew1.setXListViewListener(this);
		mLvnew1.setHeaderDividersEnabled(false);
		mLvnew1.setFooterDividersEnabled(false);
	}

	@Override
	public void onRefresh() {
		initData();
		onLoad();
	}

	@Override
	public void onLoadMore() {
		onLoad();
		}
	private void onLoad() {
		mLvnew1.stopRefresh();
		mLvnew1.stopLoadMore();
		mLvnew1.setRefreshTime(new Date().toLocaleString());
	}

	class InfoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listInfo.size();
		}

		@Override
		public Object getItem(int position) {
			return listInfo.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHodler holder;
			View view = convertView;
			if (view == null) {
				view = View.inflate(getApplicationContext(), R.layout.item_info, null);
				holder = new ViewHodler();
				holder.mTVInfoTitle = (TextView) view.findViewById(R.id.mTVInfoTitle);
				//holder.mBTlistInfo1 =(Button)view.findViewById(R.id.mBTlistInfo1);
				holder.mBTlistInfo2 =(Button)view.findViewById(R.id.mBTlistInfo2);
				view.setTag(holder);
			} else {
				holder = (ViewHodler) view.getTag();
			}
			holder.mTVInfoTitle.setText(listInfo.get(position).getTitle());
			if(listInfo.get(position).getStatus().equals("2")){
				holder.mBTlistInfo2.setText("成功");
			}else{
				holder.mBTlistInfo2.setText("回復者");
			}
			holder.mBTlistInfo2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ID=listInfo.get(position).getId();
					
				//	initData1(listInfo.get(position).getId());
					
					Intent intent=new Intent(getApplicationContext(), ReviewActivity.class);
					intent.putExtra("ID", ID);
					startActivity(intent);
				}
			});
			
			return view;
		}
	
	
			   

		 class ViewHodler {
			private TextView mTVInfoTitle;
			private Button mBTlistInfo1,mBTlistInfo2;
		}


	}
	OnClickListener listener =new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
		 switch (v.getId()) {
			case R.id.mTVw1Id:
				if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;
					
				}
				break;
			case R.id.mTVw1Ida:
				if (mPop1 != null && mPop1.isShowing()) {
					mPop1.dismiss();
					mPop1 = null;
					
				}
				break;
			case R.id.mBTW1nexta:
				if (mPop1 != null && mPop1.isShowing()) {
					mPop1.dismiss();
					mPop1 = null;
				}
				break;
		case R.id.mBTgocancel1:
			if (mPop != null && mPop.isShowing()) {
				mPop.dismiss();
				mPop = null;
			}                
			break;
		case R.id.mBTgocancel:
			initData3();
			break;
		case R.id.mBTtell1:
            if(listInfo1.size()>(mPosition+1)){
            	mPosition =mPosition+1;
            	 showDialog1();
            }else{
            	Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast12), 0).show();
            }
			break;
		case R.id.mBTtell:
			if(!TextUtils.isEmpty(listInfo1.get(mPosition).getAmobile())){
			 Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+listInfo1.get(mPosition).getAmobile()));
			  startActivity(intent);
			}else{
				Toast.makeText(getApplicationContext(), R.string.toast14, 0).show();
			}
			break;
		case R.id.mBTtella:
			if(!TextUtils.isEmpty(listInfo1.get(mPosition).getAmobile())){
				 Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+listInfo1.get(mPosition).getAmobile()));
				  startActivity(intent);
				}else{
			     Toast.makeText(getApplicationContext(), R.string.toast14, 0).show();
				}
			break;
		default:
			break;
		}
		}
	};
	private TextView mTVw1time;
	private Button mBTtell;
	private Button mBTgocancel1;
	private Button mBTW1nexta;
	private TextView mTVw1namea;
	private TextView mTVw1showa;
	private TextView mTVw1tella;
	private TextView mTVw1timea;
	private Button mBTtella;
	private TextView mTVw1Ida;

	private void showDialog1() {
		/*
		 * LayoutInflater mInflater =
		 * LayoutInflater.from(ExecuteOrderActivity.this); layout =
		 * mInflater.inflate(R.layout.window, null);
		 */
		if(mPop!=null){
			mTVw1name.setText(listInfo1.get(mPosition).getAname());
			mTVw1show.setText("");
			mTVw1tell.setText(listInfo1.get(mPosition).getAmobile());
			mTVw1time.setText(listInfo1.get(mPosition).getAdate());

		}else{
		layout = View.inflate(NewInfoActivity.this, R.layout.window1, null);
		layout.setFocusable(true); // 这个很重要
		layout.setFocusableInTouchMode(true);
		mPop = new PopupWindow(layout, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT,true);
		mPop.setFocusable(true);
        
		mPop.showAtLocation(
				NewInfoActivity.this.findViewById(R.id.mLlPubInfo1),
				Gravity.CENTER, 0, 0);// 在屏幕顶部|居右，带偏移
		mBTtell =(Button)layout.findViewById(R.id.mBTtell);
		mBTtell.setOnClickListener(listener);
		mBTgocancel1 =(Button)layout.findViewById(R.id.mBTgocancel1);
		mBTgocancel1.setOnClickListener(listener);
		mBTW1success = (Button)layout.findViewById(R.id.mBTgocancel);
		mBTW1next =(Button)layout.findViewById(R.id.mBTtell1);
		mBTW1success.setOnClickListener(listener);
		mBTW1next.setOnClickListener(listener);
		mTVw1name =(TextView)layout.findViewById(R.id.mTVw1name);
		mTVw1show =(TextView)layout.findViewById(R.id.mTVw1show);
		mTVw1tell =(TextView)layout.findViewById(R.id.mTVw1tell);
		mTVw1time =(TextView)layout.findViewById(R.id.mTVw1time);
		mTVw1name.setText(listInfo1.get(mPosition).getAname());
		mTVw1show.setText(listInfo1.get(mPosition).getUserintro());
		mTVw1tell.setText(listInfo1.get(mPosition).getAmobile());
		mTVw1time.setText(listInfo1.get(mPosition).getAdate());
		mTVw1Id =(TextView)layout.findViewById(R.id.mTVw1Id);
		mTVw1Id.setOnClickListener(listener);
		// mPop.setFocusable(true);
		// mPop.setOutsideTouchable(true);
		
		layout.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					if (mPop != null && mPop.isShowing()) {
						mPop.dismiss();
						mPop = null;
						
						return true;
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
/*					if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;
				}
*/					return true;
			}
		});
		}
	}

	private void showDialog2() {
		/*
		 * LayoutInflater mInflater =
		 * LayoutInflater.from(ExecuteOrderActivity.this); layout =
		 * mInflater.inflate(R.layout.window, null);
		 */
		layout = View.inflate(NewInfoActivity.this, R.layout.window11, null);
		layout.setFocusable(true); // 这个很重要
		layout.setFocusableInTouchMode(true);
		mPop1 = new PopupWindow(layout, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT,true);
		mPop1.setFocusable(true);
        
		mPop1.showAtLocation(
				NewInfoActivity.this.findViewById(R.id.mLlPubInfo1),
				Gravity.CENTER, 0, 0);// 在屏幕顶部|居右，带偏移
		mBTW1nexta =(Button)layout.findViewById(R.id.mBTW1nexta);
		mBTW1nexta.setOnClickListener(listener);
		mBTtella =(Button)layout.findViewById(R.id.mBTtella);
		mBTtella.setOnClickListener(listener);
		mTVw1Ida =(TextView)layout.findViewById(R.id.mTVw1Ida);
		mTVw1Ida.setOnClickListener(listener);
		mTVw1namea =(TextView)layout.findViewById(R.id.mTVw1namea);
		mTVw1showa =(TextView)layout.findViewById(R.id.mTVw1showa);
		mTVw1tella =(TextView)layout.findViewById(R.id.mTVw1tella);
		mTVw1timea =(TextView)layout.findViewById(R.id.mTVw1timea);
		mTVw1namea.setText(listInfo2.get(0).getAname());
		mTVw1showa.setText(listInfo2.get(0).getUserintro());
		mTVw1tella.setText(listInfo2.get(0).getAmobile());
		mTVw1timea.setText(listInfo2.get(0).getAdate());
		// mPop.setFocusable(true);
		// mPop.setOutsideTouchable(true);
		
		layout.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					if (mPop1 != null && mPop1.isShowing()) {
						mPop1.dismiss();
						mPop1 = null;
						
						return true;
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
/*					if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;
				}
*/					return true;
			}
		});
	}

	 private void initData1(String id) {
			IMEI =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("IMEI", "");
		
		     List<String> list =new ArrayList<String>();
		     List<String> list1 =new ArrayList<String>();
		     list.add("IMEI");
		     list1.add(IMEI);
		     list.add("id");
		     list1.add(id);
		      AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.APPLY, NewInfoActivity.this, 4);
		}
	 private void initData3() {
			IMEI =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("IMEI", "");
		     List<String> list =new ArrayList<String>();
		     List<String> list1 =new ArrayList<String>();
		     list.add("IMEI");
		     list1.add(IMEI);
		     list.add("id");
		     list1.add(ID);
		     list.add("aid");
		     list1.add(listInfo1.get(mPosition).getAid()+"");
		      AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.APPLYSUCCESS, NewInfoActivity.this,5);
		}
/*			 private void initData2() {
					IMEI =getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("IMEI", "");
				     List<String> list =new ArrayList<String>();
				     List<String> list1 =new ArrayList<String>();
				     list.add("IMEI");
				     list1.add(IMEI);
				     list.add("id");
				     list1.add(ID);
				     list.add("aid");
				     list1.add(Ifaid+"");
				      AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.APPLY, NewInfoActivity.this,6);
				}
*/}
