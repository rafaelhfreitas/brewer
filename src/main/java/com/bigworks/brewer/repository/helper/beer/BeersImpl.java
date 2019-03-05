package com.bigworks.brewer.repository.helper.beer;

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

import com.bigworks.brewer.model.Beer;
import com.bigworks.brewer.repository.filter.BeerFilter;
import com.bigworks.brewer.repository.pagination.PaginationUtil;

public class BeersImpl implements BeersQueries {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Autowired
	private PaginationUtil paginationUtil;

	
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	@Override 
	public Page<Beer> filter(BeerFilter filter, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
				
		paginationUtil.prepare(criteria, pageable);
				
		addFilter(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	private Long total(BeerFilter filter) {
		Criteria criteria = manager.unwrap((Session.class)).createCriteria(Beer.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}	

	private void addFilter(BeerFilter filter, Criteria criteria) {
		if (filter != null) {
			if(!StringUtils.isEmpty(filter.getSku())) {
				criteria.add(Restrictions.ilike("sku", filter.getSku(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));				
			}
			if(isStylePresent(filter)) {
				criteria.add(Restrictions.eq("style", filter.getStyle()));
			}
			if(filter.getFlavor() != null) {
				criteria.add(Restrictions.eq("flavor", filter.getFlavor()));
			}		
			if(filter.getOrigin() != null) {
				criteria.add(Restrictions.eq("origin", filter.getOrigin()));
			}
			if(filter.getFlavor() != null) {
				criteria.add(Restrictions.eq("flavor", filter.getFlavor()));
			}
			if(filter.getPriceFrom()!= null) {
				criteria.add(Restrictions.ge("price", filter.getPriceFrom()));
			}

			if(filter.getPriceUntil()!= null) {
				criteria.add(Restrictions.le("price", filter.getPriceUntil()));
			}
					
		}
	}
	

	private boolean isStylePresent(BeerFilter filter) {
		return filter.getStyle() != null && filter.getStyle().getId() != null;
	}

}
