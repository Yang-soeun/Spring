package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터(SecurityConfig에 있는거)가 스프링 필터체인에 등록이 된다.
public class SecurityConfig {

    /**
     * 기존에는 WebSecurityConfigurerAdapter를 상속하고 configure 메소드를 오버라이딩하여 설정하는 방법
     * 위 방법 사용불가
     * 현재: SecurityFilterChain을 리턴하는 메소드를 Bean에 등록하는 방식(Component 방식으로 컨테이너가 관리)
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();//원래 있는 로그인, 로그아웃 비활성화
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()//인증필요
                .antMatchers("/manager/**").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")//access 권한이 있는사라만 가능하게 함
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                //권한이 없는 경우 로그인 전부 로그인 페이지로 이동
                .and()
                .formLogin()
                .loginPage("/loginForm");

        return http.build();
    }
}
