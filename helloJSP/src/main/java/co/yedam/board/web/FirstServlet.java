package co.yedam.board.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
//@WebServlet("/FirstServlet.do")
public class FirstServlet extends HttpServlet {
	//init ->service -> destroy.
	
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("service 실행");
			//서비스라는 메소드가 있으면 얘만 요청. 서비스 없고 doget만 있으면 doget이 실행되어 밑의 정보가 html창에 뜸
			//따라서 둘 다 요청하고 싶으면 service에 doGet을 요청하면 됨
			doGet(req,resp);
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String name = "홍길동";
		int age = 20;
		for(int i = 0; i <5; i++) {
			response.getWriter()//
			.print("<p>" + i +"번째 이름은" + name + "이고, 나이는 " + age + "입니다." );
		}//서블릿을 쓰면 위와같이 html도 직접 이렇게 적어줘야 하므로 자바스크립트와 html을 같이 사용이 가능한 jsp를 이용하자
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
