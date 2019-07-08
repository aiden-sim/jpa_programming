package chapter10.nativequery;

import javax.persistence.*;

@Entity
public class Orders {
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@Column(name = "ORDER_COUNT")
	private long orderCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}
}
