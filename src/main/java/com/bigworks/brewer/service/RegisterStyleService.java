package com.bigworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigworks.brewer.model.Style;
import com.bigworks.brewer.repository.Styles;
import com.bigworks.brewer.service.exception.NameStyleAlreadyExistsException;

@Service
public class RegisterStyleService {
	
	@Autowired
	private Styles styles;
	
	
	@Transactional
	public void save(Style style) {
		Optional<Style> styleOptional = styles.findByNameIgnoreCase(style.getName());
		if (styleOptional.isPresent()) {
			throw new NameStyleAlreadyExistsException("Nome do estilo já cadastrado");
		}
		
		styles.save(style);
	}

}
