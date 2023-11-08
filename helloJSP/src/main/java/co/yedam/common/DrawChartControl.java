package co.yedam.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.reply.service.ReplyService;
import co.yedam.reply.serviceImpl.ReplyServiceImpl;

public class DrawChartControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//json 데이터를 넘기고 => [작성자, 건수] 이런 배열형태의 데이터를 받아서 차트 만들기
		ReplyService svc = new ReplyServiceImpl();
		List<Map<String, Object>> list = svc.getReplyCountByWriter();
		
		Gson gson = new GsonBuilder().create();
		
		try {
			resp.getWriter().print(gson.toJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
