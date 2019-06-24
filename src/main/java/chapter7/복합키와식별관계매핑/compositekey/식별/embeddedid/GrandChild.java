package chapter7.복합키와식별관계매핑.compositekey.식별.embeddedid;

import javax.persistence.*;

//손자
//@Entity
public class GrandChild {
	@EmbeddedId
	private GranChildId id;

	@MapsId("childId") //GrandChildId.childId 매핑
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "PARENT_ID"),
			@JoinColumn(name = "CHILD_ID")
	})
	private Child child;

	private String name;

	public GranChildId getId() {
		return id;
	}

	public void setId(GranChildId id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
