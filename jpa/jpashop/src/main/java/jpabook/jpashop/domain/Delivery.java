package jpabook.jpashop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
   
    //양방향
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
}