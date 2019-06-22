package chapter7.compositekey.idclass;

import java.io.Serializable;

public class ParentId implements Serializable {
    private String id1; //Parent.id1 매핑
    private String id2; //Parent.id2 매핑

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
