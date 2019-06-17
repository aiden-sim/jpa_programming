package chapter6.일대일양방향;

import javax.persistence.*;

/**
 * 예제 실행시 주석 해제
 */
@Entity
public class Locker {
	@Id @GeneratedValue
	@Column(name = "LOCKER_ID")
	private Long id;
	private String name;

	@OneToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
