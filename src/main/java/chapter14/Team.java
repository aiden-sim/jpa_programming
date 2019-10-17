package chapter14;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;


/**
 * JPA 컬렉션 사용
 */
//@Entity
public class Team {
    @Id
    private String id;

    @OneToMany
    @JoinColumn
    private Collection<Member> members = new ArrayList<Member>();


}
