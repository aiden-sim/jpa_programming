package chapter7.복합키와식별관계매핑.compositekey.식별.embeddedid;

import javax.persistence.*;

//자식
//@Entity
public class Child {
	@EmbeddedId
	private ChildId id;

	@MapsId("parentId") //ChildId.parentId 매핑
	@ManyToOne
	@JoinColumn(name = " PARENT_ID")
	private Parent parent;

	private String name;

	public ChildId getId() {
		return id;
	}

	public void setId(ChildId id) {
		this.id = id;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
