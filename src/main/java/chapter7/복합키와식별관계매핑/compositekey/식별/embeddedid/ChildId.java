package chapter7.복합키와식별관계매핑.compositekey.식별.embeddedid;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChildId implements Serializable {
	private String parentId;

	@Column(name = "CHILD_ID")
	private String id;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChildId childId = (ChildId) o;
		return Objects.equals(parentId, childId.parentId) &&
				Objects.equals(id, childId.id);
	}

	@Override public int hashCode() {
		return Objects.hash(parentId, id);
	}
}
