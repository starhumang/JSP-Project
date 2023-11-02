package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

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

//학생 한명 추가
@WebServlet("/addStudent.do")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//기본세팅
		StudentVO vo = new StudentVO();
		StudentService svc = new StudentServiceImpl();
	
		//내가 적은 정보를 인코딩처리
		req.setCharacterEncoding("utf-8");		
		
		//(우리가 입력한 값)
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pass = req.getParameter("password");
		String dept = req.getParameter("dept");
		String birth = req.getParameter("birthday");
		System.out.println(id);
		System.out.println(name);
		System.out.println(pass);
		System.out.println(dept);
		System.out.println(birth);
		
		
	
				
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // simpledateformat : 데이트타입을 문자열로 바꾸어줄때
		//(아무것도 없는 vo에 값 넣어주기)
		System.out.println(vo.getStudentId());
		
		vo.setStudentId(id);
		vo.setStudentName(name);
		vo.setStudentPassword(pass);
		vo.setStudentDept(dept);
		try {//birth : string타입, setStudentBirthday() : date타입
			vo.setStudentBirthday(sdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		System.out.println(svc.addStudent(vo)); true;
		if(svc.addStudent(vo)) {
			//{"retCode" : "OK"}
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		}else {
			//{"retCode" : "NG"} 
			resp.getWriter().print("{\"retCode\" : \"NG\"} ");
		}
		
		
		
		
		//json 문자열로 변경하기
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(svc.addStudent(vo));

		//getWriter : 클라이언트에 연결된 스트림에 출력되도록 함
		PrintWriter out = resp.getWriter();
		out.println(json);
		
	
		
		
		
		
		
	}
}