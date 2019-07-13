package chapter10.deep;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            logic(em);
            bulk(em);
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
        Product product = new Product();
        product.setId("id1");
        product.setName("productA");
        product.setPrice(1000L);
        em.persist(product);
    }

    private static void bulk(EntityManager em) {
        // 상품 조회 가격은 1000
        Product productA =
                em.createQuery("select p from Product p where p.name = :name", Product.class)
                        .setParameter("name", "productA")
                        .getSingleResult();

        // 출력 결과 1000
        System.out.println("productA 수정 전 = " + productA.getPrice());

        // 벌크 연산 수행으로 모든 상품 가격 10% 상승
        em.createQuery("update Product p set p.price = p.price * 1.1")
                .executeUpdate();

        //em.refresh(productA); // refresh를 통해 다시 조회 하면 수정 후 데이터 정상 출력

        // 출력 결과: 1000
        System.out.println("productA 수정 후 = " + productA.getPrice());
    }
}
