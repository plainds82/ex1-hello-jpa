package hello.jpa;

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
            // code
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
            Member findMember = em.find(Member.class, 1L);

            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member.getName() = " + member.getName());
            }

            // 수정
//            findMember.setName("HelloJPA");

            // 등록
//            em.persist(findMember);

            // 삭제제
//           em.remove(findMember);

            tx.commit();
        } catch (Exception exception) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

