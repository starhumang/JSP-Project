package co.yedam.student.web;

import java.io.IOException;
import java.io.PrintWriter;
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

//학생한명 조회
@WebServlet("/getStudent.do")
public class GetStudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 기본세팅
		StudentService svc = new StudentServiceImpl();

		// 아래의 출력문 전체 인코딩 처리
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");

		// 내가 입력한 아이디 값
		String id = req.getParameter("id");

		// 내가 입력한 아이디를 vo의 id라고 하겠다.
		StudentVO vo = svc.getStudent(id);//getStudent()함수에 id만 넣으면 sql에서 정보를 불러와서 전체정보가 다 넘어온다.

		Map<String, Object> map = new HashMap<>();

		// 내가 입력한것을 json문자열로 변경하기
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		// 화면에 출력
		PrintWriter out = resp.getWriter();

		if (svc.getStudent(id) == null) {
			map.put("retCode", "NG");
		} else {
			map.put("retCode", "OK");
			map.put("vo", vo);//ok가 되면 vo(전체정보)도 같이 넘기겠다
		}

		String json = gson.toJson(map);
		out.println(json);
	}
}
