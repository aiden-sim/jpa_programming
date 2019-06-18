package chapter6.다대다단방향;

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
			jpa.save(em);
			jpa.find(em);

			transaction.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	private void save(EntityManager em) {
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품A");
		em.persist(productA);

		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.getProducts().add(productA);
		em.persist(member1);
	}

	private void find(EntityManager em) {
		Member member = em.find(Member.class, "member1");
		List<Product> products = member.getProducts(); //객체 그래프 탐색
		for (Product product : products) {
			System.out.println("product.name = " + product.getName());
		}
	}
}
