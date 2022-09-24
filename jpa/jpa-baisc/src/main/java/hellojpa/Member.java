package hellojpa;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {

    @Id//PK 알려주기
    //DB에 있는것것
    private Long id;
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
