package chapter10.etc;

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
            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            entity(em);
            primary(em);
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
        Member member = new Member();
        member.setUsername("kim");
        member.setAge(2);
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("kim2");
        member2.setAge(2);
        em.persist(member2);

        Team team = new Team();
        team.setName("team1");
        team.getMembers().add(member);
        team.getMembers().add(member2);
        member.setTeam(team);
        member2.setTeam(team);
        em.persist(team);
    }

    // 기본 키 값
    private static void entity(EntityManager em) {
        Member member = em.find(Member.class, 1L);

        String qlString = "select m from Member m where m = :member";
        List resultList = em.createQuery(qlString)
                .setParameter("member", member)
                .getResultList();
    }

    private static void primary(EntityManager em) {
        Team team = em.find(Team.class, 3L);

        String qlString = "select m from Member m where m.team = :team";
        List resultList = em.createQuery(qlString)
                .setParameter("team", team)
                .getResultList();
    }
}