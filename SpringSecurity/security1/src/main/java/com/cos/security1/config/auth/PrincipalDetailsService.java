package com.cos.security1.config.auth;

import com.cos.security1.Repository.UserRepository;
import com.cos.security1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 시큐리티 config에서 loginOricessingUrl("/login");
 * /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //시큐리티 session => Authentication => UserDetails
    //return을 하게되면 Authentication 내부에 USerDetails가 들어가고
    //시큐리티 session(내부에 Authentication(내부에 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null)
            return new PrincipalDetails(userEntity);

        return null;
    }
}
