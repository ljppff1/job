package com.webdesign488.post.model;

import java.io.Serializable;

public class Job implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	private String Id;
	private String name;
	private String path;
}
