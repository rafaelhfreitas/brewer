package com.bigworks.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PictureStorage {
	
	public String saveTemporarily(MultipartFile[] files);

	public byte[] temporaryPictureRecover(String name);

	public void save(String picture);

	public byte[] pictureRecover(String picture); 

}
