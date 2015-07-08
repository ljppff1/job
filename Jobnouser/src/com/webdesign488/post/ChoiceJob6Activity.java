package com.webdesign488.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.model.Area;
import com.webdesign488.post.net.AsyncTaskUtil;
import com.webdesign488.post.util.AppManager;
import com.webdesign488.post.util.Content;
import com.webdesign488.post.util.SharedPreferenceDB5;
import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

public class ChoiceJob6Activity extends BaseActivity {
     private ListView mLvArea1;
     private List<Area> area1List =new ArrayList<Area>();
     
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.area4);
    	initView();
    	initData();
    }
	private void initData() {
	     List<String> list =new ArrayList<String>();
	     List<String> list1 =new ArrayList<String>();
   AsyncTaskUtil.getJson(list, list1,handler, dialog, Content.JOB1, ChoiceJob6Activity.this, 3);
		
	}
	  
	   private AreaAdapter adapter;
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
								JSONArray data = jRoot.getJSONArray("data");
	                            area1List.clear();
	                            for(int i=0;i<data.length();i++){
	                            	Integer areaId = data.getJSONObject(i).getInt("oid");
	        						String areaName =  data.getJSONObject(i).getString("oname");
	        						Area area =new Area();
	        						area.setAreaId(areaId+"");
	        						area.setAreaName(areaName);
	                                if(SharedPreferenceDB5.isChecked(areaId+"", ChoiceJob6Activity.this)){
	                                	area.setIsChecked(true);
	                                }else{
	                                	area.setIsChecked(false);
	                                }
                                    area1List.add(area);
	                            }
	                            adapter =new AreaAdapter();
	                            mLvArea1 .setAdapter(adapter);
	                            
							}else{
								//跳转到注册界面
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if(dialog.isShow()){
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
		private Button mArea3sure;
		private Button mArea3SelectAll;
	private void initView() {
		mTVarea1 =(TextView)this.findViewById(R.id.mTVarea3);
		mTVarea1.setText(getResources().getString(R.string.title_job1));
		mArea3sure =(Button)this.findViewById(R.id.mArea3sure);
		mArea3sure.setOnClickListener(listener);
		mArea3SelectAll =(Button)this.findViewById(R.id.mArea3SelectAll);
		mArea3SelectAll.setOnClickListener(listener);
		mLvArea1 =(ListView)this.findViewById(R.id.mLvArea1);
		mLvArea1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			 if(area1List.get(position).getIsChecked()){
				 area1List.get(position).setIsChecked(false);
				 SharedPreferenceDB5.deleteSp(area1List.get(position).getAreaId(),ChoiceJob6Activity.this);
			 }else{
				 area1List.get(position).setIsChecked(true);
				 SharedPreferenceDB5.addSp(area1List.get(position).getAreaId(),ChoiceJob6Activity.this,area1List.get(position).getAreaName());
			 }
				adapter.notifyDataSetChanged();
			}
		});
		dialog =new com.webdesign488.post.view.ProgressDialog(ChoiceJob6Activity.this);
	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mArea3sure:
				AppManager.getAppManager().finishActivity();
				break;
			case R.id.mArea3SelectAll:
				if(mArea3SelectAll.getText().equals(getResources().getString(R.string.selectall))){
					mArea3SelectAll.setText(getResources().getString(R.string.selectallcancel));
					for(int i=0;i<area1List.size();i++){
						 if(!area1List.get(i).getIsChecked()){
							 area1List.get(i).setIsChecked(true);
							 SharedPreferenceDB5.addSp(area1List.get(i).getAreaId(),ChoiceJob6Activity.this,area1List.get(i).getAreaName());
						 }
					}
					adapter.notifyDataSetChanged();
				}else{
					mArea3SelectAll.setText(getResources().getString(R.string.selectall));
					for(int i=0;i<area1List.size();i++){
					 if(area1List.get(i).getIsChecked()){
						 area1List.get(i).setIsChecked(false);
						 SharedPreferenceDB5.deleteSp(area1List.get(i).getAreaId(),ChoiceJob6Activity.this);
					 }
				}
					adapter.notifyDataSetChanged();
				}
				
				break;

			default:
				break;
			}
		}
	};
	class Holder{
		TextView area2name;
		CheckBox cb;
		}

	private class AreaAdapter extends BaseAdapter{

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
			if(convertView==null){
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_area2, null);
				holder = new Holder();
				holder.area2name =(TextView)convertView.findViewById(R.id.area2name);
				holder.cb =(CheckBox)convertView.findViewById(R.id.area2cb2);
				convertView.setTag(holder);
	
			}else{
				holder =(Holder)convertView.getTag();
			}
   			holder.area2name.setText(area1List.get(position).getAreaName());
	        if(area1List.get(position).getIsChecked()){
	        	holder.cb.setChecked(true);
	        }else{
	        	holder.cb.setChecked(false);
	        	
	        }
   			
			return convertView;
		}
		
	}
	
}
