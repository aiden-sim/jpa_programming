package chapter10.criteria.base;

import javax.persistence.*;
import javax.persistence.criteria.*;
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
            select(em); // 기본
            where(em); // 검색 조건 추가
            greaterThan(em);
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

        String id2 = "id2";
        Member member2 = new Member();
        member2.setId(id2);
        member2.setUsername("sim");
        member2.setAge(20);

        //등록
        em.persist(member2);

        //조회
        //Criteria 사용 준비
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        //루트 클래스(조회를 시작할 클래스)
        Root<Member> m = query.from(Member.class);

        //쿼리 생성
        CriteriaQuery<Member> cq =
                query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> resultList = em.createQuery(cq).getResultList();
    }

    private static void select(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder(); // Criteria 빌더를 얻어야 한다.

        CriteriaQuery<Member> cq = cb.createQuery(Member.class); // 쿼리 빌더에서 Criteria 쿼리를 생성

        Root<Member> m = cq.from(Member.class); // FROM 절 생성. m은 별칭으로 조회의 시작점이다.
        cq.select(m); // SELECT 절

        TypedQuery<Member> query = em.createQuery(cq);
        List<Member> members = query.getResultList();
    }

    private static void where(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);

        // 검색 조건 정의
        Predicate usernameEual = cb.equal(m.get("username"), "kim");

        // 정렬 조건 정의
        Order ageDesc = cb.desc(m.get("age"));

        // 쿼리 생성
        cq.select(m)
                .where(usernameEual) // WHERE 절 생성
                .orderBy(ageDesc);

        List<Member> members = em.createQuery(cq).getResultList();
    }

    private static void greaterThan(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);

        // 검색 조건 정의
        Predicate ageGt = cb.greaterThan(m.<Integer>get("age"), 10);
        cq.select(m);
        cq.where(ageGt);
        cq.orderBy(cb.desc(m.get("age")));
        List<Member> members = em.createQuery(cq).getResultList();
    }
}