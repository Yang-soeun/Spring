package jpabook.jpabook.repository;

import jpabook.jpabook.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository//컴포넌트 스캔해서 자동적으로 스프링 빈으로 관리
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    //저장하는 로직
    public void save(Member member){
        em.persist(member);//영속성 컨텍스트에 멤버 객체를 올림
    }

    //조회하는 로직(단건 조희)
    public Member findOne(Long id){
        return em.find(Member.class, id);//타입, pk
    }

    public List<Member> findAll(){
        //jpql
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름으로 조회!! 파라미터 바인딩
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
