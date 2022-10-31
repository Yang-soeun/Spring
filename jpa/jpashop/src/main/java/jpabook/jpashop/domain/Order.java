package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id  @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) //ORDINAL을 사용하면 순서가 꼬여서 장애가 발생할 수 있으므로 STRING으로 해주기
    private OrderStatus status;
}
