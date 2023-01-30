package com.cos.jwt.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

public class MyFilter3 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /**
         * 토큰 이름: cos
         * id, pwd가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 응답해준다.
         * 요청을 할때마다 header에 Authorization에 value값으로 토큰을 가지고 옴
         * 그때 토큰이 넘어오면 이 토큰이 내가 맞는 토큰이 맞는지만 검증하면 됨.(RSA, HS256)
         */
        //POST일때만 동작
        if(req.getMethod().equals("POST")){
            out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            out.println("headerAuth = " + headerAuth);
            out.println("필터3");

            if(headerAuth.equals("cos")){
                chain.doFilter(req, res);
            }else{
                PrintWriter out = res.getWriter();
                out.println("인증안됨");
            }
        }
    }
}
