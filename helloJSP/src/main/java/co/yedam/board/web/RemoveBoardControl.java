package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class RemoveBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String bno =req.getParameter("bno");
		
		
		BoardService svc = new BoardServiceImpl();
		//2. 데이터 수정
		if(svc.removeBoard(Integer.parseInt(bno))) {// 안의 메소드가 정상적으로 실행되면
			try {
				resp.sendRedirect("boardList.do"); // 3.목록화면으로 보내지고
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {//실행되지 않으면
			try {
				resp.sendRedirect("removeForm.do");// 입력페이지를 다시 열기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}

}
