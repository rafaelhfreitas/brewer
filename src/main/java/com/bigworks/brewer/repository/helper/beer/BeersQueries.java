package com.bigworks.brewer.repository.helper.beer;

import java.util.List;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.filter.BeerFilter;

public interface BeersQueries  {
	
	
	public List<Beer> filter(BeerFilter filter);

}
