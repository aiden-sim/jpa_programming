package chapter7.복합키와식별관계매핑.compositekey.식별.idclass;

import javax.persistence.*;

//자식
/*@Entity
@IdClass(ChildId.class)*/
public class Child {
	@Id
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public Parent parent;

	@Id
	@Column(name = "CHILD_ID")
	private String childId;

	private String name;

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
