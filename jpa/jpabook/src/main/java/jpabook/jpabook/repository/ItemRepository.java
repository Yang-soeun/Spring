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
            em.merge(item);//merge 파라미터의 값으로 모든 데이터를 다 변경
            //변경감지를 사용하면 원하는 속성만 변경할 수 있지만
            //병합을 사용하면 모든 속성이 변경된다.(병합시 값이 없으면 null로 들어가기 때문에 위험)
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
