package chapter6.다대다기본키;

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
		// 회원 저장
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);

		// 상품 저장
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품1");
		em.persist(productA);

		// 주문 저장
		Orders orders = new Orders();
		orders.setMember(member1);   // 주문 회원 - 연관관계 설정
		orders.setProduct(productA); // 주문 상품 - 연관관계 설정
		orders.setOrderAmount(2);    // 주문 수량
		em.persist(orders);
	}

	private void find(EntityManager em) {
		Long orderId = 1L;
		Orders order = em.find(Orders.class, orderId);

		Member member = order.getMember();
		Product product = order.getProduct();

		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + order.getOrderAmount());
	}
}
