package chapter8.lazyloading;

import javax.persistence.*;
import java.util.List;

//@Entity
public class Member {

	@Id
	private String id;
	private String username;
	private Integer age;

	// 팀과 사용자 관계는 많이 사용하기 때문에 즉시로딩
	@ManyToOne(fetch = FetchType.EAGER)
	private Team team;

	// 사용자와 주문 관계는 족므 사용하기 때문에 지연로딩
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Order> orders;
}
