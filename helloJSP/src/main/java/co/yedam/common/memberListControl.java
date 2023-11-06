package co.yedam.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.MemberVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;

public class memberListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		BoardService svc = new BoardServiceImpl();
		
		List<MemberVO> list = svc.memberList();
		
		req.setAttribute("list", list);
		String path = "WEB-INF/main/memberList.jsp";
		
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("logId");
		
		for (MemberVO vo : list) {
			if(vo.getMid().equals(userId) && vo.getRespon().equals("Admin")){
				try {
					//왜 아래의 것을 적는가
					req.getRequestDispatcher(path).forward(req, resp);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
					resp.sendRedirect("main.do");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		

	}

}
