package jpabook.jpabook.repository;

import jpabook.jpabook.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.reflect.InterTypeMethodDeclaration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        //저장
        if(item.getId() == null){//새로 생성하는 경우
            em.persist(item);
        }else{//업데이트라고 이해
            em.merge(item);
        }
    }

    //단건 조회
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    //여러개 조회
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
