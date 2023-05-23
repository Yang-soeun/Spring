package spring.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter //setter 사용보다는 생성자를 만들어서 사용하는것이 더 좋음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") //FK 없는쪽에 걸기
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
