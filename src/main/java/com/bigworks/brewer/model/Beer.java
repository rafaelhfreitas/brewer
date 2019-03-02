package com.bigworks.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.bigworks.brewer.validation.SKU;


@Entity
@Table(name = "beer")
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
    @SKU
	@NotBlank(message = "SKU é obrigatório")
	private String sku;
	
	@NotBlank(message = "Nome é obrigatório")
	private String name;
	
	@NotBlank(message = "Descrição é obrigatório")
    @Size( max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
	private String description;				 
	
	@NotNull(message= "O preço é obrigatório")
	@DecimalMin("0.01")
	@DecimalMax(value="9999999.99", message="O valor do teor alcóolico deve ser menor")
	private BigDecimal price;
	
	@NotNull(message = "O teor alcóolico é obrigatório")
	@DecimalMax(value= "100.0" , message = "O valor do teor alcóolico deve ser menor que 100")	
	@Column(name ="alcoholic_strength")
	private BigDecimal alcoholicStrength;

	@NotNull(message ="A comissão é obrigatória")
	@DecimalMax(value= "100.0" , message = "A comissão deve ser igual ou menor que 100")
	private BigDecimal commission;
	
	@NotNull(message="A quantidade do estoque é obrigatório")
	@Max(value = 9999, message="A quantidade em estoque deve ser menor que 9.999")
	@Column(name ="quantity_stock")
	private Integer quantityStock;
	
	@NotNull(message = "A origem é obrigatória")
	@Enumerated(EnumType.STRING)
	private Origin origin;
	
	@NotNull(message = "O sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	private Flavor flavor;
	
	@NotNull(message = "O estilo é obrigatório")
	@ManyToOne
	@JoinColumn(name = "style_id")
	private Style style;
		 
	private String picture;
	
	@Column(name="content_type")
	private String contentType;
	
	@PrePersist @PreUpdate
	public void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
    
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
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
	
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getAlcoholicStrength() {
		return alcoholicStrength;
	}
	
	public void setAlcoholicStrength(BigDecimal alcoholicStrength) {
		this.alcoholicStrength = alcoholicStrength;
	}
	
	public BigDecimal getCommission() {
		return commission;
	}
	
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	
	public Integer getQuantityStock() {
		return quantityStock;
	}
	
	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}
	
	public Origin getOrigin() {
		return origin;
	}
	
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
	public Flavor getFlavor() {
		return flavor;
	}
	
	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}
	
	public Style getStyle() {
		return style;
	}
	
	public void setStyle(Style style) {
		this.style = style;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	

}
