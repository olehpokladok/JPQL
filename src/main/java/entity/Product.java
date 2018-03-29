package entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "price", columnDefinition = "DECIMAL(7, 2)")
	private BigDecimal price;

	@Column(name = "in_stock")
	private int inStock;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Post post;

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", price=" + price + ", inStock=" + inStock
				+ ", getId()=" + getId() + "]";
	}

}
