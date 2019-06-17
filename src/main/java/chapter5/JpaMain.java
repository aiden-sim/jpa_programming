package chapter5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
	public static void main(String[] args) {
		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

		EntityTransaction transaction = em.getTransaction(); //트랜잭션 기능 획득
		try {
			transaction.begin(); //트랜잭션 시작

			JpaMain jpa = new JpaMain();
			jpa.testSave(em);
			jpa.testSaveNonOwner(em);
			/*jpa.testSelect(em);
			jpa.biDirection(em);
			jpa.queryLogicJoin(em);
			jpa.updateRelation(em);
			jpa.deleteRelation(em);
			jpa.deleteTeam(em);*/

			transaction.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	private void testSave(EntityManager em) {
		// 회원과 팀을 저장
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);

		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		em.persist(member1);

		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1); // 연관관계 설정 member1 -> team1
		em.persist(member2);

	}

	private void testSelect(EntityManager em) {
		Member member = em.find(Member.class, "member1");
		Team team = member.getTeam(); // 객체 그래프 탐색
		System.out.println("팀 이름 = " + team.getName());
	}

	private void queryLogicJoin(EntityManager em) {
		String jpql = "select m from Member m join m.team t where " +
				"t.name=:teamName";

		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();

		for (Member member : resultList) {
			System.out.println("[query] member.username=" + member.getUsername());
		}
	}

	private void updateRelation(EntityManager em) {
		// 새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);

		// 회원 1에 새로운 팀2 설정
		Member member = em.find(Member.class, "member1");
		member.setTeam(team2);
	}

	private void deleteRelation(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null); // 연관관계 제거
	}

	private void deleteTeam(EntityManager em) {
		Team team3 = new Team("team3", "팀3");
		em.persist(team3);

		// 회원1 저장
		Member member3 = new Member("member3", "회원3");
		member3.setTeam(team3); // 연관관계 설정 member1 -> team1
		em.persist(member3);

		//삭제시 연관관계 제거해 주어야 함.
		member3.setTeam(null);
		em.remove(team3);
	}

	private void biDirection(EntityManager em) {
		Team team = em.find(Team.class, "team1");
		List<Member> members = team.getMembers(); // 팀 -> 회원 객체 그래프 탐색

		// 실제 db에 데이터가 없으면 가지고 오지 못햇다. 캐시에서 못가지고 오나?
		for (Member member : members) {
			System.out.println("member.username = " + member.getUsername());
		}
	}

	private void testSaveNonOwner(EntityManager em) {
		// 회원 3 저장
		Member member3 = new Member("member3", "회원3");
		em.persist(member3);

		Member member4 = new Member("member4", "회원4");
		em.persist(member4);

		Team team3 = new Team("team3", "팀3");
		// 주인이 아닌 곳만 연관관계 설정
		team3.getMembers().add(member3);
		team3.getMembers().add(member4);

		em.persist(team3);
	}
}
