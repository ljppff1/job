package com.webdesign488.post.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

	public MyScrollView(Context context) {
		super(context);

	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * @Override public boolean onInterceptTouchEvent(MotionEvent event)
	 * //�������������� true �Ļ� ������ָ�ƶ�������һ�����µ���ָ���ƶ����ܱ�������ȥ�� {
	 * super.onInterceptTouchEvent(event); return false; }
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event)
	// ���������� true ������Activity �� onTouchEvent() ���ᱻϵͳ�ص�
	{
		super.onTouchEvent(event);
		return true;
	}
}
