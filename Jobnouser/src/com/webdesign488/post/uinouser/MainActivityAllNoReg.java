package com.webdesign488.post.uinouser;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign488.post.ChatInfoActivity;
import com.webdesign488.post.MyInfoActivity;
import com.webdesign488.post.NewInfoActivity;
import com.webdesign488.post.R;
import com.webdesign488.post.RegActivity;
import com.webdesign488.post.TabHostActivity;
import com.webdesign488.post.R.drawable;
import com.webdesign488.post.R.layout;
import com.webdesign488.post.R.string;
import com.webdesign488.post.VMyInfoActivity;
import com.webdesign488.post.a.PubInfoActivity;
import com.webdesign488.post.a.SPubInfoActivity;
import com.webdesign488.post.model.TabItem;
import com.webdesign488.post.util.AppManager;

/**
 * 主界面类
 * @author kw
 *
 */
public class MainActivityAllNoReg extends TabHostActivity{
	List<TabItem> mItems;
	private LayoutInflater mLayoutInflater;
//	private TabWidget tabWidget;
	private long exitTime = 0;
	private TabItem projects;
	private TabItem homepage;
	
	/**在初始化TabWidget前调用
	 * 和TabWidget有关的必须在这里初始化*/
	@Override
	protected void prepare() {
		
  
   		 homepage = new TabItem(
 				getResources().getString(R.string.titleMain1), 	//标题
 				R.drawable.a, //图标
 				R.drawable.example_tab_item_bg,  //背景
 				new Intent(this,SPubInfoActivity.class)); //跳转

		 projects = new TabItem(
				getResources().getString(R.string.titleMain2), 
				R.drawable.aa, 
				R.drawable.example_tab_item_bg, 
				new Intent(this,TwoTabActivityReg.class));
		 
        
		TabItem costs = new TabItem(
				getResources().getString(R.string.titleMain3), 
				R.drawable.aaa, 
				R.drawable.example_tab_item_bg, 
				new Intent(this,VMyInfoActivity.class));
		
		TabItem documents = new TabItem(
				getResources().getString(R.string.titleMain4), 
				R.drawable.aaaa, 
				R.drawable.example_tab_item_bg, 
				new Intent(this,ChatInfoActivity.class));
		
		mItems = new ArrayList<TabItem>();
		mItems.add(homepage);
		mItems.add(projects);
		mItems.add(costs);
		mItems.add(documents);
//		mItems.add(more);
		
		
		//设置分割线
		TabWidget tabWidget = getTabWidget();
		tabWidget.setDividerDrawable(R.drawable.tab_divider);
		
		mLayoutInflater = getLayoutInflater();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置默认选中
		setCurrentTab(0);
		/*//设置窗口的宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int count = mItems.size();
		if(count < 4){
			for(int i=0; i<count; i++){
				//设置每个选项卡的宽度
				tabWidget.getChildTabViewAt(i).setMinimumWidth(screenWidth / 4);
			}
		}*/
	}
	
	
	/**tab的title，icon，边距设定等等*/
	@Override
	protected void setTabItemTextView(TextView textView, int position) {
		textView.setPadding(3, 3, 3, 3);
		textView.setText(mItems.get(position).getTitle());
		textView.setBackgroundResource(mItems.get(position).getBg());
		textView.setCompoundDrawablesWithIntrinsicBounds(0,mItems.get(position).getIcon(), 0, 0);
	}

	/**tab唯一的id*/
	@Override
	protected String getTabItemId(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position).getTitle();//使用title作为id
	}

	/**点击tab时触发的事件*/
	@Override
	protected Intent getTabItemIntent(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position).getIntent();
	}

	@Override
	protected int getTabItemCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}
	
	/**自定义头部文件*/
	@Override
	protected View getTop() {
		// TODO Auto-generated method stub
		return mLayoutInflater.inflate(R.layout.example_top, null);
	}
	

	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			if((System.currentTimeMillis() - exitTime) > 2000){
				Toast.makeText(getApplicationContext(), "再按一次退出程序", 1).show();
				exitTime = System.currentTimeMillis();
				}else{
					AppManager.getAppManager().AppExit(getApplicationContext());
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			return true;
			}
		return super.dispatchKeyEvent(event);
	}
	
	
}
