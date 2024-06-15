package study.querydsl;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

@Transactional
@SpringBootTest
public class QuerydslDynamicQueryTest {
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

    @Test
    public void dynamicQuery_BooleanBuilder(){
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();
//        BooleanBuilder builder = new BooleanBuilder(member.username.eq(usernameCond)); //필수인 경우 초기값 넣을 수 있음
        if(usernameCond != null){
            builder.and(member.username.eq(usernameCond));
        }
        if(ageCond != null){
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    /**
     * 실무에서 많이 사용하는 방식 -> Where 다중 파라미터 사용
     */
    @Test
    public void dynamicQuery_WhereParam(){
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
                .where(usernameEq(usernameCond), ageEq(ageCond))
                .fetch();
    }

    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond != null ? member.username.eq(usernameCond) : null;
    }
    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

//    private Predicate allEq(String usernameCond, Integer ageCond){
//        return usernameEq(usernameCond).and(ageEq(ageCond));
//    }
}
