package spring.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.servlet.domain.Member.MemberRepository;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		//서블릿으로만 구현하면 자바 코드로 다 구현해야 하기 때문에 작성이 매우 불편하다.
		PrintWriter w = response.getWriter();
		w.write("<!DOCTYPE html>\n" +
			"<html>\n" +
			"<head>\n" +
			" <meta charset=\"UTF-8\">\n" +
			" <title>Title</title>\n" +
			"</head>\n" +
			"<body>\n" +
			"<form action=\"/servlet/members/save\" method=\"post\">\n" +
			" username: <input type=\"text\" name=\"username\" />\n" +
			" age: <input type=\"text\" name=\"age\" />\n" +
			" <button type=\"submit\">전송</button>\n" +
			"</form>\n" +
			"</body>\n" +
			"</html>\n");
	}
}
