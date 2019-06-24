package chapter7.복합키와식별관계매핑.compositekey.식별.idclass;

import java.io.Serializable;

public class GrandChildId implements Serializable {
	private ChildId childId; //GrandChild.child 매핑
	private String id;       //GrandChild.id 매핑

	public GrandChildId() {
	}

	public GrandChildId(ChildId childId, String id) {
		this.childId = childId;
		this.id = id;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GrandChildId that = (GrandChildId) o;

		if (!childId.equals(that.childId))
			return false;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		int result = childId.hashCode();
		result = 31 * result + id.hashCode();
		return result;
	}
}
