package chapter14;

import javax.persistence.*;
import java.util.*;

//@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;


    /**
     * Collection, List 예제
     */
    @OneToMany
    @JoinColumn
    private Collection<CollectionChild> collection = new ArrayList<CollectionChild>();

    @OneToMany
    @JoinColumn
    private List<ListChild> list = new ArrayList<ListChild>();

    /**
     * Set 예제
     */
    @OneToMany
    @JoinColumn
    private Set<SetChild> set = new HashSet<SetChild>();

}
