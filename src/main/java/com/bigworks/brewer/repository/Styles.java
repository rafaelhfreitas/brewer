package com.bigworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigworks.brewer.model.Style;


@Repository
public interface Styles extends JpaRepository<Style, Long>{
	
	
	public Optional<Style> findByNameIgnoreCase(String style);
	

}
