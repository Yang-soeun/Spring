package com.cos.security1.config.auth;

import com.cos.security1.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 /login 주소 요청이 오면 로그인을 진행
 * 로그인 진행이 완료가 되면 시큐리티 session을 만들어준다.
 * (Security ContextHolder에 session정보를 저장)
 * 시큐리티 session에 저장될 수 있는 object는 정해져있음
 * Obecject type => Authentication type 객체
 * Authentication 안에 User 정보가 있어야 함
 * User 오브젝트 타입 => UserDetails 타입 객체
 *
 * Security Session => Authentication => UserDetails
 * 세션정보를 꺼내면 Ahtentication 객체가 나오고 이 안에서 UserDetails 객체를 꺼내면 User Object에 접근할 수 있다.
 */
public class PrincipalDetails implements UserDetails {

    private User user;//컴포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {//만료
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//비밀번호의 기한이 자났는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //사용하는 경우
        //1년동안 회원이 로그인을 안하면 휴면 계정으로 하기로 함
        //현재시간 - 로그인 시간 => 1년을 초과하면 return false
        return true;
    }
}
