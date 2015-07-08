package com.webdesign488.post;

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
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.webdesign488.post.model.Job;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.ui.AbsListViewBaseActivity;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

public class PubInfo2Activity extends AbsListViewBaseActivity {
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
		list.add("tid");
		List<String> list1 = new ArrayList<String>();
		list1.add(ID);
		AsyncTaskUtil.getJson(list, list1, handler, dialog, Content.JOB3,
				PubInfo2Activity.this, 3);

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
										"hid");
								String oname = data.getJSONObject(i).getString(
										"hname");
								String opic = data.getJSONObject(i).getString(
										"hpic");
								Job job = new Job();
								job.setId(oid + "");
								job.setName(oname);
								job.setPath(opic);
								list_job.add(job);
								imageUrls[i] = opic;
							}
							if (dialog.isShow()) {
								dialog.cancel();
							}
							((GridView) listView)
									.setAdapter(new ImageAdapter());

							options = new DisplayImageOptions.Builder()
									.showImageOnLoading(R.drawable.blue)
									.showImageForEmptyUri(R.drawable.white)
									.showImageOnFail(R.drawable.black)
									.cacheInMemory(true).cacheOnDisk(true)
									.considerExifParams(true)
									.bitmapConfig(Bitmap.Config.RGB_565)
									.build();

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

	private String ID1;

	private String NAME1;

	private void initView() {
		ID = getIntent().getExtras().getString("ID");
		ID1 = getIntent().getExtras().getString("ID1");
		NAME = getIntent().getExtras().getString("NAME");
		NAME1 = getIntent().getExtras().getString("NAME1");

		dialog = new com.webdesign488.post.view.ProgressDialog(
				PubInfo2Activity.this);
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
		Intent intent = new Intent(this, PubInfo3Activity.class);
		intent.putExtra("ID", list_job.get(position).getId());
		intent.putExtra("ID1", ID1);
		intent.putExtra("ID2", ID);
		intent.putExtra("NAME2", NAME);
		intent.putExtra("NAME1", NAME1);
		intent.putExtra("NAME", list_job.get(position).getName());
		startActivity(intent);
	}

	static class ViewHolder {
		ImageView imageView;
		ProgressBar progressBar;
		TextView mTVjob1;
	}

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
				view = getLayoutInflater().inflate(R.layout.item_grid_image,
						parent, false);
				holder = new ViewHolder();
				assert view != null;
				holder.imageView = (ImageView) view.findViewById(R.id.image);
				holder.progressBar = (ProgressBar) view
						.findViewById(R.id.progress);
				holder.mTVjob1 = (TextView) view.findViewById(R.id.mTVjob1);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			imageLoader.displayImage(imageUrls[position], holder.imageView,
					options, new SimpleImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							holder.progressBar.setProgress(0);
							holder.progressBar.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							holder.progressBar.setVisibility(View.GONE);
							holder.mTVjob1.setText(list_job.get(position)
									.getName());
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							holder.progressBar.setVisibility(View.GONE);
							holder.mTVjob1.setText(list_job.get(position)
									.getName());
						}
					}, new ImageLoadingProgressListener() {
						@Override
						public void onProgressUpdate(String imageUri,
								View view, int current, int total) {
							holder.progressBar.setProgress(Math.round(100.0f
									* current / total));
						}
					});

			return view;
		}
	}

}
