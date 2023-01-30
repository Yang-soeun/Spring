package com.cos.jwt.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//스프링 시큐리티에서 UsernamePasswordAuthenticationFilter
// /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 이 필터가 동작을 함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");

        //1. username, password 받아서
        try {
            //확인
//            BufferedReader br = request.getReader();
//            String input = null;
//
//            while((input = br.readLine()) != null)
//                System.out.println(input);

            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            //로그인 시도를 하기 위해 직접 토큰을 만듬
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            //로그인 시도
            //PrincipalDetailsService에 loadUserByUsername() 함수가 실행됨
            Authentication authentication = authenticationManager.authenticate(authenticationToken);//내가 로그인한 정보가 담김

            //authentication 객체가 session 영역에 저장됨
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();// 다운캐스팅
            System.out.println(principalDetails.getUser().getUsername());//출력이 되면 로그인이 정상적으로 되었다는것을 알 수 있음

            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("========================================");

        //2. 정상인지 로그인 시도를 해보는 것. authenticationManager로 로그인 시도를 하면 PrincipalDetailsService가 호출
        //그러면 loadUserByUsername() 함수가 실행됨
        //3. PrincipaDetails를 세션에 담고 이유: 세션에 담지 않으면 권한관리가 되지 않는다.
        //4. JWT 토큰을 만들어서 응답해주면 됨
        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
    // 여기서 JWT 토큰을 만들어서 request 요청한 사용자에세 JWT 토큰을 response 해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        System.out.println("인증완료");

        PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();

        //토큰 만들기 Hash 암호방식
        String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))//토큰 만료시간
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));

        response.addHeader("Authorization", "Bearer" +jwtToken);
    }
}
