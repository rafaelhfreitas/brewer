package com.bigworks.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.bigworks.brewer.dto.PictureDTO;

public class PictureStorageRunnable implements Runnable {
	
	
	private MultipartFile[] files;
	private DeferredResult<PictureDTO> result;
	private PictureStorage pictureStorage;

	
	public PictureStorageRunnable( MultipartFile[] files, DeferredResult<PictureDTO> result,  PictureStorage pictureStorage  ) {
		this.files = files;
		this.result = result;
		this.pictureStorage = pictureStorage;
	}
	
	@Override
	public void run() {		 	
		String namePicture = this.pictureStorage.saveTemporarily(files);
		String contentType = files[0].getContentType();
		result.setResult(new PictureDTO(namePicture, contentType));
	}

}
