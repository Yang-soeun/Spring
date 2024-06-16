package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {
    private Long MemberId;
    private String username;
    private int age;
    private Long teamId;
    private String teamName;

    @QueryProjection    //단점 dto가 순수하지 않가 querydsl 라이브러리에 의존하게 된다
    public MemberTeamDto(Long memberId, String username, int age, Long teamId, String teamName) {
        MemberId = memberId;
        this.username = username;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
