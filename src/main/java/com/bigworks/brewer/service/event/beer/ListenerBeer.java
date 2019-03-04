package com.bigworks.brewer.service.event.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bigworks.brewer.storage.PictureStorage;

@Component
public class ListenerBeer {
	
	
	@Autowired
	private PictureStorage pictureStorage;
	
	
	@EventListener(condition = "#event.hasPicture()")
	public void savedBeer(SavedBeerEvent event) {
		System.out.println("Nova cerveja salva: " + event.getBeer().getName());
		pictureStorage.save(event.getBeer().getPicture());
	}

}
