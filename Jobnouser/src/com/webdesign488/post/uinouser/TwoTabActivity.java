package com.webdesign488.post.uinouser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.webdesign488.post.MyRevActivity;
import com.webdesign488.post.NewInfo1Activity;
import com.webdesign488.post.NewInfoActivity;
import com.webdesign488.post.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TwoTabActivity extends Activity {

	private GridView gridView;
	private MyAdapter myAdapter;
	List<HashMap<String, Object>> gridViewList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);

		gridView = (GridView) findViewById(R.id.home_gridView);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		init();

		myAdapter = new MyAdapter();
		gridView.setAdapter(myAdapter);
		gridView.setOnItemClickListener(new MyOnItemClickListener());
	}

	public void init() {
		gridViewList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", getResources().getString(R.string.title_pub));
		map.put("image", R.drawable.ic_news);
		gridViewList.add(map);

		map = new HashMap<String, Object>();
		map.put("title",getResources().getString(R.string.title_new1));
		map.put("image", R.drawable.ic_projectcontract);
		gridViewList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", getResources().getString(R.string.title_myrev));
		map.put("image", R.drawable.ic_projectplan);
		gridViewList.add(map);
	}

	@Override
	protected void onResume() {
		super.onResume();
		myAdapter.notifyDataSetChanged();
	}
	private final class MyOnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 0:
			startActivity(new Intent(TwoTabActivity.this, NewInfoActivity.class));
				break;
			case 1:
				startActivity(new Intent(TwoTabActivity.this, NewInfo1Activity.class));
	
				break;
			case 2:
				startActivity(new Intent(TwoTabActivity.this, MyRevActivity.class));

				break;
			}

		}
	}
	
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return gridViewList.size();
		}

		@Override
		public Object getItem(int position) {
			return gridViewList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if(convertView == null){
				holder = new Holder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.homepage_item, null);
				holder.textView = (TextView) convertView.findViewById(R.id.tv_main_item_option);
				holder.imageView = (ImageView) convertView.findViewById(R.id.iv_home_gv_item);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			holder.textView.setText(gridViewList.get(position).get("title").toString());
			holder.imageView.setImageResource((Integer)gridViewList.get(position).get("image"));
			return convertView;
		}

		class Holder{
			TextView textView;
			ImageView imageView;
		}

	}

}
