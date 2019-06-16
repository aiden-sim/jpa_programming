package chapter5;

import javax.persistence.*;

/**
 * 예제 실행시 주석 해제
 */
@Entity
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String username;

    //연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() {
    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    // 연관관계 설정
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
