package com.webdesign488.post.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webdesign488.post.R;


public final  class Content {
	public static final int JSON_IO_ERROR = 500;
	public static final int JSON_INFO = 1;
	public static final int JSON_IO_CONNECT = 501;
	
	public static final String HOME_URL = "http://josie.i3.com.hk/qqpost/json/"; 
	public static final String Contact = HOME_URL+"contact.php";
	public static final String IS_REGISTER = HOME_URL+"m_login.php";
	public static final String AREA1 = HOME_URL+"locationonelist.php";
	public static final String AREA2 = HOME_URL+"locationtwolist.php";
	public static final String AREA3 = HOME_URL+"locationthreelist.php";
	public static final String JOB1 = HOME_URL+"categoryonelist.php";
	public static final String JOB2 = HOME_URL+"categorytwolist.php";
	public static final String JOB3 = HOME_URL+"categorythreelist.php";
	public static final String REGISTER = HOME_URL+"m_reg.php";
	public static final String PUBINFO = HOME_URL+"m_jobpost.php";
	public static final String PUBLIST = HOME_URL+"m_joblist.php";
	public static final String MYREVLIST = HOME_URL+"m_myjobapply.php";
	public static final String MYREVDETAIL = HOME_URL+"jobdetail.php";
	public static final String UPDATEINFO = HOME_URL+"m_infoedit.php";
	public static final String APPLY = HOME_URL+"m_jobapplylist.php";
	public static final String APPLYSUCCESS = HOME_URL+"m_jobapplysuccess.php";
	//招聘信息  (求职者)
	public static final String FORJOB = HOME_URL+"joblist.php";
	public static final String FORJOBDETAIL = HOME_URL+"jobdetail.php";
	public static final String FORJOBTHIS = HOME_URL+"m_jobapplypost.php";
	
	
	
	
	
	
	
	public static String HTTP ="http://car.cyy928.com";

	
	//推送消息过来的表名
	public static final String PUSH_MSG_TABLE ="msg";
	//消息表中的电话
	public static final String MSG_TELEPHONE ="M_phone";
	//消息表中的地址
	public static final String MSG_ADDRESS ="M_address";
	//消息表中的经度
	public static final String MSG_LONGITUDE ="M_longitude";
	//消息表中的纬度
	public static final String MSG_LATITUDE ="M_latitude";
	//消息表中的状态
	public static final String MSG_STATUS ="M_status";
	
  //外网的
	public static String SOCKET ="car.cyy928.com";
	
/**
//	public static final String SOCKET ="192.168.1.148";
	
	**/
/*	 内网的
 * public static final String SOCKET ="192.168.1.148";
	public static final String HTTP ="http://192.168.1.148";
*/
 //socket	192.168.1.148
	//115.29.245.128

	//用来判断是否联网
	public static boolean IS_CONNECT_NET =true;
	//用来判断是否退出登录
	public static boolean IS_CANCEL_LOGIN_NET =false;
	//用来判断是否是下线
	public static boolean IS_OFF_LINE_NET =false;
	//用来显示倒计时的时间
	public static int LONG_TIME =10*1000;
	//用来判断Socket不要多次跑重复的
	public static boolean IS_IN_SOCKET =false;
	//用来判断IS_STOP_IN_EXECUTEORDERACTIVIITY
	public static boolean IS_STOP_IN_EXECUTEORDERACTIVIITY =true;
	//用来判断是否验证通过，
	public static boolean IS_Verify =false;

	//用来判断IS_In_MainActivity
	public static boolean IS_In_MainActivity =true;
	/**
	 * 得到自定义的progressDialog
	 * @param context
	 * @param msg 文字显示
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// 加载布局
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		tipTextView.setText(msg);// 设置加载信息

		Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
		loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
		loadingDialog.setCanceledOnTouchOutside(true); //点击加载框以外的区域
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		loadingDialog.show();
		
		return loadingDialog;
	}

	
	
}
