package hellojpa;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//name 기본값을 클래스 이름
//@Table
public class Member {

    @Id//PK 알려주기
    //DB에 있는것것
    private Long id;
    private String name;
    private int age;

    public Member(){
    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
