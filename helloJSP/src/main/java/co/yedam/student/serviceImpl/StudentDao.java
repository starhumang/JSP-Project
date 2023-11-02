package co.yedam.student.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DataSource;
import co.yedam.student.service.StudentVO;

public class StudentDao {
	DataSource ds = DataSource.getInstance();
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	void close() {
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
	} // close

	public int insert(StudentVO vo) {
		String sql = "insert into student(student_id, student_name, student_password, student_dept, student_birthday) values (?,?,?,?,?)";

		conn = ds.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 데이트타입을 문자열로 바꾸어줄때 simpledateformat
		// 2012-03-05 NOv-23-2012
		// Date -> STring : sdf.format()
		// STring => date : sdf.parse();
		// System.out.println(sdf.format(vo.getStudentBirthday()));
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getStudentName());
			psmt.setString(3, vo.getStudentPassword());
			psmt.setString(4, vo.getStudentDept());
			psmt.setString(5, sdf.format(vo.getStudentBirthday()));
			r = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r; // 처리된 건수가 없음 : 에러
	} // insert

	public int update(StudentVO vo) {
		String sql = "update student set student_name=?, student_password=?, student_dept=nvl(?,student_dept), student_birthday=? where student_id=?";
		conn = ds.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentName());
			psmt.setString(2, vo.getStudentPassword());
			psmt.setString(3, vo.getStudentDept());
			psmt.setString(4, sdf.format(vo.getStudentBirthday()));
			psmt.setString(5, vo.getStudentId());
			int r = psmt.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	} // update

	public int delete(String sid) {
		String sql = "delete from student where student_id=?";
		conn = ds.getConnection();
		StudentVO vo = new StudentVO();
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sid);
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	} // delete

	public List<StudentVO> list() {
		List<StudentVO> list = new ArrayList<>();
		StudentVO vo;
		String sql = "select * from student";
		ResultSet rs;
		conn = ds.getConnection();
		try {

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				vo = new StudentVO(); // ** 초기화

				vo.setStudentId(rs.getString("student_id"));
				vo.setStudentName(rs.getString("student_name"));
				vo.setStudentPassword(rs.getString("student_password"));
				vo.setStudentDept(rs.getString("student_dept"));
				vo.setStudentBirthday(rs.getDate("student_birthday"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	} // list

	public StudentVO select(String sid) {
		StudentVO vo = null;
		String sql = "select * from student where student_id=?";
		ResultSet rs;
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sid);
			rs = psmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (rs.next()) {
				vo = new StudentVO();
				vo.setStudentId(rs.getString("student_id"));
				vo.setStudentName(rs.getString("student_name"));
				vo.setStudentPassword(rs.getString("student_password"));
				vo.setStudentDept(rs.getString("student_dept"));
				try {
					vo.setStudentBirthday(sdf.parse(rs.getString("student_birthday")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	} // select

}// dao
