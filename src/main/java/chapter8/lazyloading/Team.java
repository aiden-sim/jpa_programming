package chapter8.lazyloading;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Team {
	private String id;

	@Id public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
