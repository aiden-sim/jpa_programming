package chapter7.복합키와식별관계매핑.compositekey.비식별.idclass;

import javax.persistence.Column;
import javax.persistence.Id;

/*@Entity
@IdClass(ParentId.class)*/
public class Parent {
    @Id
    @Column(name = "PARENT_ID1")
    private String id1; //ParentId.id1과 연결

    @Id
    @Column(name = "PARENT_ID2")
    private String id2; //ParentId.id2와 연결
    private String name;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
