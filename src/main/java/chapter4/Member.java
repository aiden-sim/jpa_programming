package chapter4;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 예제 실행시 주석 해제
 */
/*@Entity
@DynamicUpdate
@Table(name = "MEMBER", uniqueConstraints = { @UniqueConstraint(
		name = "NAME_AGE_UNIQUE",
		columnNames = { "NAME", "AGE" }) })*/
public class Member {


	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME", nullable = false, length = 10)
	private String username;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Lob
	private String description;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}