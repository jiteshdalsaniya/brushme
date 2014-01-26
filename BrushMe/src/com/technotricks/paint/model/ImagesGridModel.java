package com.technotricks.paint.model;

import java.io.Serializable;

public class ImagesGridModel implements Serializable{
	
	
	private String id="";
	private String imageName_or_Path="";// NAme of assert and full path of sd card files

	public String getImageName_OR_Path() {
		return imageName_or_Path;
	}

	public void setImageName_OR_Path(String imageName_or_Path) {
		this.imageName_or_Path = imageName_or_Path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	

}
