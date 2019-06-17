package chapter6.일대일단방향;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 예제 실행시 주석 해제
 */
//@Entity
public class Locker {
	@Id @GeneratedValue
	@Column(name = "LOCKER_ID")
	private Long id;
	private String name;

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

}
