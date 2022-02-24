package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //TypedQuery : 반환타입이 명활할 떄 사용 ex) query1 - Member.class, query2 - String
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            //Query : 반환타입이 명확하지 않을 떄 사용 ex) query3 - String, int
            Query query3 = em.createQuery("select m.username, m.age from Member m");

            //query.getResultList() : 결과가 하나 이상일 떄, 리스트 반환
            //결과가 없으면 빈 리스트 반환
            //query.getSingleResult() : 결과가 정확히 하나, 단일 객체 반환
            //결과가 없으면 : NOResultException
            //결과가 둘 이상이면 : NonUniqueExceResultption

            //파라미터 바인딩 - 이름기준, 위치기준  BUT 위치기준으로는 쓰지말고 이름기준으로만 사용하자
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("result = " + result);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

