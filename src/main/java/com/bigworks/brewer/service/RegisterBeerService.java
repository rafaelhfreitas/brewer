package com.bigworks.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.Beers;

@Service
public class RegisterBeerService {
	
	@Autowired
	private Beers beers;
	
	
	@Transactional
	public void save(Beer beer) {
		beers.save(beer);
	}

}
