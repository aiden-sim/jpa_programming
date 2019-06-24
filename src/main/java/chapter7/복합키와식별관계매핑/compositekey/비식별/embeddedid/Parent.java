package chapter7.복합키와식별관계매핑.compositekey.비식별.embeddedid;

import javax.persistence.*;

//@Entity
public class Parent {
    @EmbeddedId
    private ParentId id;
    private String name;

    public ParentId getId() {
        return id;
    }

    public void setId(ParentId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
