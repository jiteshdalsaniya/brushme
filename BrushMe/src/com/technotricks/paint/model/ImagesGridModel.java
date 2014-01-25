package com.technotricks.paint.model;

import java.io.Serializable;

public class ImagesGridModel implements Serializable{
	
	
	private String id="";
	private String imageName="";

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	

}
