package hello.core.member;

public interface MemberRepository {
    //기능
    void save(Member member);//회원 저장 기능

    Member findById(Long memberId);//회원의 아이디를 찾는 기능
}
