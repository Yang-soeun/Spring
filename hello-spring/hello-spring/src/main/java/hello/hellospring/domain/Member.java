package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {//jpA가 관리하는 엔티티가 된다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 알아서 자동으로 생성
    //요구사항
    private Long id;//식별자 임의의 값
    //DB의 column명이 username이라면 -> @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
