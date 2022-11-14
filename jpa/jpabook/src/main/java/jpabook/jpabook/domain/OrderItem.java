package jpabook.jpabook.domain;

import jpabook.jpabook.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice;//주문가격
    private int count;//주문 수량

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);//재고를 줄여줘야함

        return orderItem;
    }
    //비즈니스 로직
    //주문취소
    public void cancel() {
        getItem().addStock(count);//취소를 했으므로 주문 수량만큼 다시 늘려줘야함
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
