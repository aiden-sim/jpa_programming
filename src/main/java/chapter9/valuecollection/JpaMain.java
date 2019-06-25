package chapter9.valuecollection;

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
			save(em);
			transaction.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	private static void save(EntityManager em) {
		Member member = new Member();

		//임베디드 값 타입
		member.setHomeAddress(new Address("통영", "몽돌해수욕장", "660-123"));

		//기본값 타입 컬렉션
		member.getFavoriteFoods().add("짬뽕");
		member.getFavoriteFoods().add("짜장");
		member.getFavoriteFoods().add("탕수육");

		//임베디드 값 타입 컬렉션
		member.getAddressesHistory().add(new Address("서울", "강남", "123-123"));
		member.getAddressesHistory().add(new Address("서울", "강복", "000-000"));
		em.persist(member);
	}
}
