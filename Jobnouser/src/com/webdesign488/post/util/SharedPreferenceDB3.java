package com.webdesign488.post.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceDB3 {
   
	public static boolean isChecked(String str,Context context){
		Boolean flag =false;
		String JOB1 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB1", "");

	      String[] item_JOB1 = JOB1.split("[|]");
	for(int i=0;i<item_JOB1.length;i++){
		if(item_JOB1[i].equals(str)){
			flag =true;
		}
	}
       
		return flag;
	}
	
	public static void deleteSp(String str,Context context){

		String JOB1 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB1", "");
		String JOB11 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB11", "");

      String[] item_JOB1 = JOB1.split("[|]");
     List<String> item_JOB11 =new ArrayList<String>();
     String[] item_JOB12 = JOB11.split("[,]");
     List<String> item_JOB13 =new ArrayList<String>();
     for(int i=0;i<item_JOB12.length;i++){
     item_JOB13.add(item_JOB12[i]);
     }

     
for(int i=0;i<item_JOB1.length;i++){
	item_JOB11.add(item_JOB1[i]);
}
for(int i=0;i<item_JOB11.size();i++){
	if(item_JOB11.get(i).equals(str)){
		item_JOB11.remove(i);
		item_JOB13.remove(i);
	}
}

StringBuilder sb = new StringBuilder(); 
StringBuilder sb1 = new StringBuilder(); 
if(item_JOB11!=null&&item_JOB11.size()>0){
   for(int j=0;j<item_JOB11.size();j++){
	   if(j<item_JOB11.size()-1){
		sb.append(item_JOB11.get(j)+"|");   
	   }else{
		   sb.append(item_JOB11.get(j));
	   }
	   if(j<item_JOB13.size()-1){
		sb1.append(item_JOB13.get(j)+",");   
	   }else{
		   sb1.append(item_JOB13.get(j));
	   }

   }
}




   String JOB12 =sb.toString();
   String JOB13 =sb1.toString();
   Util.logh("MYSP", JOB13 +"   "+JOB12); 
   SharedPreferences mySharedPreferences1=context.getSharedPreferences("AREA", Activity.MODE_PRIVATE); 
	SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
    if(TextUtils.isEmpty(JOB12)){
     editor1.putString("JOB1",""); 
     editor1.putString("JOB11","");
     }else{
         editor1.putString("JOB1",JOB12); 
         editor1.putString("JOB11",JOB13); 
     }
     editor1.commit(); 
    
	}
	public static void addSp(String str,Context context,String str1){
		SharedPreferences mySharedPreferences1=context.getSharedPreferences("AREA", Activity.MODE_PRIVATE); 
		String JOB1 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB1", "");
		//存放回显的文字
		String JOB11 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB11", "");
       	SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
        
        if(TextUtils.isEmpty(JOB1)){
	           editor1.putString("JOB1",str); 
	           editor1.putString("JOB11", str1);
        }else{
           editor1.putString("JOB1",JOB1+"|"+str); 
           editor1.putString("JOB11",JOB11+","+str1); 
        }
        Util.logh("MYSP", JOB1 +"   "+JOB11); 

           editor1.commit(); 

	}

}
