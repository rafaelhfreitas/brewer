package com.bigworks.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bigworks.brewer.service.RegisterBeerService;
import com.bigworks.brewer.storage.PictureStorage;
import com.bigworks.brewer.storage.local.PictureStorageLocal;

@Configuration
@ComponentScan(basePackageClasses= RegisterBeerService.class)
public class ServiceConfig {
		
	
	@Bean
	public PictureStorage pictureStorageLocal() {
		return new PictureStorageLocal();
	}

}
