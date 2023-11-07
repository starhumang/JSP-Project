package co.yedam.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.board.service.BoardService;
import co.yedam.board.serviceImpl.BoardServiceImpl;

public class LoginControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		System.out.println("id= " + id + " pw= " + pw);

		// session : 서버-클라이언트(세션에 아이디 값을 담아놔서 창을 닫지 않는 한 계속 유지됨)

		BoardService svc = new BoardServiceImpl();
		if (svc.loginCheck(id,pw) != null) {
			HttpSession session = req.getSession();
			session.setAttribute("logId", id);
			try {
				resp.sendRedirect("boardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				resp.sendRedirect("loginForm.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
