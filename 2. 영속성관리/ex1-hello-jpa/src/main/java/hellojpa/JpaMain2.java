package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Member2 member1 = new Member2(150L, "A");
            Member2 member2 = new Member2(160L, "B");

            em.persist(member1);
            em.persist(member2);

            tx.commit(); // 이 시점에 DB로 날라간다!


            // 수정
            Member2 member3 = em.find(Member2.class, 150L);
            member3.setName("ZZZZ");


            // 삭제
            Member2 member4 = em.find(Member2.class, "member4");
            em.remove(member4);

            // 플러시
            Member2 member5 = new Member2(200L, "member200");
            em.persist(member5);
            em.flush(); // 이 떄 insert 쿼리 호출, 즉 바로 DB에 반영되버림.

            // 준영속
            Member2 member6 = em.find(Member2.class, 150L);
            member6.setName("AAAA");

            em.detach(member6); // JPA에서 더이상 관리하지 않는다.
            em.clear(); // 영속성 컨텍스트 초기화
        }
        catch(Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }
        emf.close();


    }
}
