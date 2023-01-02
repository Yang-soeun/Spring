package jpabook.jpabook.api;

import jpabook.jpabook.domain.Member;
import jpabook.jpabook.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController //@Controller @ResponseBody 합친거
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    //!!!!! api 요청 스펙에 맞춰서 별도의 dto를 만들어서 파라미터로 사용하는 것이 좋음 -> 엔티티를 그냥 파라미터로 받지 않기 !!!!!

    @GetMapping("/api/v1/members")//회원 조회 api
    public List<Member> membersV1() {
        //문제점: 엔티티에 있는 모든 정보들이 노출된다.
        //엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
        //응답 스펙을 맞추기 위해 로직이 추가된다.(@JsonIgnore, 별도의 뷰 로직 등)
        //엔티티가 변경되면 API 스펙이 변경된다.

        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2() {
        List<Member> findMembers = memberService.findMembers();
        
        //Dto로 변경하기
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))//멤버 엔티티에서 이름을 꺼내와서 dto에 넣고
                .collect(Collectors.toList());//리스트로 변경하기

        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{//이렇게 감싸줘야함
        //private int count;//이런식으로 count 필드 추가 가능.
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        //api스펙과 동일해야 유지보수가 편리함
        private String name;//노출할것만 노출
    }

    @PostMapping("/api/v1/members")//회원 등록 api
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        //프레젠테이션 계층에 대한 검증이 엔티티 계층에 들어가서 좋지 않음
        //엔티티의 스팩(이름을 변경)을 바꾸면 api스팩도 바뀌게 된다는 문제점 발생 -> dto로 만들어서 하는것이 좋다
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")//수정
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);

        return new UpdateMemberResponse(findMember.getId(), findMember.getName());

    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }
    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;
    }

    //응답
    @Data
    static class CreateMemberResponse {
        private Long id;//아이디값 반환

        public CreateMemberResponse(Long id) {//생성자
            this.id = id;
        }
    }

}
