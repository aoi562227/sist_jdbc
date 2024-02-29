package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

import javax.swing.JOptionPane;

import kr.co.sist.statement.vo.EmployeeVO;

public class StatementDAO {
	public void insertEmp(EmployeeVO employeeVO) throws SQLException {
		//드라이버 얻기
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection conn = null;
		Statement stmt = null;
		
		
		
		try {
			//커넥션 얻기
			conn = DriverManager.getConnection(url, id, pass);
			//쿼리문 생성객체 얻기
			stmt = conn.createStatement();
			//쿼리문 수행 후 결과
			StringBuilder insertEmployee = new StringBuilder();
			insertEmployee.append("insert into employee(empno, ename, job, sal) values(")
			.append(employeeVO.getEmpno()).append(", '")
			.append(employeeVO.getEname()).append("', '").append(employeeVO.getJob()).append("', ")
			.append(employeeVO.getSal()).append(")");
			stmt.executeUpdate(insertEmployee.toString());
		} finally {
			//연결 끊기
			if (stmt!=null) {
				stmt.close();
			}
			if (conn!=null) {
				conn.close();
			}
		}
			
	}

	
	public int updateEmp(EmployeeVO employeeVO) throws SQLException {
		int cnt = 0;
		
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//커넥션 얻기
			conn = DriverManager.getConnection(url, id, pass);
			//쿼리문 생성객체 얻기
			stmt = conn.createStatement();
			//쿼리문 수행 후 결과 얻기
			StringBuilder updateEmp = new StringBuilder();
			updateEmp.append("update employee	")
					 .append("set job = '").append(employeeVO.getJob()).append("', sal=")
					 .append(employeeVO.getSal())
					 .append("	where empno = ").append(employeeVO.getEmpno());
			System.out.println(updateEmp);
			stmt.executeUpdate(updateEmp.toString());
		} finally {
			//연결 끊기
			if (stmt!=null) {
				stmt.close();
			}
			if (conn!=null) {
				conn.close();
			}
		}
		
		
		return cnt;
	}
	public int deleteEmp(int empno) throws SQLException {
		int cnt = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//커넥션 얻기
			conn = DriverManager.getConnection(url, id, pass);
			//쿼리문 생성객체 얻기
			stmt = conn.createStatement();
			//쿼리문 수행 후 결과 얻기
			StringBuilder deleteEmp = new StringBuilder();
			deleteEmp.append("delete from employee	")
					 .append("where empno = ").append(empno);
					
			System.out.println(deleteEmp);
			cnt = stmt.executeUpdate(deleteEmp.toString());
		} finally {
			//연결 끊기
			if (stmt!=null) {
				stmt.close();
			}
			if (conn!=null) {
				conn.close();
			}
		}
		return cnt;
	}
	public List<EmployeeVO> selectAllEmp() throws SQLException {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//커넥션 얻기
			conn = DriverManager.getConnection(url, id, pass);
			//쿼리문 생성객체 얻기
			stmt = conn.createStatement();
			//쿼리문 수행 후 결과 얻기
			String selectEmp = "select empno, ename, job, sal, hiredate from employee";
			System.out.println(selectEmp);
			rs = stmt.executeQuery(selectEmp);
			EmployeeVO employeeVO = null;
			
			
			while(rs.next()) {
				employeeVO = new EmployeeVO(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"), rs.getDate("hiredate"));	
				list.add(employeeVO);
			}
//			
		} finally {
			//연결 끊기
			if (rs!=null) {
				rs.close();
			}
			if (conn!=null) {
				conn.close();
			}
			if (stmt!=null) {
				stmt.close();
			}
		}
		return list;
	}
	public EmployeeVO selectOneEmp(int empno) throws SQLException {
		EmployeeVO employeeVO = null;
		
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//커넥션 얻기
			conn = DriverManager.getConnection(url, id, pass);
			//쿼리문 생성객체 얻기
			stmt = conn.createStatement();
			//쿼리문 수행 후 결과 얻기
			StringBuilder selectEmp = new StringBuilder();
			selectEmp.append("select ename, job, sal, hiredate	")
					 .append("from employee	")
					 .append("where empno = ").append(empno);
			System.out.println(selectEmp.toString());
			rs = stmt.executeQuery(selectEmp.toString());
			
			if (rs.next()) {
				employeeVO = new EmployeeVO(empno, rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"), rs.getDate("hiredate"));	
			}
//			
		} finally {
			//연결 끊기
			if (rs!=null) {
				rs.close();
			}
			if (conn!=null) {
				conn.close();
			}
			if (stmt!=null) {
				stmt.close();
			}
		}
		
		return employeeVO;
	}
	

	
}
