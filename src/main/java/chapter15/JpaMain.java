package chapter15;

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

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin();  //트랜잭션 시작
            //logic(em);   //저장
            select(em);  //비즈니스 로직
            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {
        Order order1 = new Order();
        em.persist(order1);

        Order order2 = new Order();
        em.persist(order2);

        Order order3 = new Order();
        em.persist(order3);

        Member member1 = new Member();
        member1.getOrders().add(order1);
        order1.setMember(member1);
        em.persist(member1);

        Member member2 = new Member();
        member2.getOrders().add(order2);
        order2.setMember(member2);
        em.persist(member2);

        Member member3 = new Member();
        member3.getOrders().add(order3);
        order3.setMember(member3);
        em.persist(member3);
    }

    public static void select(EntityManager em) {
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        //List<Member> members = em.createQuery("select m from Member m join fetch m.orders", Member.class).getResultList();
    }
}
