package spring.datajpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.datajpa.dto.MemberDto;
import spring.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;

/**
 * 구현체가 없는 동작하는 이유: interface만 보고 스프링 데이터 jpa가 구현체(프록시 객체(?))를 만들어서 injection 해줌
 * @Repository 생략가능
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    //리포지토리 메소드에 쿼리 정의하기
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    //@Query, 값, DTO 조회하기기
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //Dto 조회하기
    //Dto로 조회할때는 new operation을 적어줘야함
    @Query("select new spring.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    //파라미터 바인딩 - 위치기반은 사용하지말고, 이름기반으로 사용하기
    //컬렉션
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);
    //사용방법: memberRepository.findByNames(Arrays.asList("AAA", "BBB"));

    //벌크성 수정 쿼리
    //clearAutomatically = true -> clear 과정을 자동으로 해줌
    @Modifying(clearAutomatically = true)//해줘야함, jpa의 excuteUpdate와 같은 동작을 함, 안하면 에러남
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    //패치 조인 -> Member를 조회할때 연관된 team도 같이 한번에 조회해옴
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    //data jpa fetch join 방법
    @Override
    @EntityGraph(attributePaths = {"team"})//멤버를 조회할때 같이 조회하고 싶은것도 적어주면 됨
    List<Member> findAll();

    //jpql에도 fetch join 가능
    //쿼리를 작성하고 fetch join 부분만 추가하기기
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    //method 이름으로 하는 fetch join
    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);
}