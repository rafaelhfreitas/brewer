package com.bigworks.brewer.dto;

public class PictureDTO {
	
	
	private String name;
	private String contentType;
	
	public PictureDTO(String name, String contentType) {
		this.name = name;
		this.contentType = contentType;
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	

}
