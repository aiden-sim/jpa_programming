package chapter7.복합키와식별관계매핑.compositekey.비식별.embeddedid;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//@Embeddable
public class ParentId implements Serializable {
    @Column(name = "PARENT_ID1")
    private String id1;

    @Column(name = "PARENT_ID2")
    private String id2;

    public ParentId() {

    }

    public ParentId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentId parentId = (ParentId) o;

        if (!id1.equals(parentId.id1)) return false;
        return id2.equals(parentId.id2);
    }

    @Override
    public int hashCode() {
        int result = id1.hashCode();
        result = 31 * result + id2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ParentId{" +
                "id1='" + id1 + '\'' +
                ", id2='" + id2 + '\'' +
                '}';
    }
}
