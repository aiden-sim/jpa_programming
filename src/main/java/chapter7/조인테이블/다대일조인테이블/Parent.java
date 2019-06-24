package chapter7.조인테이블.다대일조인테이블;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//부모
@Entity
public class Parent {
	@Id @GeneratedValue
	@Column(name = "PARENT_ID")
	private Long id;
	private String name;

	@OneToMany(mappedBy = "parent")
	private List<Child> child = new ArrayList<Child>();

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

	public List<Child> getChild() {
		return child;
	}

	public void setChild(List<Child> child) {
		this.child = child;
	}
}
