package jpabook.jpabook.domain.item;

import jpabook.jpabook.domain.Category;
import jpabook.jpabook.exception.NotEnoughStockException;
import lombok.Getter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //엔티티 안에 있는 데이터를 이용할때는 비즈니스 로직을 엔티티 안에 하는것이 좋다.
    //==비즈니스 로직==/
    //재고 증가
    public void addStock(int quantity){
        this.stockQuantity +=quantity;
    }

    //재고 줄이기
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock <0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
