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
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
//
//            //TypedQuery : 반환타입이 명활할 떄 사용 ex) query1 - Member.class, query2 - String
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            //Query : 반환타입이 명확하지 않을 떄 사용 ex) query3 - String, int
//            Query query3 = em.createQuery("select m.username, m.age from Member m");
//
//            //query.getResultList() : 결과가 하나 이상일 떄, 리스트 반환
//            //결과가 없으면 빈 리스트 반환
//            //query.getSingleResult() : 결과가 정확히 하나, 단일 객체 반환
//            //결과가 없으면 : NOResultException
//            //결과가 둘 이상이면 : NonUniqueExceResultption
//
//            //파라미터 바인딩 - 이름기준, 위치기준  BUT 위치기준으로는 쓰지말고 이름기준으로만 사용하자
//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//            System.out.println("result = " + result);

            /**
             * 프로젝션 - select절에 조회할 대상을 정하는 것
             * 프로젝션 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입)
             */
            /*
                select m from Member m - 엔티티 프로젝션
                select m.team from Member m - 엔티티 프로젝션(Member에 연관된 team을 가져온다, 결과가 team)
                select m.address from Member m - 임베디드 타입 프로젝션
                select m.username, m.age from Member m - 스칼라 타입 프로젝션
                DISTINCT로 중복 제거
             */
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);

//            //엔티티 프로젝션을 한 List에 반환되는 Member대상들은 모두 영속성컨텍스트에서 다 관리된다
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//
//            //그래서 거기있는 것들 바꾸면 다 정상적으로 반영된다
//            Member findMember = result.get(0);
//            findMember.setAge(20);

            /**
             * 프로젝션 - 여러 값 조회
             * 1. Query 타입으로 조회
             * 2. Object[] 타입으로 조회
             * 3. new 명령어로 조회
             *  - 단순값을 DTO로 바로 조회
             *    select new jpabook.jpql.UserDTO(m.username, m.age) from Member m
             *  - 패키지 명을 포함한 전체 클래스 명 입력
             *  - 순서와 타입이 일치하는 생성자 필요
             */
            // 1. Query 타입으로 조회
//            List resultList = em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username = " +result[0]);
//            System.out.println("age = " +result[1]);

            // 2. Object[] 타입으로 조회
//            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//
//            Object[] result = resultList.get(0);
//            System.out.println("username = " +result[0]);
//            System.out.println("age = " +result[1]);

            // 3. new 명령어로 조회
//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("username = " + memberDTO.getUsername());
//            System.out.println("age = " + memberDTO.getAge());
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }


            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

