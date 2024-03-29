package chapter5;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 예제 실행시 주석 해제
 */
//@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

    // 양방향 엔티티
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public Team(){}

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
