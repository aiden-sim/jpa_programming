package chapter10.jpql.typequery;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            naemdParameters(em);
            positionalParameters(em);
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
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("kim");
        member.setAge(2);

        //등록
        em.persist(member);

        //조회
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);

        List<Member> resultList = query.getResultList();
        for (Member mem : resultList) {
            System.out.println("member = " + mem);
        }
    }

    public static void naemdParameters(EntityManager em) {
        String usernameParam = "kim";

        TypedQuery<Member> query =
                em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);

        query.setParameter("username", usernameParam);
        List<Member> resultList = query.getResultList();
    }

    public static void positionalParameters(EntityManager em) {
        String usernameParam = "kim";

        List<Member> members =
                em.createQuery("SELECT m FROM Member m where m.username = ?1", Member.class)
                        .setParameter(1, usernameParam)
                        .getResultList();
    }
}