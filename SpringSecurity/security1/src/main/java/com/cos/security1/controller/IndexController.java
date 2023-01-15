package com.cos.security1.controller;

import com.cos.security1.Repository.UserRepository;
import com.cos.security1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @GetMapping({"", "/"})
    public String index(){

        //머스테치 기본폴더 src/main/resources/
        // view resolver 설정: templates (prefix), .mustache(suffix)로 설정 -> 생략 가능
        return "index";
    }

    @GetMapping("/user")
    public String user(){
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
