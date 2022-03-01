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
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member" + i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//
//            em.flush();
//            em.clear();
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }
//            /**
//             * 조인:
//             * 내부조인
//             * - select m from Member m [INNER] JOIN m.team t
//             * 외부조인
//             * - select m from Member m LEFT [OUTER] JOIN m.team t
//             * 세타조인
//             * - select count(m) from Member m, Team t where m.username = t.name
//             */

//            /**
//             *  조건식 - CASE식
//             *  기본CASE, 단순CASE식
//             *  COALESCE : 하나씩 조회해서 null이 아니면 반환
//             *  select coalesce(m.username, '이름 없는 회원') from Member m
//             *  NULLIF : 두 값이 같으면 null 반환, 다르면 첫번째 값 반환
//             *  select NULLIF(m.username, '관리자') from Member m
//             */
            // 사용자 정의 함수 호출 예시
//            String query =
//                    "select function('group_concat', m.username) from Member m";
//
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

            /**
             * 경로 표현식
             * 묵시적 조인 사용(X) 웬만하면 명시적 조인 사용하자!
             *
             * 명시적 조인 : join키워드 직접 사용
             *  select m from Member m join m.team t
             *
             * 묵시적 조인 : 경로 표현식에 의해 묵시적으로 SQL 발생(내부 조인만 가능)
             *  select m.team from Member m
             */

            /**
             * **실무에서 매우 중요**
             * JPQL - 페치조인(fetch join)
             * - SQL 조인 종류 X
             * - JPQL에서 성능 최적화를 위해 제공하는 기능
             * - 연관된 엔티티나 컬렉션을 SQL 한 번에 함께 조회하는 기능
             * - join fetch 명령어 사용
             * - 페치 조인 ::= [LEFT [OUTER] | INNER] JOIN FETCH 조인경로
             */
            // * 페치 조인 한계
            // 페치 조인 대상에는 별칭을 줄 수 없다.
            // 둘 이상의 컬렉션은 페치 조인 할 수 없다.
            // 컬렉션을 페치 조인하면 페이징 API(setFirstResult, setMaxResults)를 사용할 수 없다.
            // - 일대일, 다대일 같은 단일 값 연관 필드들은 페치 조인해도 페이징 가능
            // - 하이버네이트는 경고 로그를 남기고 메모리에서 페이징

            /*
                페치 조인의 특징과 한계 정리
                - 연관된 엔티티들을 SQL 한 번으로 조회 - 성능 최적화
                - 엔티티에 직접 적용하는 글로벌 로딩 전략보다 우선함
                    ex) @OneToMany(fetch = FetchType.LAZY) //글로벌 로딩 전략
                - 실무에서 글로벌 로딩 전략은 모두 지연 로딩
                - 최적화가 필요한 곳은 페치 조인 적용
                - 모든 것을 페치 조인으로 해결할 수 없음
                - 페치 조인은 객체 그래프를 유지할 때 사용하면 효과적
                - 여러 테이블을 조인해서 엔티티가 가진 모양이 아닌 전혀 다른 결과를 내야 하면,
                페치 조인보다는 일반조인을 사용하고 필요한 데이터들만 조회해서 DTO로 반환하는 것이 효과적
             */
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m from Member m";
            String query = "select m from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class)
                            .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
                // 회원1, 팀A를 영속성컨텍스트에 없기 때문에 쿼리를 날려서 영속성컨텍스트에 올리고 결과를 반환 SQL로 가져온다(영속성 컨텍스트에 없기 떄문에)
                // 회원2, 팀A를 영속성컨텍스트에서 가져옴
                // 회원3, 팀B가 영속성컨텍스트에 없기 때문에 쿼리를 날려서 영속성컨텍스트에 올리고 결과를 반환

                // 회원 100명 -> N + 1 문제가 발생  --> 그래서 fetch join을 사용
                // 한방 쿼리인 fetch join으로 가져오면 프록시가 아닌 진짜 데이터인 Team을 가져오기때문에
                // 쿼리가 한 번으로 모두 조회할 수 있게 된다
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

