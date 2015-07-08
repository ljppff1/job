package com.webdesign488.post;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.webdesign488.post.util.AppManager;

/**
 * ѡ���
 * @author kw
 *
 */
public abstract class TabHostActivity extends TabActivity {
	
	private TabHost mTabHost;
	private TabWidget mTabWidget; //�ײ��˵�
	private LayoutInflater mLayoutInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	//	setTheme(R.style.Theme_Tabhost);// ������ʽ
		
		// ����ȫ��ģʽ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// ȥ��������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.api_tap_host);
		
		
		mLayoutInflater = getLayoutInflater();
		
		mTabHost = getTabHost();
		mTabWidget = getTabWidget();
		prepare();
		initTabSpec();
		AppManager.getAppManager().addActivity(this); // ���Activity����ջ
		
	}
	
	
	

	//��ʼ��tab
	private void initTabSpec() {
		
		int count = getTabItemCount();
		
		for(int i=0; i<count;i++){
			View tabItem = mLayoutInflater.inflate(R.layout.api_tab_item, null);
			TextView tvTabItem = (TextView) tabItem.findViewById(R.id.tab_item_tv);
			setTabItemTextView(tvTabItem, i);
			//set id
			String tabItemId = getTabItemId(i);
			//set tabSpec
			TabSpec tabSpec = mTabHost.newTabSpec(tabItemId);
			tabSpec.setIndicator(tabItem);
			tabSpec.setContent(getTabItemIntent(i));
			
			mTabHost.addTab(tabSpec);
		}
		
	}


	//�Զ���ͷ������
	protected View getTop() {
		// do nothing or you override it
		return null;
	}

	//�ڳ�ʼ������֮ǰ����
	protected void prepare() {
		// do nothing or you override it
		
	}
	
	protected int getTabCount(){
		return mTabHost.getTabWidget().getTabCount();
	}
	
	
	//����TabItem��ͼ��ͱ����
	abstract protected void setTabItemTextView(TextView textView, int position);
	
	abstract protected String getTabItemId(int position);
	
	abstract protected Intent getTabItemIntent(int position);
	
	abstract protected int getTabItemCount();
	
	protected void setCurrentTab(int index){
		mTabHost.setCurrentTab(index);
	}
	
	protected void focusCurrentTab(int index){
		mTabWidget.focusCurrentTab(index);
	}
}
