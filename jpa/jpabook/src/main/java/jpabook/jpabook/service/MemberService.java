package jpabook.jpabook.service;

import jpabook.jpabook.domain.Member;
import jpabook.jpabook.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//자동으로 스프링 빈으로 등록
@Transactional(readOnly = true)//읽기 메소드에만 적용되도록 아닌 메소드에는 false로 넣기
@RequiredArgsConstructor//final만 가지고 생성
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired
    //스프링빈에 등록되어 있는 memberRepository를 주입해줌
    //생성자가 하나만 있는 경우 Autowired 안해도 됨 알아서 해줌
    /*
    @RequiredArgsConstructor가 생성해줌

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;//생성자로 주입
    }
    */

    //회원 가입
    @Transactional//기본적으로 트랜잭션안에서 이루어져야함
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원 방지
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        //중복회원검증
        List<Member> findMembers = memberRepository.findByName(member.getName());//데베에 유니크 제약조건으로 하는것이 더 안전함
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한건 조희
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
