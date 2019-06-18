package chapter6.다대다복합키;

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
		//회원 저장
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);

		//상품 저장
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품1");
		em.persist(productA);

		//회원상품 저장
		MemberProduct memberProduct = new MemberProduct();
		memberProduct.setMember(member1);   // 주문 회원 - 연관관계 설정
		memberProduct.setProduct(productA); // 주문 상품 - 연관관계 설정
		memberProduct.setOrderAmount(2);    // 주문 수량

		em.persist(memberProduct);
	}

	private void find(EntityManager em) {
		// 기본 키 값 생성
		MemberProductId memberProductId = new MemberProductId();
		memberProductId.setMember("member1");
		memberProductId.setProduct("productA");

		MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

		Member member = memberProduct.getMember();
		Product product = memberProduct.getProduct();

		System.out.println("member = " + member.getUserName());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + memberProduct.getOrderAmount());
	}
}
