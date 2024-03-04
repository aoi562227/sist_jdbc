package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DBConnection;
import kr.co.sist.statement.vo.EmployeeVO;

public class PreparedStatementDAO {
	private static PreparedStatementDAO preparedStatementDAO;
	
	private PreparedStatementDAO() {
		
	}
	
	public static PreparedStatementDAO getInstance() {
		if (preparedStatementDAO == null) {
			preparedStatementDAO = new PreparedStatementDAO();
		}
		return preparedStatementDAO;
	}
	
	public void insertEMP(EmployeeVO employeeVO) throws SQLException{
		DBConnection dbConnection = DBConnection.getInstance();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			String insertEmp = "insert into employee(empno, ename, job, sal) values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertEmp);
			
			pstmt.setInt(1, employeeVO.getEmpno());
			pstmt.setString(2, employeeVO.getEname());
			pstmt.setString(3, employeeVO.getJob());
			pstmt.setDouble(4, employeeVO.getSal());
			
			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, conn);
		}
				
	}
	
	public int updateEmp(EmployeeVO employeeVO) throws SQLException {
		int cnt = 0;
		
		DBConnection dbConnection = DBConnection.getInstance();
		//1.
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			//2.
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			//3. 쿼리문 생성객체 얻기
			String updateEmp="update employee set job=?,sal=? where empno=?";
			
			preparedStatement = conn.prepareStatement(updateEmp);
			//4. 바인드변수에 값 설정
			preparedStatement.setString(1, employeeVO.getJob());
			preparedStatement.setDouble(2, employeeVO.getSal());
			preparedStatement.setInt(3, employeeVO.getEmpno());
			//5. 쿼리문 수행 후 결과 얻기
			cnt = preparedStatement.executeUpdate();
			
		} finally {
			//6. 연결 끊기
			dbConnection.dbClose(null, preparedStatement, conn);
		}
		

		return cnt;
	}
	
	public int deleteEmp(int empno) throws SQLException {
		int cnt = 0;
		
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
		
			String deleteEmp = "delete from employee where empno=?";
			preparedStatement = conn.prepareStatement(deleteEmp);
			
			preparedStatement.setInt(1, empno);
			
			cnt = preparedStatement.executeUpdate();
		} finally {
			dbConnection.dbClose(null, preparedStatement, conn);
		}
		
		
		return cnt;
	}
	
	public EmployeeVO selectOneEmp(int empno) throws SQLException {
		EmployeeVO employeeVO = null;
		
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			StringBuilder selectOneEmp = new StringBuilder();
			selectOneEmp
			.append("select ename, job, sal, hiredate, to_char(hiredate, 'yyyy-mm-dd') hiredate2 ")
			.append("from employee ")
			.append("where empno=?");
			
			preparedStatement = conn.prepareStatement(selectOneEmp.toString());
			
			preparedStatement.setInt(1, empno);
			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
	            // 결과셋에서 사원 정보를 읽어와서 EmployeeVO 객체에 설정
	            String ename = rs.getString("ename");
	            String job = rs.getString("job");
	            double sal = rs.getDouble("sal");
	            String hiredate = rs.getString("hiredate");
	            
	            // EmployeeVO 객체 생성
	            employeeVO = new EmployeeVO(empno, rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"), rs.getDate("hiredate"), rs.getString("hiredate2"));
	        }
			
		} finally {
			dbConnection.dbClose(rs, preparedStatement, conn);
		}
		
		return employeeVO;
	}
	
	public List<EmployeeVO> selectAllEmp() throws SQLException {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			String selectAllEmp = "select empno, ename, job, sal, to_char(hiredate, 'yyyy-mm-dd q\"분기\"') hiredate from employee";
			
			preparedStatement = conn.prepareStatement(selectAllEmp);			
			
			rs = preparedStatement.executeQuery();
			EmployeeVO employeeVO = null;
			while(rs.next()) {
				employeeVO = new EmployeeVO(rs.getInt("empno"), rs.getString("ename"),rs.getString("job"),rs.getDouble("sal"), null, rs.getString("hiredate"));
				list.add(employeeVO);
			}			
	
		} finally {
			dbConnection.dbClose(rs, preparedStatement, conn);
		}
		
		
		return list;
	}
	
	
}
