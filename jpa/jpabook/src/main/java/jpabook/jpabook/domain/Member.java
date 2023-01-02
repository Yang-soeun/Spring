package jpabook.jpabook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;
    @Embedded
    private Address address;

    //@JsonIgnore//이 정보는 제외하고 json으로 뿌림
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
