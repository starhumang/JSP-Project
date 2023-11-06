package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class ModifyBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// 1.파라미터활용 -> 2. 데이터수정 -> 목록이동.
		
		//1. 파라미터 활용(가져오기)
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		String bno =req.getParameter("bno");
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		BoardService svc = new BoardServiceImpl();
		//2. 데이터 수정
		if(svc.editBoard(vo)) {// 안의 메소드가 정상적으로 실행되면
			try {
				resp.sendRedirect("boardList.do"); // 3.목록화면으로 보내지고
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {//실행되지 않으면
			try {
				resp.sendRedirect("modifyForm.do");// 입력페이지를 다시 열기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}// end execute

}
