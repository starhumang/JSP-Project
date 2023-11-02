package co.yedam.common;
//DAO
import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource { //싱글톤만드는 법
	private static DataSource dataSource = new DataSource(); //1. 내 클래스에서 나의 클래스를 만들기
	private DataSource() {} //2. 날 생성하지 못하도록 내가 나의 생성자를 만들기
	
	//위에서 static을 해놨기 때문에 아래에서도 다 적용
	private static String driver = "oracle.jdbc.OracleDriver"; // 구글에 자바 오라클 연동 검색해서 드라이버 복사함 (공백 없어야 함)
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe"; //SQL 인사관리 속성 열어서 봄 (공백 없어야 함)
	private static String user = "hr"; //SQL 아이디
	private static String password = "1234";//SQL 비밀번호
	
	private static Connection conn;
	
	public static DataSource getInstance() { //3. 외부에서는 인스턴스만 갖다 쓰도록 만들기
		return dataSource;
	}
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB 연결성공 ~~~~~ ");
		}catch(Exception e) {
			e.printStackTrace();
		}
			return conn;
}
}
