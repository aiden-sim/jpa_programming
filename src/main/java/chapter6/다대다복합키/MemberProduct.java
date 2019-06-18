package chapter6.다대다복합키;

import javax.persistence.*;

/**
 * 복합 기본키
 */
//@Entity
@IdClass(MemberProductId.class)
public class MemberProduct {

	@Id //pk
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID") //fk
	private Member member; // MemberProductId.member와 연결

	@Id //pk
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID") //fk
	private Product product; // MemberProductId.product와 연결

	private int orderAmount;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
}
