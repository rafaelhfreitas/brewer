package com.bigworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigworks.brewer.model.Style;
import com.bigworks.brewer.repository.helper.style.StylesQueries;


@Repository
public interface Styles extends JpaRepository<Style, Long> , StylesQueries{
	
	
	public Optional<Style> findByNameIgnoreCase(String style);
	

}
