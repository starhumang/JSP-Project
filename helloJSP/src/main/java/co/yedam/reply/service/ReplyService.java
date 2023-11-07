package co.yedam.reply.service;

import java.util.List;

public interface ReplyService {
	//목록, 단건, 조회
	public List<ReplyVO> replyList(int BoardNo);// 댓글 목록
	public ReplyVO getReply(int replyNo);//단건조회
	public boolean addReply(ReplyVO vo);//등록
	public boolean editReply(ReplyVO vo);//수정
	public boolean delReply(int replyNo);//삭제
	
	
	//조회 후 바로 등록 등과 같은 이중기능을 위해 ReplyMapper와 ReplyService가 비슷한 모양을 하고 있다.
	//ReplyMapper(select, insert, update, delete) -> Replysevice(ex(select + 기능 추가))
	
}
