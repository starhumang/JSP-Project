package co.yedam.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.board.service.BoardVO;
import co.yedam.board.service.MemberVO;
import co.yedam.common.DataSource;

public class BoardDAO {
	// 목록, 단건조회, 등록, 수정, 삭제:

	DataSource ds = DataSource.getInstance();
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> selectList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		sql = "select* from board order by board_no";
		conn = ds.getConnection();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setLastUpdate(rs.getDate("last_update"));
				vo.setTitle(rs.getString("title"));
				vo.setViewCnt(rs.getInt("view_cnt"));
				vo.setWriteDate(rs.getDate("write_Date"));
				vo.setWriter(rs.getString("writer"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public BoardVO select(int boardNo) {
		sql = "select * from board where board_no = ?";
		conn = ds.getConnection();
		BoardVO vo = new BoardVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			if (rs.next()) {

				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContent(rs.getString("content"));
				vo.setImage(rs.getString("image"));
				vo.setLastUpdate(rs.getDate("last_update"));
				vo.setTitle(rs.getString("title"));
				vo.setViewCnt(rs.getInt("view_cnt"));
				vo.setWriteDate(rs.getDate("write_Date"));
				vo.setWriter(rs.getString("writer"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	public int insert(BoardVO vo) {
		sql = "insert into board(board_no, title, content, writer, image) values(seq_board.nextval,?,?,?,?)";
		conn = ds.getConnection();
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setString(4, vo.getImage());
			r = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	public int update(BoardVO vo) {
		sql = "update board set content=?, image=nvl(?,image), last_update=sysdate, title=? where board_no=?";
		conn = ds.getConnection();
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());
			psmt.setString(2, vo.getImage());
			psmt.setString(3, vo.getTitle());
			psmt.setInt(4, vo.getBoardNo());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	public int delete(int num) {
		sql = "delete from board where board_no =?";
		int r = 0;
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			r = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	// 조회수 증가
	public int updateCnt(int boardNo) {
		sql = "update board set view_cnt=view_cnt+1 where board_no=?";
		int r = 0;
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			r = psmt.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}

	// 아이디랑 비번을 받아 조회값을 boolean으로
	public boolean getUser(String id, String pw) {
		System.out.println(id+","+pw);
		sql = "SELECT* FROM MEMBER WHERE MID=? AND PASS=?";
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			boolean result = rs.next();
			System.out.println("rs.next()" + result);
			if (result) {
				System.out.println("성공");//안뜸
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	
	//관리자가 전체 유저 리스트 볼 수 있게 하는 것 memberVO타입으로 만들기(아이디, 이름, 패스워드, 권한정보, 등)
	public List<MemberVO> selectMemList(){
		List<MemberVO> list = new ArrayList<>();
		sql = "SELECT* FROM MEMBER ORDER BY MID";
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				mv.setMid(rs.getString("MID"));
				mv.setPass(rs.getString("PASS"));
				mv.setName(rs.getString("NAME"));
				mv.setPhone(rs.getString("PHONE"));
				mv.setRespon(rs.getString("RESPONSIBILITY"));
				list.add(mv);
		}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	

}// end of boardDAO
