package chapter3;

import common.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {
	static EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("jpabook");

	public static void main(String[] args) {
		Member member = createMember("memberA", "회원");
		member.setUsername("회원명변경");

		mergeMember(member);
	}

	private static Member createMember(String memberA, String 회원) {
		// ==영속성 컨텍스트1 시작==
		EntityManager em1 = emf.createEntityManager();
		EntityTransaction tx1 = em1.getTransaction();
		tx1.begin();

		String id = "test";
		String username = "simjunbo";

		Member member = new Member();
		member.setId(id);
		member.setUsername(username);
		member.setAge(2);

		em1.persist(member);
		tx1.commit();

		em1.close(); // 영속성 컨텍스트1 종료. (준영속 상태)
		// ==영속성 컨텍스트1 종료==
		return member;
	}

	private static void mergeMember(Member member) {
		// ==영속성 컨텍스트2 시작==
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		tx2.begin();
		Member mergeMember = em2.merge(member);
		tx2.commit();

		// 준영속 상태
		System.out.println("member = " + member.getUsername());

		// 영속 상태
		System.out.println("mergeMember = " + mergeMember.getUsername());

		System.out.println("em2 contains memeber = " + em2.contains(member));
		System.out.println("em2 contains memeber = " + em2.contains(mergeMember));
		em2.close();
		// ==영속성 컨텍스트2 종료==
	}
}
