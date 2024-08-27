package spring.servlet.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
	@Override //서블릿이 호출되면 service 메서드가 호출된다.
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);

		String username = request.getParameter("username");
		System.out.println("username = " + username);

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("hello " + username);	//바디에 데이터 넣기
	}
}
