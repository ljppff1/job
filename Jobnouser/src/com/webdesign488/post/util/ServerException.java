package com.webdesign488.post.util;

import android.util.Log;

/**
 * 
* Title: ServerException
* Description:�Զ���������ڲ��쳣
* Company: SD 
* @author luolei
* @date 2014��6��6��
 */
public class ServerException extends Exception{

	private static final long serialVersionUID = 1L;

	public ServerException(String msg){
		super(msg);
		Log.e("�쳣����", this.getMessage());
	}
	
}
