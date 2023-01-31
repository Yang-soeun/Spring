package com.cos.jwt.auth;

import com.cos.jwt.model.User;
import com.cos.jwt.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//http://localhost:8080/login(로그인 요청이 올때)일때 동작을 함
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService: 진입");
        User userEntity = userRepository.findByUsername(username);

        return new PrincipalDetails(userEntity);
    }
}
