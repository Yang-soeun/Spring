package spring.datajpa.repository;

import org.springframework.stereotype.Repository;
import spring.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }
    
    public void delete(Member member){
        em.remove(member);
    }
    
    public List<Member> findAll(){//전체 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }//단건 조회

    //순수한 jpa 조건에 맞는 회원의 나이를 한번에 증가하는 함수
    public int bulkAgePlus(int age){
        return em.createQuery(
                "update Member m set m.age = m.age + 1" +
                        " where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
    /**
     * 벌크연산을 하는 경우 영속성 컨텍스트에 저장되는 것이 아니라 db바로 저장이 되므로
     * 영속성 컨텍스트에는 기존의 값이 남아 있기 때문에 조회할 경우 결과가 다르게 된다.
     * 그래서 영속성 컨텍스트에 있는 값들을 다 지워줘야함
     * 날리는 방법: em.flush(); -> em.clear(); 두개 해주면 OK
     */
}
