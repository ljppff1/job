package com.webdesign488.post.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceDB8 {
   
	public static boolean isChecked(String str,Context context){
		Boolean flag =false;
		String AREA2 =context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString("AREA2", "");

	      String[] item_AREA2 = AREA2.split("[|]");
	for(int i=0;i<item_AREA2.length;i++){
		if(item_AREA2[i].equals(str)){
			flag =true;
		}
	}
       
		return flag;
	}
	
	public static void deleteSp(String str,Context context){

		String AREA2 =context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString("AREA2", "");
		String AREA21 =context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString("AREA21", "");

      String[] item_AREA2 = AREA2.split("[|]");
     List<String> item_AREA21 =new ArrayList<String>();
     String[] item_AREA22 = AREA21.split("[,]");
     List<String> item_AREA23 =new ArrayList<String>();
     for(int i=0;i<item_AREA22.length;i++){
     item_AREA23.add(item_AREA22[i]);
     }

     
for(int i=0;i<item_AREA2.length;i++){
	item_AREA21.add(item_AREA2[i]);
}
for(int i=0;i<item_AREA21.size();i++){
	if(item_AREA21.get(i).equals(str)){
		item_AREA21.remove(i);
		item_AREA23.remove(i);
	}
}

StringBuilder sb = new StringBuilder(); 
StringBuilder sb1 = new StringBuilder(); 
if(item_AREA21!=null&&item_AREA21.size()>0){
   for(int j=0;j<item_AREA21.size();j++){
	   if(j<item_AREA21.size()-1){
		sb.append(item_AREA21.get(j)+"|");   
	   }else{
		   sb.append(item_AREA21.get(j));
	   }
	   if(j<item_AREA23.size()-1){
		sb1.append(item_AREA23.get(j)+",");   
	   }else{
		   sb1.append(item_AREA23.get(j));
	   }

   }
}




   String AREA22 =sb.toString();
   String AREA23 =sb1.toString();
   Util.logh("MYSP", AREA23 +"   "+AREA22); 
   SharedPreferences mySharedPreferences1=context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE); 
	SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
    if(TextUtils.isEmpty(AREA22)){
     editor1.putString("AREA2",""); 
     editor1.putString("AREA21","");
     }else{
         editor1.putString("AREA2",AREA22); 
         editor1.putString("AREA21",AREA23); 
     }
     editor1.commit(); 
    
	}
	public static void addSp(String str,Context context,String str1){
		SharedPreferences mySharedPreferences1=context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE); 
		String AREA2 =context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString("AREA2", "");
		//存放回显的文字
		String AREA21 =context.getSharedPreferences("AREAAPPLY", Activity.MODE_PRIVATE).getString("AREA21", "");
       	SharedPreferences.Editor editor1 = mySharedPreferences1.edit(); 
        
        if(TextUtils.isEmpty(AREA2)){
	           editor1.putString("AREA2",str); 
	           editor1.putString("AREA21", str1);
        }else{
           editor1.putString("AREA2",AREA2+"|"+str); 
           editor1.putString("AREA21",AREA21+","+str1); 
        }
        Util.logh("MYSP", AREA2 +"   "+AREA21); 

           editor1.commit(); 

	}

}
