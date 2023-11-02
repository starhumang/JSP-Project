package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

@WebServlet("/editStudent.do")
public class ModStudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 -> 바뀐정보 OK/NG

		StudentVO vo = new StudentVO();
		StudentService svc = new StudentServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		req.setCharacterEncoding("utf-8");

		String name = req.getParameter("name");
		String pass = req.getParameter("password");
		String birth = req.getParameter("birthday");

		vo.setStudentName(name);
		vo.setStudentPassword(pass);
		try {
			vo.setStudentBirthday(sdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		if (svc.editStudent(vo)) {
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\" : \"NG\"} ");
		}
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(svc.editStudent(vo));
		PrintWriter out = resp.getWriter();
		out.println(json);

	}

}