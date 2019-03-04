package com.bigworks.brewer.storage.local;


import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.bigworks.brewer.storage.PictureStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class PictureStorageLocal implements PictureStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(PictureStorageLocal.class);
	
	private Path local;
	private Path temporaryLocal; 
	
	public PictureStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".brewerPictures"));		
	}	
	
	public PictureStorageLocal(Path path) {
        this.local = path;		
		createFolders();
	}


	@Override
	public String saveTemporarily(MultipartFile[] files) {		
		String newName = null;
		if (files != null && files.length > 0) {
			MultipartFile file = files[0];
			newName = renameFile(file.getOriginalFilename());
			try {
				file.transferTo(new File(this.temporaryLocal.toAbsolutePath().toString() + getDefault().getSeparator() + newName));
			} catch (Exception e) {
				throw new RuntimeException("Erro ao salvar a foto na pasta temporaria", e);
			}			
		}				
		return newName;
		
	}
	
	
	@Override
	public byte[] pictureRecover(String name) {
		try {
			return Files.readAllBytes(this.local.resolve(name));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto ",e);
		}
	}
	
	
	@Override
	public byte[] temporaryPictureRecover(String name) {
		try {
			return Files.readAllBytes(this.temporaryLocal.resolve(name));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporaria",e);
		}
	}	

	
	@Override
	public void save(String picture) {
		try {
			Files.move(this.temporaryLocal.resolve(picture), this.local.resolve(picture));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao mover a foto para o destino final");
		}
		
		
		try {
			Thumbnails.of(this.local.resolve(picture).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando o thumbnail");
		}
		
		
	}
	
	
	private void createFolders() {
		try {
			Files.createDirectories(this.local);
			this.temporaryLocal = getDefault().getPath(this.local.toString(),"temp");
			Files.createDirectories(this.temporaryLocal);
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporaria: "+ this.temporaryLocal.toAbsolutePath());
				 
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar imagens.");
		}
		
	}
	
	
	private String renameFile(String originalFilename) {
		String newName = UUID.randomUUID().toString() + "_" + originalFilename;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Novo original: %s, novo nome %s", originalFilename, newName));
		}
			
		return newName;
	}





}
