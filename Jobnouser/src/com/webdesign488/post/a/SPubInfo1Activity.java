package com.webdesign488.post.a;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.webdesign488.post.R;
import com.webdesign488.post.a.PubInfoActivity.ViewHolder;
import com.webdesign488.post.model.Job;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.ui.AbsListViewBaseActivity;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

public class SPubInfo1Activity extends AbsListViewBaseActivity {
	String[] imageUrls;

	DisplayImageOptions options;
	private List<Job> list_job = new ArrayList<Job>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pubinfo);
		initView();
		initData();

	}

	private void initData() {
		List<String> list = new ArrayList<String>();
		list.add("oid");
		List<String> list1 = new ArrayList<String>();
		list1.add(ID);
		AsyncTaskUtil.getJson(list, list1, handler, dialog, Content.JOB2,
				SPubInfo1Activity.this, 3);

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
							JSONArray data = jRoot.getJSONArray("data");
							list_job.clear();
							imageUrls = new String[data.length()];

							for (int i = 0; i < data.length(); i++) {
								Integer oid = data.getJSONObject(i).getInt(
										"tid");
								String oname = data.getJSONObject(i).getString(
										"tname");
								String opic = data.getJSONObject(i).getString(
										"tpic");
								Job job = new Job();
								job.setId(oid + "");
								job.setName(oname);
								job.setPath(opic);
								list_job.add(job);
								
							}
							if (dialog.isShow()) {
								dialog.cancel();
							}
							((GridView) listView)
									.setAdapter(new ImageAdapter());

					

						} else {

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

	private ProgressDialog dialog;

	private String ID;

	private String NAME;

	private void initView() {
		ID = getIntent().getExtras().getString("ID");
		NAME = getIntent().getExtras().getString("NAME");

		dialog = new com.webdesign488.post.view.ProgressDialog(
				SPubInfo1Activity.this);
		listView = (GridView) findViewById(R.id.gridview);
		// ((GridView) listView).setAdapter(new ImageAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startImagePagerActivity(position);
			}
		});

	}

	private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, SPubInfo2Activity.class);
		intent.putExtra("ID", list_job.get(position).getId());
		intent.putExtra("ID1", ID);
		intent.putExtra("NAME1", NAME);
		intent.putExtra("NAME", list_job.get(position).getName());
		startActivity(intent);
	}

	static class ViewHolder {
        RelativeLayout mRLItemgrid1;
		TextView mTVjob1;
	}
    private int[] b =new int[]{R.drawable.ss1,R.drawable.ss2,R.drawable.ss3,R.drawable.ss4,R.drawable.ss5,R.drawable.ss6,R.drawable.ss7,R.drawable.ss8};

	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			View view = convertView;
			if (view == null) {
				view = getLayoutInflater().inflate(R.layout.item_grid_image2, parent, false);
				holder = new ViewHolder();
				holder.mTVjob1 =(TextView)view.findViewById(R.id.mTVjob1);
				holder.mRLItemgrid1 =(RelativeLayout)view.findViewById(R.id.mRLItemgrid1);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			
			 int a1 =(position+8)%8 ;
			 holder.mRLItemgrid1.setBackgroundResource(b[a1]);
			// holder.mRLItemgrid1.setBackgroundColor(a[3]);
			// holder.mTVjob1.setBackgroundColor(a[3]);
			// holder.mRLItemgrid1.setBackgroundColor(a[a1]);
			 
			 holder.mTVjob1.setText(list_job.get(position).getName());
			return view;
		}
	}

}
