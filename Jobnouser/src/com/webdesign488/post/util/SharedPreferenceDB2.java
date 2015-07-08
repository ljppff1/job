package com.webdesign488.post.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceDB2 {

	public static boolean isChecked(String str, Context context) {
		Boolean flag = false;
		String AREA1 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA1", "");

		String[] item_AREA1 = AREA1.split("[|]");
		for (int i = 0; i < item_AREA1.length; i++) {
			if (item_AREA1[i].equals(str)) {
				flag = true;
			}
		}

		return flag;
	}

	public static void deleteSp(String str, Context context) {

		String AREA1 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA1", "");
		String AREA11 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA11", "");

		String[] item_AREA1 = AREA1.split("[|]");
		List<String> item_AREA11 = new ArrayList<String>();
		String[] item_AREA12 = AREA11.split("[,]");
		List<String> item_AREA13 = new ArrayList<String>();
		for (int i = 0; i < item_AREA12.length; i++) {
			item_AREA13.add(item_AREA12[i]);
		}

		for (int i = 0; i < item_AREA1.length; i++) {
			item_AREA11.add(item_AREA1[i]);
		}
		for (int i = 0; i < item_AREA11.size(); i++) {
			if (item_AREA11.get(i).equals(str)) {
				item_AREA11.remove(i);
				item_AREA13.remove(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		if (item_AREA11 != null && item_AREA11.size() > 0) {
			for (int j = 0; j < item_AREA11.size(); j++) {
				if (j < item_AREA11.size() - 1) {
					sb.append(item_AREA11.get(j) + "|");
				} else {
					sb.append(item_AREA11.get(j));
				}
				if (j < item_AREA13.size() - 1) {
					sb1.append(item_AREA13.get(j) + ",");
				} else {
					sb1.append(item_AREA13.get(j));
				}

			}
		}

		String AREA12 = sb.toString();
		String AREA13 = sb1.toString();
		Util.logh("MYSP", AREA13 + "   " + AREA12);
		SharedPreferences mySharedPreferences1 = context.getSharedPreferences(
				"AREA", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();
		if (TextUtils.isEmpty(AREA12)) {
			editor1.putString("AREA1", "");
			editor1.putString("AREA11", "");
		} else {
			editor1.putString("AREA1", AREA12);
			editor1.putString("AREA11", AREA13);
		}
		editor1.commit();

	}

	public static void addSp(String str, Context context, String str1) {
		SharedPreferences mySharedPreferences1 = context.getSharedPreferences(
				"AREA", Activity.MODE_PRIVATE);
		String AREA1 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA1", "");
		// 存放回显的文字
		String AREA11 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA11", "");
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();

		if (TextUtils.isEmpty(AREA1)) {
			editor1.putString("AREA1", str);
			editor1.putString("AREA11", str1);
		} else {
			editor1.putString("AREA1", AREA1 + "|" + str);
			editor1.putString("AREA11", AREA11 + "," + str1);
		}
		Util.logh("MYSP", AREA1 + "   " + AREA11);

		editor1.commit();

	}

}
