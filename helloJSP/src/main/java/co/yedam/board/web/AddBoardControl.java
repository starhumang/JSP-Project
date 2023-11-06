package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class AddBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		BoardVO vo = new BoardVO();
		
		
		if (req.getMethod().equals("GET")) {

			// 제목, 내용, 작성자.
			String title = req.getParameter("title");
			String writer = req.getParameter("writer");
			String content = req.getParameter("content");

			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriter(writer);


		} else if (req.getMethod().equals("POST")) {
			//가장쉽게 파일 업로드할 수 있는 방법
			String saveDir = req.getServletContext().getRealPath("image");
			int size = 5 * 1024 *1024;
			//MultipartRequest mr;
			try {
				MultipartRequest mr = //
				new MultipartRequest(req,//요청정보
						saveDir,//저장경로
						size,//최대업로드 크기
						"UTF-8",//encoding
						new DefaultFileRenamePolicy());//리네임정책
				String title = mr.getParameter("title");
				String writer = mr.getParameter("writer");
				String content = mr.getParameter("content");
				String img = mr.getFilesystemName("img");
				
				vo = new BoardVO();
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setImage(img);
				vo.setContent(content);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// end of if
		
		
		
		BoardService svc = new BoardServiceImpl();
		if (svc.addBoard(vo)) {
			try {// 목록화면으로
				resp.sendRedirect("boardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {// 에러가 나면 입력페이지 다시
			try {
				resp.sendRedirect("boardForm.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}//end of execute

}
