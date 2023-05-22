package spring.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    //jpa는 기본적으로 파라미터가 없는 생성자가 필요함(표준스펙)
    //private 만들면 안됨 -> 프록싱 기술을 사용할때 구현체들이 막힐 수 있음(?)
    protected Member() {//jpa 때문에 기본 생성자가 필요함, 아무데나 호출할 수 없도록 protected
    }

    public Member(String username) {
        this.username = username;
    }
}
