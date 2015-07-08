package com.webdesign488.post.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceDB6 {

	public static boolean isChecked(String str, Context context) {
		Boolean flag = false;
		String AREA5 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5", "");
		if (str.equals(AREA5)) {
			flag = true;
		}
		return flag;
	}

	public static void deleteSp(String str, Context context) {

		String AREA5 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5", "");
		String AREA51 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA51", "");
		String AREA5a = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5a", "");
		String AREA5b = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5b", "");

		SharedPreferences mySharedPreferences1 = context.getSharedPreferences(
				"AREA", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();
		editor1.putString("AREA5", "");
		editor1.putString("AREA51", "");
		editor1.putString("AREA5a", "");
		editor1.putString("AREA5b", "");

		editor1.commit();

	}

	public static void addSp(String str, Context context, String str1,
			String stra, String strb) {
		SharedPreferences mySharedPreferences1 = context.getSharedPreferences(
				"AREA", Activity.MODE_PRIVATE);
		String AREA5 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5", "");
		String AREA5a = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5a", "");
		String AREA5b = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA5b", "");
		// 存放回显的文字
		String AREA51 = context.getSharedPreferences("AREA",
				Activity.MODE_PRIVATE).getString("AREA51", "");
		SharedPreferences.Editor editor1 = mySharedPreferences1.edit();

		editor1.putString("AREA5", str);
		editor1.putString("AREA51", str1);
		editor1.putString("AREA5a", stra);
		editor1.putString("AREA5b", strb);
		editor1.commit();

	}

}
