package co.yedam.board.web;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class BoardListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//서블릿, jsp: 서블릿(컨트롤역할 : 데이터처리) -> jsp(뷰)
		BoardService svc = new BoardServiceImpl();
		List<BoardVO> list = svc.boardList();//현재 list에 전체 내용이 다 들어가 있음
		
		req.setAttribute("list", list);
		
		// 페이지요청(boardList.do) -> 다른페이지로 재지정(board/boardList.jsp)
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/board/boardList.jsp");//.forward(req, resp);//서블릿페이지에서 다른 정보로 이동할 페이지를 뭐시기.
		//예외처리가 들어가야하는 이유? 뭔데??
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
