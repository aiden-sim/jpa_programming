package chapter8.orphan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Parent {
	@Id @GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private List<Child> children = new ArrayList<Child>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
}
