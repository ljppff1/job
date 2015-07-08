/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.webdesign488.post.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.webdesign488.post.R;
import com.webdesign488.post.model.Job;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ChooseJob1Activity extends AbsListViewBaseActivity {

	String[] imageUrls;

	DisplayImageOptions options;
	private List<Job> list_job = new ArrayList<Job>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_image_grid);
		initView();
		initData();
	}

	private void initData() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		new MyTask().execute(params);
	}

	class MyTask extends AsyncTask<List<NameValuePair>, Integer, String> {

		private com.webdesign488.post.net.SDHttpClient sdClient;

		@Override
		protected void onPreExecute() {
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(List<NameValuePair>... params) {
			sdClient = new com.webdesign488.post.net.SDHttpClient();
			String json = null;
			try {
				json = sdClient.post(Content.JOB1, params[0]);
			} catch (Exception e) {

				e.printStackTrace();
			}
			Util.logh("JSON", json);
			if (dialog.isShow()) {
				dialog.cancel();
			}
			Message msg = new Message();
			msg.what = 1;
			msg.obj = json;
			handler.sendMessage(msg);
			return json;
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {

			case 1:
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
										"oid");
								String oname = data.getJSONObject(i).getString(
										"oname");
								String opic = data.getJSONObject(i).getString(
										"opic");
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

	private void initView() {
		dialog = new com.webdesign488.post.view.ProgressDialog(
				ChooseJob1Activity.this);
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
		/*
		 * Intent intent = new Intent(this, ImagePagerActivity.class);
		 * intent.putExtra(Extra.IMAGES, imageUrls);
		 * intent.putExtra(Extra.IMAGE_POSITION, position);
		 * startActivity(intent);
		 */}

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