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
	//��Ƹ��Ϣ  (��ְ��)
	public static final String FORJOB = HOME_URL+"joblist.php";
	public static final String FORJOBDETAIL = HOME_URL+"jobdetail.php";
	public static final String FORJOBTHIS = HOME_URL+"m_jobapplypost.php";
	
	
	
	
	
	
	
	public static String HTTP ="http://car.cyy928.com";

	
	//������Ϣ�����ı���
	public static final String PUSH_MSG_TABLE ="msg";
	//��Ϣ���еĵ绰
	public static final String MSG_TELEPHONE ="M_phone";
	//��Ϣ���еĵ�ַ
	public static final String MSG_ADDRESS ="M_address";
	//��Ϣ���еľ���
	public static final String MSG_LONGITUDE ="M_longitude";
	//��Ϣ���е�γ��
	public static final String MSG_LATITUDE ="M_latitude";
	//��Ϣ���е�״̬
	public static final String MSG_STATUS ="M_status";
	
  //������
	public static String SOCKET ="car.cyy928.com";
	
/**
//	public static final String SOCKET ="192.168.1.148";
	
	**/
/*	 ������
 * public static final String SOCKET ="192.168.1.148";
	public static final String HTTP ="http://192.168.1.148";
*/
 //socket	192.168.1.148
	//115.29.245.128

	//�����ж��Ƿ�����
	public static boolean IS_CONNECT_NET =true;
	//�����ж��Ƿ��˳���¼
	public static boolean IS_CANCEL_LOGIN_NET =false;
	//�����ж��Ƿ�������
	public static boolean IS_OFF_LINE_NET =false;
	//������ʾ����ʱ��ʱ��
	public static int LONG_TIME =10*1000;
	//�����ж�Socket��Ҫ������ظ���
	public static boolean IS_IN_SOCKET =false;
	//�����ж�IS_STOP_IN_EXECUTEORDERACTIVIITY
	public static boolean IS_STOP_IN_EXECUTEORDERACTIVIITY =true;
	//�����ж��Ƿ���֤ͨ����
	public static boolean IS_Verify =false;

	//�����ж�IS_In_MainActivity
	public static boolean IS_In_MainActivity =true;
	/**
	 * �õ��Զ����progressDialog
	 * @param context
	 * @param msg ������ʾ
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// �õ�����view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// ���ز���
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// ��ʾ����
		tipTextView.setText(msg);// ���ü�����Ϣ

		Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// �����Զ�����ʽdialog
		loadingDialog.setCancelable(true); // �Ƿ���԰������ؼ�����ʧ
		loadingDialog.setCanceledOnTouchOutside(true); //������ؿ����������
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// ���ò���
		loadingDialog.show();
		
		return loadingDialog;
	}

	
	
}
