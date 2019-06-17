package chapter6.일대다단방향;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");

		Team team1 = new Team("team1");
		team1.getMembers().add(member1);
		team1.getMembers().add(member2);

		em.persist(member1); // Insert -member1
		em.persist(member2); // Insert -member2
		em.persist(team1);   // Insert -team1, update -member1, member2
	}

}
