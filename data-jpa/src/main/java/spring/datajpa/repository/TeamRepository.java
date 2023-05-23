package spring.datajpa.repository;

import org.apache.coyote.http2.HpackDecoder;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.datajpa.entity.Member;
import spring.datajpa.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    //쿼리 메소드 기능
    List<Member> findByUsername(String username);

    // And = and 조건
    // 필드명이 변경되면 반드시 인터페이스에 정의한 메서드 이름도 변경해야 함
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}
