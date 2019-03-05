package com.bigworks.brewer.repository.pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {
	
	
	public void prepare(Criteria criteria, Pageable pageable) {
		int actualPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		
		criteria.setFirstResult(actualPage * totalRecordsPerPage );
		criteria.setMaxResults(totalRecordsPerPage);
		
		Sort sort = pageable.getSort();
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property): Order.desc(property) );
		}
	}

}
