package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
		System.out.println("dfdfd");
		StudentVO vo = new StudentVO();
		StudentService svc = new StudentServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pass = req.getParameter("password");
		String birth = req.getParameter("birthday");
		vo.setStudentId(id);
		vo.setStudentName(name);
		vo.setStudentPassword(pass);
		try {
			vo.setStudentBirthday(sdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		if (svc.editStudent(vo)) {
			System.out.println("update ok");
			map.put("retCode", "OK");
			map.put("vo", vo);//ok가 되면 vo(전체정보)도 같이 넘기겠다
		} else {
			map.put("retCode", "NG");
		}
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(map);
		PrintWriter out = resp.getWriter();
		out.println(json);

	}

}