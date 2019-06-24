package chapter3;

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
			Member member = new Member();
			member.setId("memberA");
			member.setUsername("지한");
			member.setAge(2);

			//등록
			em.persist(member);

			// 영속 엔티티 조회
			Member memberA = em.find(Member.class, "memberA");

			// 영속 엔티티 데이터 수정
			memberA.setUsername("hi");
			memberA.setAge(10);

			transaction.commit();//트랜잭션 커밋

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}

		emf.close(); //엔티티 매니저 팩토리 종료
	}

	public void testDetached() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction transaction = em.getTransaction();

		Member member = new Member();
		member.setId("memeberA");
		member.setUsername("회원A");

		// 회원 엔티티 영속 상태
		em.persist(member);

		// 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
		em.detach(member);
		transaction.commit();
	}
}
