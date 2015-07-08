package com.webdesign488.post.model;

import java.io.Serializable;

public class AInfo implements Serializable {

	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}
	public String getUserintro() {
		return userintro;
	}
	public void setUserintro(String userintro) {
		this.userintro = userintro;
	}
	private String content;
	private String username;
	private String usermobile;
	private String userintro;
	
	
}
