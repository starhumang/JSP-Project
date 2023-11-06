package co.yedam.board.serviceImpl;

import java.util.List;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.service.MemberVO;

public class BoardServiceImpl implements BoardService {
	BoardDAO dao = new BoardDAO();
	
	@Override
	public List<BoardVO> boardList() {

		return dao.selectList();
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		dao.updateCnt(boardNo);//여기서 수량 1씩 증가
		return dao.select(boardNo);
	}

	@Override
	public boolean addBoard(BoardVO vo) {
		
		return dao.insert(vo) == 1;
	}

	@Override
	public boolean editBoard(BoardVO vo) {
	
		return dao.update(vo) == 1;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		
		return dao.delete(boardNo) == 1;
	}
	
	@Override
	public boolean loginCheck(String id, String pw) {
		return dao.getUser(id, pw);
	}
	@Override
	public List<MemberVO> memberList() {
		// TODO Auto-generated method stub
		return dao.selectMemList();
}


}
