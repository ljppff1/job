package com.webdesign488.post.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceDB5 {
   
	public static boolean isChecked(String str,Context context){
		Boolean flag =false;
		String JOB2 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB2", "");

	      String[] item_JOB2 = JOB2.split("[|]");
	for(int i=0;i<item_JOB2.length;i++){
		if(item_JOB2[i].equals(str)){
			flag =true;
		}
	}
       
		return flag;
	}
	
	public static void deleteSp(String str,Context context){

		String JOB2 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB2", "");
		String JOB21 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB21", "");

      String[] item_JOB2 = JOB2.split("[|]");
     List<String> item_JOB21 =new ArrayList<String>();
     String[] item_JOB22 = JOB21.split("[,]");
     List<String> item_JOB23 =new ArrayList<String>();
     for(int i=0;i<item_JOB22.length;i++){
     item_JOB23.add(item_JOB22[i]);
     }

     
for(int i=0;i<item_JOB2.length;i++){
	item_JOB21.add(item_JOB2[i]);
}
for(int i=0;i<item_JOB21.size();i++){
	if(item_JOB21.get(i).equals(str)){
		item_JOB21.remove(i);
		item_JOB23.remove(i);
	}
}

StringBuilder sb = new StringBuilder(); 
StringBuilder sb1 = new StringBuilder(); 
if(item_JOB21!=null&&item_JOB21.size()>0){
   for(int j=0;j<item_JOB21.size();j++){
	   if(j<item_JOB21.size()-1){
		sb.append(item_JOB21.get(j)+"|");   
	   }else{
		   sb.append(item_JOB21.get(j));
	   }
	   if(j<item_JOB23.size()-1){
		sb1.append(item_JOB23.get(j)+",");   
	   }else{
		   sb1.append(item_JOB23.get(j));
	   }

   }
}




   String JOB22 =sb.toString();
   String JOB23 =sb1.toString();
   Util.logh("MYSP", JOB23 +"   "+JOB22); 
   SharedPreferences mySharedPreferences1=context.getSharedPreferences("AREA", Activity.MODE_PRIVATE); 
	SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
    if(TextUtils.isEmpty(JOB22)){
     editor1.putString("JOB2",""); 
     editor1.putString("JOB21","");
     }else{
         editor1.putString("JOB2",JOB22); 
         editor1.putString("JOB21",JOB23); 
     }
     editor1.commit(); 
    
	}
	public static void addSp(String str,Context context,String str1){
		SharedPreferences mySharedPreferences1=context.getSharedPreferences("AREA", Activity.MODE_PRIVATE); 
		String JOB2 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB2", "");
		//存放回显的文字
		String JOB21 =context.getSharedPreferences("AREA", Activity.MODE_PRIVATE).getString("JOB21", "");
       	SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
        
        if(TextUtils.isEmpty(JOB2)){
	           editor1.putString("JOB2",str); 
	           editor1.putString("JOB21", str1);
        }else{
           editor1.putString("JOB2",JOB2+"|"+str); 
           editor1.putString("JOB21",JOB21+","+str1); 
        }
        Util.logh("MYSP", JOB2 +"   "+JOB21); 

           editor1.commit(); 

	}

}
