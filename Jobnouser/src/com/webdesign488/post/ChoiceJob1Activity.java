package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.model.Area;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.view.ProgressDialog;

public class ChoiceJob1Activity extends BaseActivity {
	private ListView mLvArea1;
	private List<Area> area1List = new ArrayList<Area>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.area1);
		initView();
		initData();
	}

	private void initData() {
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		AsyncTaskUtil.getJson(list, list1, handler, dialog, Content.JOB1,
				ChoiceJob1Activity.this, 3);

	}

	private AreaAdapter adapter;
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
							area1List.clear();
							for (int i = 0; i < data.length(); i++) {
								Integer areaId = data.getJSONObject(i).getInt(
										"oid");
								String areaName = data.getJSONObject(i)
										.getString("oname");
								Area area = new Area();
								area.setAreaId(areaId + "");
								area.setAreaName(areaName);
								area1List.add(area);
							}
							adapter = new AreaAdapter();
							mLvArea1.setAdapter(adapter);

						} else {
							// 跳转到注册界面
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
	private ProgressDialog dialog;
	private TextView mTVarea1;

	private void initView() {
		mTVarea1 = (TextView) this.findViewById(R.id.mTVarea1);
		mTVarea1.setText(getResources().getString(R.string.title_job1));
		mLvArea1 = (ListView) this.findViewById(R.id.mLvArea1);
		mLvArea1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ChoiceJob1Activity.this,
						ChoiceJob2Activity.class);
				intent.putExtra("ID", area1List.get(position).getAreaId());
				intent.putExtra("NAME", area1List.get(position).getAreaName());
				startActivity(intent);

			}

		});
		dialog = new com.webdesign488.post.view.ProgressDialog(
				ChoiceJob1Activity.this);
	}

	class Holder {
		TextView area1Name;
	}

	private class AreaAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return area1List.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return area1List.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_area1, null);
				holder = new Holder();
				holder.area1Name = (TextView) convertView
						.findViewById(R.id.area1name);
				convertView.setTag(holder);

			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.area1Name.setText(area1List.get(position).getAreaName());

			return convertView;
		}

	}

}
