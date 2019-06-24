package chapter7.복합키와식별관계매핑.compositekey.식별.embeddedid;

import javax.persistence.Column;
import javax.persistence.Id;

//부모
//@Entity
public class Parent {
	@Id @Column(name = "PARENT_ID")
	private String id;
	private String name;

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
