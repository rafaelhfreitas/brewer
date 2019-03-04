package com.bigworks.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.Beers;
import com.bigworks.brewer.service.event.beer.SavedBeerEvent;

@Service
public class RegisterBeerService {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Transactional
	public void save(Beer beer) {
		beers.save(beer);
		
		publisher.publishEvent(new SavedBeerEvent(beer));
	}

}
