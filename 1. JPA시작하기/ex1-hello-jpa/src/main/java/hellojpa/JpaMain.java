package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);

            // 검색
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            // 삭제
            Member findMember2 = em.find(Member.class, 1L);
            em.remove(findMember2);

            // 수정
            Member findMember3 = em.find(Member.class, 1L);
            findMember3.setName("HelloJPA");

            // 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList(); // 멤버 객체 대상으로 쿼리
            for(Member m : result) {
                System.out.println("member.name: " + m.getName());
            }
            // 조회 - 페이징
            List<Member> result2 = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList(); // 멤버 객체 대상으로 쿼리

            tx.commit();

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
