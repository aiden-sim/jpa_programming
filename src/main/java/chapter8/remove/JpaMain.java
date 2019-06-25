package chapter8.remove;

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
			saveNoCascade(em);
			remove(em);
			transaction.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	private static void saveNoCascade(EntityManager em) {
		//부모 저장
		Parent parent = new Parent();
		em.persist(parent);

		//1번 자식 저장
		Child child1 = new Child();
		child1.setParent(parent); //자식 -> 부모 연관관계 설정
		parent.getChildren().add(child1); //부모 -> 자식
		em.persist(child1);

		//2번 자식 저장
		Child child2 = new Child();
		child2.setParent(parent); //자식 -> 부모 연관관계 설정
		parent.getChildren().add(child2); //부모 -> 자식
		em.persist(child2);
	}

	private static void remove(EntityManager em) {
		Parent findParent = em.find(Parent.class, 1L);
		em.remove(findParent);
	}
}
