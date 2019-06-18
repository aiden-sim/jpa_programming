package chapter6.다대다단방향;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 예제 실행시 주석 해제
 */
//@Entity
public class Product {
	@Id @Column(name = "PRODUCT_ID")
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
