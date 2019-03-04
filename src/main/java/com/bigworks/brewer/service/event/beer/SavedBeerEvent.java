package com.bigworks.brewer.service.event.beer;

import org.springframework.util.StringUtils;

import com.bigworks.brewer.model.Beer;

public class SavedBeerEvent {
	
	private Beer beer;
	
	public SavedBeerEvent(Beer beer) {
		this.beer  =  beer;
	}

	public Beer getBeer() {
		return beer;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}
	
	
	public boolean hasPicture() {
		return !StringUtils.isEmpty(beer.getPicture());
	}
		

}
