package spring.datajpa.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass//진짜 상속이 아니라 속성만 내려서 사용할 수 있도록
@Getter @Setter
public class JpaBaseEntity {

    @Column(updatable = false)//createdDate는 변경하지 못하도록 설정
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist(){ //저장하기 전에 이벤트 발생
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    public void preUpdate(){//업데이트가 있으면 갱신
        updatedDate = LocalDateTime.now();
    }
}
