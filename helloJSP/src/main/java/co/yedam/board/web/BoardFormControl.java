package co.yedam.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class BoardFormControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher("WEB-INF/board/boardForm.jsp").forward(req, resp);//위치 재지정
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

	}

}
