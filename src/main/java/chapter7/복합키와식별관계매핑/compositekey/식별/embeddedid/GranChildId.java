package chapter7.복합키와식별관계매핑.compositekey.식별.embeddedid;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class GranChildId implements Serializable {
	private ChildId childId; // @MapsId("childId")로 매핑

	@Column(name = "GRANDCHILD_ID")
	private String id;

	public ChildId getChildId() {
		return childId;
	}

	public void setChildId(ChildId childId) {
		this.childId = childId;
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
		GranChildId that = (GranChildId) o;
		return Objects.equals(childId, that.childId) &&
				Objects.equals(id, that.id);
	}

	@Override public int hashCode() {
		return Objects.hash(childId, id);
	}
}
