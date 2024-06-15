package study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

@Transactional
@SpringBootTest
public class QuerydslBasicTest {
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startQuerydsl(){
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))//파라미터 바인딩 처리
                .fetchOne();

        AssertionsForClassTypes.assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    /**
     * 제공하는 더 많은 검색 조건 쿼리는 강의자료 참고
     */
    @Test
    public void search(){
        Member fidnMember = queryFactory
                .selectFrom(member) //selectFrom으로 합치기 가능
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();

        AssertionsForClassTypes.assertThat(fidnMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchBetween(){
        Member fidnMember = queryFactory
                .selectFrom(member) //selectFrom으로 합치기 가능
                .where(member.username.eq("member1")
                        .and(member.age.between(10, 30)))
                .fetchOne();

        AssertionsForClassTypes.assertThat(fidnMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam(){
        Member fidnMember = queryFactory
                .selectFrom(member) //selectFrom으로 합치기 가능
                .where( //and -> , 로 가능
                        member.username.eq("member1")
                        ,member.age.eq(10))
                .fetchOne();

        AssertionsForClassTypes.assertThat(fidnMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch(){
        /**
         * 리스트 조회, 데이터 없으면 빈 리스트 반환
         */
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        /**
         * 단 건 조회
         * 결과가 없으면 null
         * 결과가 둘 이상이면 NonUniqueResultException
         */
        Member findMember1 = queryFactory
                .selectFrom(member)
                .fetchOne();

        /**
         * fetchFirst() == limit(1).fetchOne()
         */
        Member findMember2 = queryFactory
                .selectFrom(member)
                .fetchFirst();

        /**
         * 페이징에서 사용
         * 페이징 정보 포함, total count 쿼리 추가 실행
         * 아래 쿼리도 두개가 날라감
         */
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        results.getTotal();
        List<Member> content = results.getResults();

        /**
         * count 쿼리로 변경해서 count 수 조회
         */
        long count = queryFactory
                .selectFrom(member)
                .fetchCount();

    }

    @Test
    public void sort(){
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        assertThat(result).hasSize(3)
                .extracting("username")
                .containsExactly("member5", "member6", null);
    }
}
