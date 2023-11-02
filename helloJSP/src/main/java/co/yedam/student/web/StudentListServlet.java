package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

//학생전체 조회
@WebServlet("/studentList.do") // 이 주소 치면 실행됨.
public class StudentListServlet extends HttpServlet {

//생명주기 : init -> service -> destroy 
	//생성자
	public StudentListServlet() {
		System.out.println("생성자 call");
	}

	//메소드
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init call");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//★★ studentList.do?name=Hong&age=20  
		String name = req.getParameter("name"); // => name에 담긴 파라매터 값 읽어들이겠다. - getParameter
		String age = req.getParameter("age"); // request가 읽어들이는 값은 무조건 string! 
		
		System.out.println(name + "&" + age);
		System.out.println("service call");
		
		
		//응답정보의 컨텐트타입, 인코딩처리.
		resp.setCharacterEncoding("utf-8"); 
		resp.setContentType("text/json;charset=utf-8"); //페이지에보여주는 포맷 지정 
		
		//학생정보 json 전송.
		StudentService svc = new StudentServiceImpl();
		List<StudentVO> list = svc.listStudent();
		//자바 객체를 json 문자열로 변경하기.
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(list); // list라는 자바객체를 json 문자열로 바꿔줍니당...
		

		PrintWriter out = resp.getWriter(); // 클라이언트에 연결된 스트림에 출력되도록함. - getWriter
		out.println(json);
		
	}

	@Override
	public void destroy(){
		System.out.println("destroy call");
	}
	
	//java > webapp 밑에 studnet 만들고 student.info html 만들러갔음.
	
} // StudentListServlet
