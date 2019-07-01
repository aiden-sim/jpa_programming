package chapter10.jpql.typequery;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
	public static void main(String[] args) {
		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

		try {
			tx.begin(); //트랜잭션 시작
			logic(em);  //비즈니스 로직
			naemdParameters(em);
			positionalParameters(em);
			newCommand(em);
			page(em);
			innerJoin(em);
			outerJoin(em);
			thetaJoin(em);
			joinOn(em);
			entityFetchJoin(em);      //Only JPA
			collectionFetchJoin(em);  //Only JPA
			fetchJoinAndDistinct(em); //Only JPA
			tx.commit();//트랜잭션 커밋

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}

		emf.close(); //엔티티 매니저 팩토리 종료
	}

	public static void logic(EntityManager em) {
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("kim");
		member.setAge(2);
		em.persist(member);

		id = "id2";
		Member member2 = new Member();
		member2.setId(id);
		member2.setUsername("kim2");
		member2.setAge(2);
		em.persist(member2);

		Team team = new Team();
		team.setName("team1");
		team.getMembers().add(member);
		team.getMembers().add(member2);
		member.setTeam(team);
		member2.setTeam(team);
		em.persist(team);

		//조회
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);

		List<Member> resultList = query.getResultList();
		for (Member mem : resultList) {
			System.out.println("member = " + mem);
		}
	}

	public static void naemdParameters(EntityManager em) {
		String usernameParam = "kim";

		TypedQuery<Member> query =
				em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);

		query.setParameter("username", usernameParam);
		List<Member> resultList = query.getResultList();
	}

	public static void positionalParameters(EntityManager em) {
		String usernameParam = "kim";

		List<Member> members =
				em.createQuery("SELECT m FROM Member m where m.username = ?1", Member.class)
						.setParameter(1, usernameParam)
						.getResultList();
	}

	private static void newCommand(EntityManager em) {
		TypedQuery<UserDTO> query = em.createQuery("SELECT new chapter10.jpql.typequery.UserDTO(m.username, m.age) FROM Member m",
				UserDTO.class);

		List<UserDTO> resultList = query.getResultList();
	}

	private static void page(EntityManager em) {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC", Member.class);

		query.setFirstResult(10);
		query.setMaxResults(20);
		List<Member> resultList = query.getResultList();
	}

	private static void innerJoin(EntityManager em) {
		String teamName = "team1";
		String query = "SELECT m FROM Member m INNER JOIN m.team t "
				+ "WHERE t.name = :teamName";

		// Member 기준 Entity Join  (Team 값이 없다.)
		List<Member> members = em.createQuery(query, Member.class)
				.setParameter("teamName", teamName)
				.getResultList();

		String query2 = "SELECT m, t FROM Member m JOIN m.team t";
		// 두 엔티티 모두 조회
		List<Object[]> result = em.createQuery(query2).getResultList();
		for (Object[] row : result) {
			Member member = (Member) row[0];
			Team team = (Team) row[1];
		}
	}

	private static void outerJoin(EntityManager em) {
		String username = "kim2";
		String query = "SELECT m FROM Member m LEFT OUTER JOIN m.team t "
				+ "WHERE m.username = :username";

		List<Member> members = em.createQuery(query, Member.class)
				.setParameter("username", username)
				.getResultList();
	}

	private static void thetaJoin(EntityManager em) {
		String query = "select count(m) from Member m, Team t where m.username = t.name";
		Long result = em.createQuery(query, Long.class).getSingleResult();
	}

	private static void joinOn(EntityManager em) {
		String query = "select m, t from Member m left join m.team t on t.name = 'team2'";
		List<Object[]> result = em.createQuery(query).getResultList();
		for (Object[] row : result) {
			Member member = (Member) row[0];
			Team team = (Team) row[1];
		}
	}

	private static void entityFetchJoin(EntityManager em) {
		String jpql = "select m from Member m join fetch m.team";

		List<Member> members = em.createQuery(jpql, Member.class).getResultList();

		for (Member member : members) {
			// 페치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안 함
			System.out.println("username = " + member.getUsername() + ", " + "teamname = " + member.getTeam().getName());
		}
	}

	private static void collectionFetchJoin(EntityManager em) {
		String jpql = "select t from Team t join fetch t.members where t.name = 'team1'";
		List<Team> teams = em.createQuery(jpql, Team.class).getResultList();

		for (Team team : teams) {
			System.out.println("teamname = " + team.getName() + ", team = " + team);

			for (Member member : team.getMembers()) {
				//페치 조인으로 팀과 회원을 함께 조회해서 지연 로딩 발생 안 함
				System.out.println("->username = " + member.getUsername() + ", member = " + member);
			}
		}
	}

	private static void fetchJoinAndDistinct(EntityManager em) {
		String jpql = "select distinct t from Team t join fetch t.members where t.name = 'team1'";
		// 원래 결과가 하나만 나와야 되는데 ...
		List<Team> teams = em.createQuery(jpql, Team.class).getResultList();

		for (Team team : teams) {
			System.out.println("teamname = " + team.getName() + ", team = " + team);

			for (Member member : team.getMembers()) {
				//페치 조인으로 팀과 회원을 함께 조회해서 지연 로딩 발생 안 함
				System.out.println("->username = " + member.getUsername() + ", member = " + member);
			}
		}
	}
}