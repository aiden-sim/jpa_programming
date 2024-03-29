package chapter3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 예제 실행시 주석 해제
 */
/*@Entity
@Table(name="MEMBER")*/
public class Member {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String username;

	private Integer age;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}