package chapter5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        JpaMain jpa = new JpaMain();
        jpa.testSave();
    }

    public void testSave() {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction transaction = em.getTransaction(); //트랜잭션 기능 획득
        try {
            transaction.begin(); //트랜잭션 시작

            // 회원과 팀을 저장
            // 팀1 저장
            Team team1 = new Team("team1", "팀1");
            em.persist(team1);

            // 회원1 저장
            Member member1 = new Member("member1", "회원1");
            member1.setTeam(team1); // 연관관계 설정 member1 -> team1
            em.persist(member1);

            // 회원2 저장
            Member member2 = new Member("member2", "회원2");
            member2.setTeam(team1); // 연관관계 설정 member1 -> team1
            em.persist(member2);

            transaction.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
