package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.web.AddBoardControl;
import co.yedam.board.web.BoardFormControl;
import co.yedam.board.web.BoardListControl;
import co.yedam.board.web.GetBoardControl;
import co.yedam.board.web.ModifyBoardControl;
import co.yedam.board.web.ModifyFormControl;
import co.yedam.board.web.RemoveBoardControl;
import co.yedam.board.web.RemoveFormControl;
import co.yedam.reply.web.AddReplyControl;
import co.yedam.reply.web.RemoveReplyControl;
import co.yedam.reply.web.ReplyListControl;
//모든 요청, 실행은 여기서
public class FrontController extends HttpServlet{
	
	// init -> service
	Map<String, Command> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		//메인 페이지
		map.put("/main.do", new MainControl());
		
		//로그인&로그아웃
		map.put("/loginForm.do", new LoginFormControl());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
		
		//목록화면
		map.put("/boardList.do", new BoardListControl());
		map.put("/getBoard.do", new GetBoardControl());
		
		//등록화면
		map.put("/boardForm.do", new BoardFormControl());//화면만 열어준다
		map.put("/addBoard.do", new AddBoardControl());
		
		//수정화면
		map.put("/modifyForm.do", new ModifyFormControl());
		map.put("/modifyBoard.do", new ModifyBoardControl());
		
		//삭제화면
		map.put("/removeForm.do", new RemoveFormControl());
		map.put("/removeBoard.do", new RemoveBoardControl());
		System.out.println("init_");
		
		//관리자 유저목록 조회
		map.put("/memberList.do", new memberListControl());
		
		
		//댓글목록
		map.put("/replyList.do", new ReplyListControl());
		map.put("/addReply.do", new AddReplyControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		
		//차트.
		map.put("/chartForm.do", new ChartFormControl());//차트 보여주는 링크랑 폼 만드는 것
		map.put("/drawChart.do", new DrawChartControl());//실제 데이터 가져오는 것
		
		//아래는 연습용
//		map.put("/FirstServlet.do", new FirstControl());
//		map.put("/second.do", new SecondControl());
	}
	
	
	
	//항상 실행되는 서블릿
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청정보의 한글 인코딩 방식
		req.setCharacterEncoding("UTF-8");
//		System.out.println("FrontController");
		
		
		//사용자가 어떤 페이지를 요청했는지 보여주는 것
		String uri = req.getRequestURI();// http://localhost:8080/helloJSP/??.do
		String context = req.getServletContext().getContextPath(); //helloJSP 위의 주소에서 현 프로젝트 이름을 가져와 줌.
		String page = uri.substring(context.length());
		System.out.println("page : "+ page);
		Command controller = map.get(page);//controller실제 구현한 뭐시기의 부모..
		controller.execute(req, resp);
		
	}
}
