package chapter10.nativequery;

import javax.persistence.*;
import java.math.BigInteger;
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
			entitySelect(em); // 엔티티 조회
			valueSelect(em);  // 값 조회
			//resultMapping(em); // 결과 매핑
			//namedQuery(em);    // NamedNative
			nativeQueryWithResultMapping(em); // resultMapping + namedNative
			xmlQuery(em);        // xml
			//storedProcedure(em); // SP
			//sotredProcedureByParameter(em); // SP 파라미터 형태
			tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}

		emf.close(); //엔티티 매니저 팩토리 종료
	}

	private static void logic(EntityManager em) {
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("kim");
		member.setAge(20);

		//등록
		em.persist(member);

		String id2 = "id2";
		Member member2 = new Member();
		member2.setId(id2);
		member2.setUsername("kim2");
		member2.setAge(30);

		//등록
		em.persist(member2);

		Team team = new Team();
		team.setName("team1");
		team.getMembers().add(member);
		team.getMembers().add(member2);
		member.setTeam(team);
		member2.setTeam(team);
		em.persist(team);

		Orders order = new Orders();
		order.setOrderCount(10);
		order.setMember(member);
		em.persist(order);
	}

	private static void entitySelect(EntityManager em) {
		String sql = "SELECT ID, AGE, NAME, TEAM_ID " +
				"FROM MEMBER WHERE AGE > ?";

		Query nativeQuery = em.createNativeQuery(sql, Member.class)
				.setParameter(1, 20);

		List<Member> resultList = nativeQuery.getResultList();
	}

	private static void valueSelect(EntityManager em) {
		String sql = "SELECT ID, AGE, NAME, TEAM_ID " +
				"FROM MEMBER WHERE AGE > ?";

		Query nativeQuery = em.createNativeQuery(sql) // 두번째 파라미터 사용하지 않으면 값 조회 이다.
				.setParameter(1, 10);

		List<Object[]> resultList = nativeQuery.getResultList();
		for (Object[] row : resultList) {
			System.out.println("id = " + row[0]);
			System.out.println("age = " + row[1]);
			System.out.println("name = " + row[2]);
			System.out.println("team_id = " + row[3]);
		}
	}

	private static void resultMapping(EntityManager em) {
		String sql =
				"SELECT M.ID, AGE, NAME, TEAM_ID, I.ORDER_COUNT " +
						"FROM MEMBER M " +
						"LEFT JOIN " +
						" (SELECT IM.ID, COUNT(*) AS ORDER_COUNT " +
						" FROM ORDERS O, MEMBER IM " +
						" WHERE O.MEMBER_ID = IM.ID) I " +
						"ON M.ID = I.ID";

		Query nativeQuery = em.createNativeQuery(sql, "memberWithOrderCount");
		List<Object[]> resultList = nativeQuery.getResultList();
		for (Object[] row : resultList) {
			Member member = (Member) row[0];
			BigInteger orderCount = (BigInteger) row[1];

			System.out.println("member = " + member);
			System.out.println("orderCount =  " + orderCount);
		}
	}

	private static void namedQuery(EntityManager em) {
		TypedQuery<Member> nativeQuery = em.createNamedQuery("Member.memberSQL", Member.class)
				.setParameter(1, 20);

		List<Member> resultList = nativeQuery.getResultList();
		for (Member mem : resultList) {
			System.out.println("member = " + mem);
		}
	}

	private static void nativeQueryWithResultMapping(EntityManager em) {
		List<Object[]> resultList = em.createNamedQuery("Member.memberWithOrderCount")
				.getResultList();
		for (Object[] row : resultList) {
			Member member = (Member) row[0];
			BigInteger orderCount = (BigInteger) row[1];

			System.out.println("member = " + member);
			System.out.println("orderCount =  " + orderCount);
		}
	}

	private static void xmlQuery(EntityManager em) {
		List<Object[]> resultList = em.createNamedQuery("Member.memberWithOrderCountByXml")
				.getResultList();
		for (Object[] row : resultList) {
			Member member = (Member) row[0];
			BigInteger orderCount = (BigInteger) row[1];

			System.out.println("member = " + member);
			System.out.println("orderCount =  " + orderCount);
		}
	}

	/*
	프로시저
	DELIMTER //

	CREATE PROCEDURE proc_multiply (INOUT inParam INT, INOUT outParam INT)
	BEGIN
	SET out Param = inParam *2;
	END //
	*/

	private static void storedProcedure(EntityManager em) {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("proc_multiply");
		spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);

		spq.setParameter(1, 100);
		spq.execute();

		Integer out = (Integer) spq.getOutputParameterValue(2);
		System.out.println("out = " + out);
	}

	private static void sotredProcedureByParameter(EntityManager em) {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("proc_multiply");
		spq.registerStoredProcedureParameter("inParam", Integer.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter("outParam", Integer.class, ParameterMode.OUT);

		spq.setParameter("inParam", 100);
		spq.execute();

		Integer out = (Integer) spq.getOutputParameterValue("outParam");
		System.out.println("out = " + out);
	}
}