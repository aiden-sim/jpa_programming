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
            distinct(em); // 중복제거
            construct(em); // 생성자 구문
            tuple(em); // 튜플
            groupby(em); // group by
            having(em);  // having
            join(em);    // join
            subquery(em); //subquery
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

        Team team = new Team();
        team.setName("team1");
        team.getMembers().add(member);
        team.getMembers().add(member2);
        member.setTeam(team);
        member2.setTeam(team);
        em.persist(team);

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

    private static void distinct(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);
        cq.multiselect(m.get("username"), m.get("age")).distinct(true);
        //cq.select(cb.array(m.get("username"), m.get("age"))).distinct(true);

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> resultList = query.getResultList();
    }

    private static void construct(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MemberDTO> cq = cb.createQuery(MemberDTO.class);
        Root<Member> m = cq.from(Member.class);

        cq.select(cb.construct(MemberDTO.class, m.get("username"), m.get("age"))); // 생성자 위치로 매핑하는듯

        TypedQuery<MemberDTO> query = em.createQuery(cq);
        List<MemberDTO> resultList = query.getResultList();
    }

    private static void tuple(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        // CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<Member> m = cq.from(Member.class);
        cq.multiselect(
                m.get("username").alias("username"), // 튜플 별칭
                m.get("age").alias("age")
        );

        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> resultList = query.getResultList();
        for (Tuple tuple : resultList) {
            // 튜플 별칭으로 조회
            String username = tuple.get("username", String.class);
            Integer age = tuple.get("age", Integer.class);
        }
    }

    private static void groupby(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);

        Expression maxAge = cb.max(m.<Integer>get("age"));
        Expression minAge = cb.min(m.<Integer>get("age"));

        cq.multiselect(m.get("team").get("name"), maxAge, minAge);
        cq.groupBy(m.get("team").get("name")); // group by

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> resultList = query.getResultList();
    }

    private static void having(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);

        Expression maxAge = cb.max(m.<Integer>get("age"));
        Expression minAge = cb.min(m.<Integer>get("age"));

        cq.multiselect(m.get("team").get("name"), maxAge, minAge);
        cq.groupBy(m.get("team").get("name"));
        cq.having(cb.gt(minAge, 10));

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> resultList = query.getResultList();
    }

    private static void join(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Member> m = cq.from(Member.class);
        Join<Member, Team> t = m.join("team", JoinType.INNER.INNER); // 내부 조인

        cq.multiselect(m, t)
                .where(cb.equal(t.get("name"), "team1"));

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> resultList = query.getResultList();
    }

    private static void subquery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> mainQuery = cb.createQuery(Member.class);

        Subquery<Double> subquery = mainQuery.subquery(Double.class);

        // 서브 쿼리
        Root<Member> m2 = subquery.from(Member.class);
        subquery.select(cb.avg(m2.<Integer>get("age")));

        // 메인 쿼리
        Root<Member> m = mainQuery.from(Member.class);
        mainQuery.select(m)
                .where(cb.ge(m.<Integer>get("age"), subquery));

        TypedQuery<Member> query = em.createQuery(mainQuery);
        List<Member> resultList = query.getResultList();
    }
}