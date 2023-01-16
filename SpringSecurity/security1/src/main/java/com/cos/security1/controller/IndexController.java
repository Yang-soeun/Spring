package com.cos.security1.controller;

import com.cos.security1.Repository.UserRepository;
import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //일반 로그인
    @GetMapping("/test/login")
    public @ResponseBody String testLogin(
            Authentication authentication, //방법 1) 의존성 주입
            @AuthenticationPrincipal PrincipalDetails userDetails){//방법 2) 다운캐스팅
        System.out.println("/test/login ====================== ");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();//다운캐스팅
        System.out.println("authentication = " + principalDetails.getUser());

        System.out.println("userDetails = " + userDetails.getUsername());

        return "세션정보 확인하기";
    }

    //구글 로그인
    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(
            Authentication authentication,
            @AuthenticationPrincipal OAuth2User oauth){
        System.out.println("/test/oauth/login ====================== ");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();//다운캐스팅
        System.out.println("authentication = " + oAuth2User.getAttributes());//방법1
        System.out.println("oauth2User = " + oauth.getAttributes());//방법2

        return "OAuth 세션정보 확인하기";
    }

    @GetMapping({"", "/"})
    public String index(){

        //머스테치 기본폴더 src/main/resources/
        // view resolver 설정: templates (prefix), .mustache(suffix)로 설정 -> 생략 가능
        return "index";
    }

    /**
     * OAuth(지금은 구글 로그인)로 로그인을 해도 PrincipalDetails
     * 일반 로그인도 PrincipalDetails
     */
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails.getUser() = " + principalDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    @GetMapping("/loginForm")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user){
        //회원가입
        //롤을 지정
        user.setRole("ROLE_USER");
        //패스워드 암호화
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";//회원가입이 정상적으로 완료가 된다면
    }

    @Secured("ROLE_ADMIN")//ADMIN 권한만 접근가능
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }


    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")//data 메소드가 실행되기 직전에 실행행
    @GetMapping("/data")
    public @ResponseBody String  data(){
        return "데이터정보";
    }
}
