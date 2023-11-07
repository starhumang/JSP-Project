package co.yedam.reply.mapper;

import java.util.List;

import co.yedam.reply.service.ReplyVO;
//public int(얘가 반환되는 값(resultType)) delete(int boardNo)(얘가 파라미터타입);
public interface ReplyMapper {
	public List<ReplyVO> replyList(int boardNo);
}
