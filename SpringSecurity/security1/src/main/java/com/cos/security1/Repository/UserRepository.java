package com.cos.security1.Repository;

import com.cos.security1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Type, PrimaryKey 자료형>
 * CRUD 함수를 JpaRepository가 들고있음
 * JpaRepository를 상속했기 때문에 @Repository 어노테이션이 없어도 IoC가 된다.(스프링 빈으로 등록된다.)
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username); //JPA Query Method

}
