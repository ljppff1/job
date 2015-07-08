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
 * ��������
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
	
	/**�ڳ�ʼ��TabWidgetǰ����
	 * ��TabWidget�йصı����������ʼ��*/
	@Override
	protected void prepare() {
		
  
   		 homepage = new TabItem(
 				getResources().getString(R.string.titleMain1), 	//����
 				R.drawable.a, //ͼ��
 				R.drawable.example_tab_item_bg,  //����
 				new Intent(this,SPubInfoActivity.class)); //��ת

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
		
		
		//���÷ָ���
		TabWidget tabWidget = getTabWidget();
		tabWidget.setDividerDrawable(R.drawable.tab_divider);
		
		mLayoutInflater = getLayoutInflater();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//����Ĭ��ѡ��
		setCurrentTab(0);
		/*//���ô��ڵĿ��
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int count = mItems.size();
		if(count < 4){
			for(int i=0; i<count; i++){
				//����ÿ��ѡ��Ŀ��
				tabWidget.getChildTabViewAt(i).setMinimumWidth(screenWidth / 4);
			}
		}*/
	}
	
	
	/**tab��title��icon���߾��趨�ȵ�*/
	@Override
	protected void setTabItemTextView(TextView textView, int position) {
		textView.setPadding(3, 3, 3, 3);
		textView.setText(mItems.get(position).getTitle());
		textView.setBackgroundResource(mItems.get(position).getBg());
		textView.setCompoundDrawablesWithIntrinsicBounds(0,mItems.get(position).getIcon(), 0, 0);
	}

	/**tabΨһ��id*/
	@Override
	protected String getTabItemId(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position).getTitle();//ʹ��title��Ϊid
	}

	/**���tabʱ�������¼�*/
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
	
	/**�Զ���ͷ���ļ�*/
	@Override
	protected View getTop() {
		// TODO Auto-generated method stub
		return mLayoutInflater.inflate(R.layout.example_top, null);
	}
	

	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			if((System.currentTimeMillis() - exitTime) > 2000){
				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����", 1).show();
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
