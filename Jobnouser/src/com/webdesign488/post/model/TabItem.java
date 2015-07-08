package com.webdesign488.post.model;

import android.content.Intent;

/**
 * TabItem��
 * 
 * @author kw
 * 
 */
public class TabItem {

	private String title; // ����
	private int icon; // ͼ��
	private int bg; // ����
	private Intent intent; // ��ͼ

	public TabItem(String title, int icon, int bg, Intent intent) {
		super();
		this.title = title;
		this.icon = icon;
		this.bg = bg;
		this.intent = intent;
	}

	public TabItem() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getBg() {
		return bg;
	}

	public void setBg(int bg) {
		this.bg = bg;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

}
