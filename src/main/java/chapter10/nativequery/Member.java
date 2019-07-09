package chapter10.nativequery;

import javax.persistence.*;

/**
 * 예제 실행시 주석 해제
 */

@Entity
/*
// resultMapping
@SqlResultSetMapping(name = "memberWithOrderCount",
		entities = { @EntityResult(entityClass = Member.class) },
		columns = { @ColumnResult(name = "ORDER_COUNT") })
*/
/*
// namedQuery
@NamedNativeQuery(
		name = "Member.member",
		query = "SELECT ID, AGE ,NAME, TEAM_ID " +
				"FROM MEMBER WHERE AGE > ?",
		resultClass = Member.class
)
*/
// resultMapping + namedSQL +
@SqlResultSetMapping(name = "memberWithOrderCount",
		entities = { @EntityResult(entityClass = Member.class) },
		columns = { @ColumnResult(name = "ORDER_COUNT") })
@NamedNativeQuery(
		name = "Member.memberWithOrderCount",
		query = "SELECT M.ID, AGE, NAME, TEAM_ID, I.ORDER_COUNT " +
				"FROM MEMBER M " +
				"LEFT JOIN " +
				"   (SELECT IM.ID, COUNT(*) AS ORDER_COUNT " +
				"   FROM ORDERS O, MEMBER IM " +
				"   WHERE O.MEMBER_ID = IM.ID) I " +
				"ON M.ID = I.ID",
		resultSetMapping = "memberWithOrderCount"
)
public class Member {
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String username;

	@Column(name = "AGE")
	private Integer age;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
	private Team team;

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

		// 무한루프에 빠지지 않도록
		if (!team.getMembers().contains(this)) {
			team.getMembers().add(this);
		}
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}