package com.webdesign488.post.model;

import java.io.Serializable;

public class ForInfo implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAmobile() {
		return amobile;
	}
	public void setAmobile(String amobile) {
		this.amobile = amobile;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
private String aid;
   private String aname;
   private String amobile;
   private String adate;
   public String getJstatus() {
	return jstatus;
}
public void setJstatus(String jstatus) {
	this.jstatus = jstatus;
}
public String getUserintro() {
	return userintro;
}
public void setUserintro(String userintro) {
	this.userintro = userintro;
}
public String getAstatus() {
	return astatus;
}
public void setAstatus(String astatus) {
	this.astatus = astatus;
}
private String jstatus;
   private String userintro;
   private String astatus;
   
   
   public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
public String getWechat() {
	return wechat;
}
public void setWechat(String wechat) {
	this.wechat = wechat;
}
public String getQqno() {
	return qqno;
}
public void setQqno(String qqno) {
	this.qqno = qqno;
}
public String getQqzone() {
	return qqzone;
}
public void setQqzone(String qqzone) {
	this.qqzone = qqzone;
}
public String getWhatapp() {
	return whatapp;
}
public void setWhatapp(String whatapp) {
	this.whatapp = whatapp;
}
private String    website;  
   private String    wechat;  
   private String    qqno;  
   private String    qqzone;  
   private String    whatapp;  

   
   
}
