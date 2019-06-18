package chapter6.다대다양방향;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 예제 실행시 주석 해제
 */
//@Entity
public class Member {
	@Id @Column(name = "MEMBER_ID")
	private String id;

	private String username;

	@ManyToMany
	@JoinTable(name = "MEMBER_PRODUCT",
			joinColumns = @JoinColumn(name = "MEMBER_ID"),
			inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private List<Product> products = new ArrayList<Product>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		products.add(product);
		product.getMembers().add(this);
	}
}
