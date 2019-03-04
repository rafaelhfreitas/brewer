package com.bigworks.brewer.repository.helper.beer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.filter.BeerFilter;

public interface BeersQueries  {
	
	
	public Page<Beer> filter(BeerFilter filter, Pageable pageable);

}
