package com.webdesign488.post.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceDB4 {

	public static boolean isChecked(String str, Context context) {
		Boolean flag = false;
		String JOB3 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("JOB3", "");

		String[] item_JOB3 = JOB3.split("[|]");
		for (int i = 0; i < item_JOB3.length; i++) {
			if (item_JOB3[i].equals(str)) {
				flag = true;
			}
		}

		return flag;
	}

	public static void deleteSp(String str, Context context) {

		String JOB3 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("JOB3", "");
		String JOB31 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("JOB31", "");

		String[] item_JOB3 = JOB3.split("[|]");
		List<String> item_JOB31 = new ArrayList<String>();
		String[] item_JOB32 = JOB31.split("[,]");
		List<String> item_JOB33 = new ArrayList<String>();
		for (int i = 0; i < item_JOB32.length; i++) {
			item_JOB33.add(item_JOB32[i]);
		}

		for (int i = 0; i < item_JOB3.length; i++) {
			item_JOB31.add(item_JOB3[i]);
		}
		for (int i = 0; i < item_JOB31.size(); i++) {
			if (item_JOB31.get(i).equals(str)) {
				item_JOB31.remove(i);
				item_JOB33.remove(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		if (item_JOB31 != null && item_JOB31.size() > 0) {
			for (int j = 0; j < item_JOB31.size(); j++) {
				if (j < item_JOB31.size() - 1) {
					sb.append(item_JOB31.get(j) + "|");
				} else {
					sb.append(item_JOB31.get(j));
				}
				if (j < item_JOB33.size() - 1) {
					sb1.append(item_JOB33.get(j) + ",");
				} else {
					sb1.append(item_JOB33.get(j));
				}

			}
		}

		String JOB32 = sb.toString();
		String JOB33 = sb1.toString();
		Util.logh("MYSP", JOB33 + "   " + JOB32);
		SharedPreferences mySharedPreferences1 = context.getSharedPreferences(
				"AREA", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();
		if (TextUtils.isEmpty(JOB32)) {
			editor1.putString("JOB3", "");
			editor1.putString("JOB31", "");
		} else {
			editor1.putString("JOB3", JOB32);
			editor1.putString("JOB31", JOB33);
		}
		editor1.commit();

	}

	public static void addSp(String str, Context context, String str1) {
		SharedPreferences mySharedPreferences1 = context.getSharedPreferences(
				"AREA", Activity.MODE_PRIVATE);
		String JOB3 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("JOB3", "");
		// 存放回显的文字
		String JOB31 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("JOB31", "");
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();

		if (TextUtils.isEmpty(JOB3)) {
			editor1.putString("JOB3", str);
			editor1.putString("JOB31", str1);
		} else {
			editor1.putString("JOB3", JOB3 + "|" + str);
			editor1.putString("JOB31", JOB31 + "," + str1);
		}
		Util.logh("MYSP", JOB3 + "   " + JOB31);

		editor1.commit();

	}

}
