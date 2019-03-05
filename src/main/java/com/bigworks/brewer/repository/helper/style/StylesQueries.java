package com.bigworks.brewer.repository.helper.style;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bigworks.brewer.model.Style;
import com.bigworks.brewer.repository.filter.StyleFilter;

public interface StylesQueries {
	
	
	public Page<Style> filter(StyleFilter filter, Pageable pageable);

}
