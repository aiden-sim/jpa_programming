package chapter7.compositekey.embeddedid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction transaction = em.getTransaction(); //트랜잭션 기능 획득
        try {
            transaction.begin(); //트랜잭션 시작

            JpaMain jpa = new JpaMain();
            jpa.save(em);
            jpa.find(em);

            transaction.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private void save(EntityManager em) {
        Parent parent = new Parent();
        ParentId parentId = new ParentId("myId1", "myId2");
        parent.setId(parentId);
        parent.setName("parentName");
        em.persist(parent);
    }

    private void find(EntityManager em) {
        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent = em.find(Parent.class, parentId);
        System.out.println(parent);
    }
}
