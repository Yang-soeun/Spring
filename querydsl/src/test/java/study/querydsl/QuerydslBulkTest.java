package study.querydsl;

import static study.querydsl.entity.QMember.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

@SpringBootTest
@Transactional
public class QuerydslBulkTest {
    @PersistenceContext EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
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

    /**
     * 벌크 연산을 하면 영속성 컨텍스트의 값을 바꾸는게 아니라 바로 DB에 업데이트 되기 때문에
     * 둘의 값(상태)이 다르게 된다.
     * select해서 조회를 한다면 바뀌기 전 상태가 나온다(영속성 컨텍스트가 우선권을 가짐)
     * repeatable read
     */
    @Test
    public void bulkUpdate(){
        //영향을 받은 row 수가 나옴
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))   //lt: 미만
                .execute();

        //해결 방법
        em.flush();
        em.clear();
    }

    @Test
    public void bulkAdd(){
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
    }

    @Test
    public void bulkDelete(){
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }
}
