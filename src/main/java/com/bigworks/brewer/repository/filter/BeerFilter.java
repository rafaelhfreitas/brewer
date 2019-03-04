package com.bigworks.brewer.repository.filter;

import java.math.BigDecimal;

import com.bigworks.brewer.model.Flavor;
import com.bigworks.brewer.model.Origin;
import com.bigworks.brewer.model.Style;

public class BeerFilter {
	
	
	private String sku;
	private String name;
	private Style style;
	private Flavor flavor;
	private Origin origin;
	private BigDecimal priceFrom;
	private BigDecimal priceUntil;
	
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Style getStyle() {
		return style;
	}
	
	public void setStyle(Style style) {
		this.style = style;
	}
	
	public Flavor getFlavor() {
		return flavor;
	}
	
	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}
	
	public Origin getOrigin() {
		return origin;
	}
	
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}
	
	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}
	
	public BigDecimal getPriceUntil() {
		return priceUntil;
	}
	
	public void setPriceUntil(BigDecimal priceUntil) {
		this.priceUntil = priceUntil;
	}

}
