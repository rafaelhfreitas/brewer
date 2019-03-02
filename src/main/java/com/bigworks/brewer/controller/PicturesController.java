package com.bigworks.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.bigworks.brewer.dto.PictureDTO;
import com.bigworks.brewer.storage.PictureStorage;
import com.bigworks.brewer.storage.PictureStorageRunnable;

@RestController
@RequestMapping("/pictures")
public class PicturesController {
	
	
	@Autowired
	private PictureStorage pictureStorage;	
	
	@PostMapping
	public DeferredResult<PictureDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<PictureDTO> result = new DeferredResult<>();
		
		Thread thread = new Thread(new PictureStorageRunnable(files, result, pictureStorage));
		thread.start(); 
	
		return result;
	}
	
	
	@GetMapping("/temp/{name:.*}")
	public byte[] temporaryPictureRecover(@PathVariable String name) {
		return pictureStorage.temporaryPictureRecover(name);
	}

}
