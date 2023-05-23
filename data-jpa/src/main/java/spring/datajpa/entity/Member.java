package spring.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})//객체를 출력할때, 연관관계 필드는 적으면 안됨!!
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    //JPA에서 ManyToOne에 대한 fetch는 EAGER 이므로 LAZY로 변경해야함
    //JPA에서 모든 연관관계는 지연로딩으로 setting 해야함 -> 성능최적화 하기가 쉬움
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")//FK 명이된다.
    private Team team;

    //jpa는 기본적으로 파라미터가 없는 생성자가 필요함(표준스펙)
    //private 만들면 안됨 -> 프록싱 기술을 사용할때 구현체들이 막힐 수 있음(?)
//    protected Member() {//jpa 때문에 기본 생성자가 필요함, 아무데나 호출할 수 없도록 protected
//    } -> @NoArgsConstructor(access = AccessLevel.PROTECTED)

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;

        if(team != null){//null이여도 그냥 무시하고 setting
            changeTeam(team);
        }
    }

    //연관관계를 setting하는 method
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
