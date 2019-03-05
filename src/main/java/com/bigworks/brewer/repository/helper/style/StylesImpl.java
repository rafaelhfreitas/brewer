package com.bigworks.brewer.repository.helper.style;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bigworks.brewer.model.Style;
import com.bigworks.brewer.repository.filter.StyleFilter;
import com.bigworks.brewer.repository.pagination.PaginationUtil;

public class StylesImpl  implements StylesQueries {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	@Override 
	public Page<Style> filter(StyleFilter filter, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Style.class);		
				
		paginationUtil.prepare(criteria, pageable);
						
		addFilter(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	private Long total(StyleFilter filter) {
		Criteria criteria = manager.unwrap((Session.class)).createCriteria(Style.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}	

	private void addFilter(StyleFilter filter, Criteria criteria) {
		if (filter != null) {
			if(!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));				
			}
					
		}
	}
	

}
