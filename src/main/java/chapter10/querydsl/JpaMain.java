package chapter10.querydsl;

import com.querydsl.core.QueryModifiers;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.naming.directory.SearchResult;
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
            queryDSL(em); // 조회
            paging(em);   // 페이징
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
        member.setAge(30);

        //등록
        em.persist(member);
    }

    private static void queryDSL(EntityManager em) {
        //조회
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        //쿼리, 결과 조회
        List<Member> members =
                query.selectFrom(qMember)
                        //.where(qMember.username.eq("kim").and(qMember.age.gt(10)))
                        .where(qMember.username.eq("kim"), (qMember.age.gt(10)))
                        .fetch();
    }

    private static void paging(EntityManager em) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        //쿼리, 결과 조회
        List<Member> members =
                query.selectFrom(qMember)
                        //.where(qMember.username.eq("kim").and(qMember.age.gt(10)))
                        .where((qMember.age.gt(10)))
                        .orderBy(qMember.age.desc())
                        .offset(10).limit(20)
                        .fetch();

        // or
        QueryModifiers queryModifiers = new QueryModifiers(10L, 20L); // limit, offset

        List<Member> members2 =
                query.selectFrom(qMember)
                        //.where(qMember.username.eq("kim").and(qMember.age.gt(10)))
                        .where(qMember.age.gt(10))
                        .restrict(queryModifiers)
                        .fetch();

        // 실제 페이징 처리
        QueryResults result = query.from(qMember)
                .where(qMember.age.gt(10))
                .offset(0).limit(10)
                .fetchResults();

        long total = result.getTotal(); // 검색된 전체 데이터 수
        long limit = result.getLimit();
        long offse = result.getOffset();
        List<Member> results = result.getResults(); // 조회된 데이터
    }
}