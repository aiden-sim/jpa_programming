package chapter7.복합키와식별관계매핑.compositekey.식별.idclass;

import chapter7.복합키와식별관계매핑.compositekey.비식별.idclass.Child;

import javax.persistence.*;

//손자
/*@Entity
@IdClass(GrandChildId.class)*/
public class GrandChild {
	@Id
	@ManyToOne
	@JoinColumns(
			{
					@JoinColumn(name = "PARENT_ID"),
					@JoinColumn(name = "CHILD_ID")
			}
	)
	private chapter7.복합키와식별관계매핑.compositekey.비식별.idclass.Child child;

	@Id @Column(name = "GRANDCHILD_ID")
	private String id;
	private String name;

	public chapter7.복합키와식별관계매핑.compositekey.비식별.idclass.Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

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
}
