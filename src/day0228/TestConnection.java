package day0228;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	public TestConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id = "scott";
		String password = "tiger";
		Connection con = null;
		Statement stmt = null;
		try {
			con = DriverManager.getConnection(url, id, password);
			System.out.println("연결 끝");
			
			stmt = con.createStatement();
			
			int deptno = 21;
			String dname = "영업부";
			String loc = "서울";
			
//			String insertCpDept = "insert into cp_dept(deptno, dname, loc_ values(" + deptno +"', '"+dname+"', '"
//					+"', '" +loc +"')";
			StringBuilder insertCpDept = new StringBuilder();
			insertCpDept.append("insert into cp_dept(deptno,dname,loc) values(").append(deptno)
			.append(", '").append(dname).append("', '").append(loc).append("')");
			System.out.println(insertCpDept);
			
			int cnt = stmt.executeUpdate(insertCpDept.toString());
			System.out.println(cnt+"건");
			
			// Perform query using Statement
			// by providing SSN at run time
		} finally {
			if (con!=null) {
				con.close();
			}
			// TODO: handle exception
		}
		

	}
	
	public static void main(String[] args) {
		try {
			new TestConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//main
}
