package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.web.BoardListControl;
import co.yedam.board.web.GetBoardControl;
//모든 요청, 실행은 여기서
public class FrontController extends HttpServlet{
	
	// init -> service
	Map<String, Command> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		map.put("/boardList.do", new BoardListControl());
		map.put("/getBoard.do", new GetBoardControl());
		
		
		//아래는 연습용
//		map.put("/FirstServlet.do", new FirstControl());
//		map.put("/second.do", new SecondControl());
	}
	
	
	
	//항상 실행되는 서블릿
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FrontController");
		//사용자가 어떤 페이지를 요청했는지 보여주는 것
		String uri = req.getRequestURI();// http://localhost:8080/helloJSP/??.do
		String context = req.getServletContext().getContextPath(); //helloJSP 위의 주소에서 현 프로젝트 이름을 가져와 줌.
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command controller = map.get(page);//controller실제 구현한 뭐시기의 부모..
		controller.execute(req, resp);
		
	}
}
