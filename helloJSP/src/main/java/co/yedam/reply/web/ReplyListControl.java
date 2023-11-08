package co.yedam.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Command;
import co.yedam.common.PageDTO;
import co.yedam.reply.service.ReplyService;
import co.yedam.reply.service.ReplyVO;
import co.yedam.reply.serviceImpl.ReplyServiceImpl;

public class ReplyListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");
		System.out.println(page);//page = null임 문제...
		page = (page == null)? "1" : page;//페이지가 널이면 1, 널이 아니면 page그대로
		
		
		//페이징 계산.
		ReplyService svc = new ReplyServiceImpl();
		PageDTO dto = new PageDTO(Integer.parseInt(bno),//
						svc.getTotalCnt(Integer.parseInt(bno)),//
						Integer.parseInt(page));
		if(Integer.parseInt(page) > dto.getCurrentPage()) {
			page = String.valueOf(dto.getCurrentPage());
		}
		List<ReplyVO> list = svc.replyList(Integer.parseInt(bno), Integer.parseInt(page));
		System.out.println(list);
		//list랑 dto같이 넘기기
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("dto", dto);
		
		
//		System.out.println("bno : " + bno);	
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(map);
		resp.setContentType("text/json;charset=utf-8");
		
		try {
			resp.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
