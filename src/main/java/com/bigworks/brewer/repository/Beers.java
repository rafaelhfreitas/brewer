package com.bigworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.helper.beer.BeersQueries;

@Repository
public interface Beers extends JpaRepository<Beer, Long>, BeersQueries{
	
	
	public Optional<Beer> findBySkuIgnoreCase(String sku);

}
