package co.yedam.board.service;

import java.util.List;

public interface BoardService {
	//목록, 단건조회, 등록, 수정, 삭제:
	public List<BoardVO> boardList();
	public BoardVO getBoard(int boardNo);//책번호 적으면 전체 내용 가져욤
	public boolean addBoard(BoardVO vo); //true or false 값으로 가져옴
	public boolean editBoard(BoardVO vo); //true or false 값으로 가져옴
	public boolean removeBoard(int boardNo);//책번호 적으면 해당 내용 삭제
	
	
	//로그인처리.
	public MemberVO loginCheck(String id, String pw);
	
	//관리자 목록보기
	public List<MemberVO> memberList();
	
}
