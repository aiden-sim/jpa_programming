package chapter6.다대다양방향;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 예제 실행시 주석 해제
 */
//@Entity
public class Product {
	@Id
	private String id;
	private String name;

	@ManyToMany(mappedBy = "products") // 역방향 추가
	private List<Member> members = new ArrayList<Member>();

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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
