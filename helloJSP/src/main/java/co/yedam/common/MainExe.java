package co.yedam.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import co.yedam.reply.mapper.ReplyMapper;
import co.yedam.reply.service.ReplyVO;

public class MainExe {
	public static void main(String[] args) {
		SqlSession session = //
				DataSourceMybatis.getInstance().openSession(true);
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		
		List<ReplyVO> list = mapper.replyList(2,1); //2번글에 첫번째 답글 보겠습니다.
		list.forEach(vo -> System.out.println(vo));
		
		List<Map<String, Object>> map = mapper.getReplyCountByWriter();
		System.out.println(map);
		
		
	
		
		
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	//학생아이디, 비밀번호, 이름, 학과, 생일
//	
//	StudentVO vo = new StudentVO();
//	vo.setStudentId("newbie");
//	vo.setStudentName("신입생");
//	vo.setStudentPassword("1234");
//	vo.setStudentDept("영문학과");
//	
//	try {
//		vo.setStudentBirthday(sdf.parse("2001-01-01"));
//	}catch(ParseException e){
//		e.printStackTrace();
//	}
//	
//	StudentService svc = new StudentServiceImpl();
//	if(svc.addStudent(vo)) {
//		System.out.println("성공");
//	}else {
//		System.out.println("에러");
//	}
//	
//	//전체조회
//	svc.listStudent()
//		.forEach(student -> System.out.println(student));
	
		
	} // void main
} //class MainEXE
