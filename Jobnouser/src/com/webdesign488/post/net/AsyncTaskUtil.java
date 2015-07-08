package com.webdesign488.post.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.webdesign488.post.util.Util;
import com.webdesign488.post.view.ProgressDialog;

public class AsyncTaskUtil {
	public static boolean isNetworkConnected(Context context) { 
		if (context != null) { 
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
		.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
		if (mNetworkInfo != null) { 
		return mNetworkInfo.isAvailable(); 
		} 
		} 
		return false; 
		}

    /**
     * 
     * @param list   封装的param参数
     * @param handler 
     * @param dialog
     * @param url      
     * @param context
     * @param what   handler传的what
     */
	public static void getJson(List<String> list,List<String> list1,final Handler handler,final ProgressDialog dialog,final String url,Context context,final Integer what){
    	   if(isNetworkConnected(context)){
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for(int i=0;i<list.size();i++){
				params.add(new BasicNameValuePair(list.get(i), list1.get(i)));
			}
			
			new AsyncTask<List<NameValuePair>, Integer, String>(){
               protected void onPreExecute() {
            	   dialog.show();
               };
				@Override
				protected String doInBackground(List<NameValuePair>... params) {
					SDHttpClient sdClient = new com.webdesign488.post.net.SDHttpClient();
					String json = null;
					try {
						json = sdClient.post(url, params[0]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Util.logh("JSON", json);

			        if(dialog.isShow()){
			     	   dialog.cancel();
			        }
					Message msg =new Message();
					msg.what =what;
					msg.obj =json;
					handler.sendMessage(msg);
					return null;
				}
				
			}.execute(params);

    	   }else{
				Message msg =new Message();
				msg.what =1;
				handler.sendMessage(msg);

    	   }
    	   
    	 
       }
}
