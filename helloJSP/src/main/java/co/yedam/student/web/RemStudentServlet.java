package co.yedam.student.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.student.service.StudentService;
import co.yedam.student.serviceImpl.StudentServiceImpl;

//학생한명 삭제
@WebServlet({"/RemStudentServlet.do","/delStudent.do"}) // 두개 주소 중에 아무거나 쳐도 이 서블릿을 불러온다는 의미임
public class RemStudentServlet extends HttpServlet{
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		StudentService svc = new StudentServiceImpl();
		
		if(svc.removeStudent(sid)) {
			//{"retCode" : "OK"}
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		}else {
			//{"retCode" : "NG"} 
			resp.getWriter().print("{\"retCode\" : \"NG\"} ");
		}
	}
	
	
	
	
	
	
	
	
	
	
} //RemStudentServlet
