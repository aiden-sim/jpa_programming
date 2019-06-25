package chapter8.cascade;

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
			saveWithCascade(em);
			transaction.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	private static void saveWithCascade(EntityManager em) {
		Child child1 = new Child();
		Child child2 = new Child();

		Parent parent = new Parent();
		child1.setParent(parent); //연관관계 추가
		child2.setParent(parent); //연관관계 추가
		parent.getChildren().add(child1);
		parent.getChildren().add(child2);

		//부모 저장, 연관된 자식드롣 저장
		em.persist(parent);
	}
}
