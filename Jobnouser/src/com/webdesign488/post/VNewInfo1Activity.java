package com.webdesign488.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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

import com.webdesign488.post.model.Info;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;
import com.webdesign488.post.view.XListView;
import com.webdesign488.post.view.XListView.IXListViewListener;
/**
 * 求职者身份进入
 * @author liujun
 *
 */
public class VNewInfo1Activity extends BaseActivity implements
		IXListViewListener {
	private XListView mLvnew1;
	private List<Info> listInfo = new ArrayList<Info>();
	private String IMEI;
	private boolean flag = false;
	private View layout;
	private Button mBTW1next;
	private PopupWindow mPop;
	private TextView mTVw1name;
	private TextView mTVw1show;
	private TextView mTVw1tell;
	private TextView mTVw1Id;
	private TextView mTVw1title;
	private TextView mTVw1content;
	private TextView mTVw1area;
	private TextView mTVw1local;
	private String Iftitle;
	private String Ifcontent;
	private String Ifcategory;
	private String Iflocation;
	private String Ifaid;
	private String Ifusername;
	private String Ifusermobile;
	private String Ifuserintro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newinfo1);
		initView();
		initData();
	}

	@Override
	protected void onResume() {
		if (flag&&listInfo.size()<=0) {
			initData();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		flag = true;
		super.onPause();
	}

	private void initData() {
		IMEI = getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString(
				"IMEI", "");
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		list.add("IMEI");
		list1.add(IMEI);
		AsyncTaskUtil.getJson(list, list1, handler, dialog, Content.FORJOB,
				VNewInfo1Activity.this, 3);
	}

	private InfoAdapter adapter;

	private Handler handler = new Handler() {

		private View mRLwindow2bottom;
		private Button mBTgocancel;

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
							JSONArray data = jRoot.getJSONArray("data");
							listInfo.clear();
							for (int i = 0; i < data.length(); i++) {
								Integer id = data.getJSONObject(i).getInt("id");
								String title = data.getJSONObject(i).getString(
										"title");
								Info info = new Info();
								info.setId(id + "");
								info.setTitle(title);
								listInfo.add(info);
							}
							adapter = new InfoAdapter();
							mLvnew1.setAdapter(adapter);
						} else {
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
				String json1 = (String) msg.obj;
				if (!TextUtils.isEmpty(json1)) {
					try {
						JSONObject jRoot = new JSONObject(json1);
						int code = jRoot.getInt("code");
						if (code == 1) {
							JSONObject data = jRoot.getJSONObject("data");
							Ifusername = data.getString("username");
							Ifusermobile = data.getString("usermobile");
							Ifuserintro = data.getString("userintro");
							Iftitle = data.getString("title");
							Ifcontent = data.getString("content");
							String categoryone = data.getString("categoryone");
							String categorytwo = data.getString("categorytwo");
							String categorythree = data
									.getString("categorythree");
							String locationone = data.getString("locationone");
							String locationtwo = data.getString("locationtwo");
							String locationthree = data
									.getString("locationthree");
							if (Util.isEmpty(locationtwo)) {
								Iflocation = locationone;
							} else if (Util.isEmpty(locationthree)) {
								Iflocation = locationone + ">" + locationtwo;
							} else {
								Iflocation = locationone + ">" + locationtwo
										+ ">" + locationthree;
							}
							if (Util.isEmpty(categorytwo)) {
								Ifcategory = categoryone;
							} else if (Util.isEmpty(categorythree)) {
								Ifcategory = categoryone + ">" + categorytwo;
							} else {
								Ifcategory = categoryone + ">" + categorytwo
										+ ">" + categorythree;
							}
							showDialog1();

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
				String json2 = (String) msg.obj;
				if (!TextUtils.isEmpty(json2)) {
					try {
						JSONObject jRoot = new JSONObject(json2);
						int code = jRoot.getInt("code");
						if (code == 1) {
							if (mPop != null && mPop.isShowing()) {
								mPop.dismiss();
								mPop = null;
							}
							Toast.makeText(
									getApplicationContext(),
									getResources()
											.getString(R.string.psuccess1), 0)
									.show();
						} else if (code == 0) {
							if (mPop != null && mPop.isShowing()) {
								mPop.dismiss();
								mPop = null;
							}
							String msg1 = jRoot.getString("msg");
							Toast.makeText(getApplicationContext(), msg1, 0)
									.show();
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

		private void showDialog1() {
			/*
			 * LayoutInflater mInflater =
			 * LayoutInflater.from(ExecuteOrderActivity.this); layout =
			 * mInflater.inflate(R.layout.window, null);
			 */
			
			if(mPop==null){
				layout = View
						.inflate(VNewInfo1Activity.this, R.layout.window2, null);
				layout.setFocusable(true); // 这个很重要
				layout.setFocusableInTouchMode(true);

			mPop = new PopupWindow(layout, LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT, true);
			mPop.setFocusable(true);

			mPop.showAtLocation(
					VNewInfo1Activity.this.findViewById(R.id.mLlPubInfo1),
					Gravity.CENTER, 0, 0);// 在屏幕顶部|居右，带偏移
			mBTW1next = (Button) layout.findViewById(R.id.mBTW1next);
			mBTW1next.setOnClickListener(listener);
			mBTW1next.setText(R.string.sure);
			mBTgocancel =(Button)layout.findViewById(R.id.mBTgocancel);
			mBTgocancel.setVisibility(View.GONE);
			mTVw1name = (TextView) layout.findViewById(R.id.mTVw1name);
			mTVw1show = (TextView) layout.findViewById(R.id.mTVw1show);
			mTVw1tell = (TextView) layout.findViewById(R.id.mTVw1tell);
			mTVw1title = (TextView) layout.findViewById(R.id.mTVw1title);
			mTVw1content = (TextView) layout.findViewById(R.id.mTVw1content);
			mTVw1area = (TextView) layout.findViewById(R.id.mTVw1area);
			mTVw1local = (TextView) layout.findViewById(R.id.mTVw1local);
			mTVw1name.setText(Ifusername);
			mTVw1show.setText(Ifuserintro);
			mTVw1tell.setText(Ifusermobile);
			mTVw1title.setText(Iftitle);
			mTVw1content.setText(Ifcontent);
			mTVw1area.setText(Iflocation);
			mTVw1local.setText(Ifcategory);
			mTVw1Id = (TextView) layout.findViewById(R.id.mTVw1Id);
			mTVw1Id.setOnClickListener(listener);

			// mPop.setFocusable(true);
			// mPop.setOutsideTouchable(true);
			}
			layout.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_BACK:
						flag = false;
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
					/*
					 * if (mPop != null && mPop.isShowing()) { mPop.dismiss();
					 * mPop = null; }
					 */return true;
				}
			});

		}
	};
	private ProgressDialog dialog;

	private void initView() {
		dialog = new com.webdesign488.post.view.ProgressDialog(
				VNewInfo1Activity.this);

		mLvnew1 = (XListView) this.findViewById(R.id.mLvnew1);
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHodler holder;
			View view = convertView;
			if (view == null) {
				view = View.inflate(getApplicationContext(),
						R.layout.item_info1, null);
				holder = new ViewHodler();
				holder.mTVInfoTitle = (TextView) view
						.findViewById(R.id.mTVInfoTitle);
				holder.mBTlistInfo2 = (Button) view
						.findViewById(R.id.mBTlistInfo2);
				view.setTag(holder);
			} else {
				holder = (ViewHodler) view.getTag();
			}
			holder.mTVInfoTitle.setText(listInfo.get(position).getTitle());

			holder.mBTlistInfo2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					initData1(listInfo.get(position).getId());
				}
			});

			return view;
		}

		private void initData1(String id) {
			IMEI = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
					.getString("IMEI", "");

			Ifaid = id;
			List<String> list = new ArrayList<String>();
			List<String> list1 = new ArrayList<String>();
			list.add("IMEI");
			list1.add(IMEI);
			list.add("id");
			list1.add(id);
			AsyncTaskUtil.getJson(list, list1, handler, dialog,
					Content.FORJOBDETAIL, VNewInfo1Activity.this, 4);
		}

		class ViewHodler {
			private TextView mTVInfoTitle;
			private Button mBTlistInfo1, mBTlistInfo2;
		}

	}

	OnClickListener listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mTVw1Id:
				flag = false;
				if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;

				}
				break;
			case R.id.mBTW1next:
				if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;

				}

				break;
			default:
				break;
			}
		}

		private void initData3() {
			IMEI = getSharedPreferences("AREA", Activity.MODE_PRIVATE)
					.getString("IMEI", "");

			List<String> list = new ArrayList<String>();
			List<String> list1 = new ArrayList<String>();
			list.add("IMEI");
			list1.add(IMEI);
			list.add("id");
			list1.add(Ifaid);
			AsyncTaskUtil.getJson(list, list1, handler, dialog,
					Content.FORJOBTHIS, VNewInfo1Activity.this, 5);

		}
	};

}
