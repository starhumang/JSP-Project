package co.yedam.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yedam.board.service.BoardVO;
import co.yedam.board.service.MemberVO;
//public int(얘가 반환되는 값(resultType)) delete(int boardNo)(얘가 파라미터타입);
public interface BoardMapper {
	public List<BoardVO> selectList();
	public BoardVO select(int boardNo);
	public int updateCnt(int boardNo);
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(int boardNo);
	
	
	//로그인 관련.(파라미터 2개인 경우)
	public MemberVO getuser(@Param("id")String id, @Param("pw") String pw);
	
	//관리자 관련
	public List<MemberVO> selectMemList();
}
