package kr.co.sist.callable.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import day0304.homework.DBConnection;
import kr.co.sist.statement.vo.EmployeeVO;
import kr.co.sist.vo.ResultVO;

public class CallableStatementDAO {
	private static CallableStatementDAO callableStatementDAO;
	
	public CallableStatementDAO() {

	}
	
	public static CallableStatementDAO getInstance() {
		if (callableStatementDAO == null) {
			callableStatementDAO = new CallableStatementDAO();
		}
		return callableStatementDAO;
	}
	
	public ResultVO insertEmployee(EmployeeVO employeeVO) throws SQLException {
		ResultVO resultVO=null;
		
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			cstmt = conn.prepareCall("{ call insert_employee(?,?,?,?,?,?) }");
			
			//in param
			cstmt.setInt(1, employeeVO.getEmpno());
			cstmt.setString(2, employeeVO.getEname());
			cstmt.setString(3, employeeVO.getJob());
			cstmt.setDouble(4, employeeVO.getSal());
			
			//out param
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			
			cstmt.execute();
			
			resultVO = new ResultVO(cstmt.getInt(5), cstmt.getString(6));
		} finally {
			dbConnection.dbClose(null, cstmt, conn);
		}
		
		return resultVO;
	}
	
	public ResultVO updateEmployee(EmployeeVO eVO) throws SQLException {
		ResultVO rVO=null;
		
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			cstmt = conn.prepareCall("{call update_employee(?,?,?,?,?) }");
			//in param
			cstmt.setInt(1, eVO.getEmpno());
			cstmt.setString(2, eVO.getJob());
			cstmt.setDouble(3, eVO.getSal());
			//out param
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			
			cstmt.execute();
			rVO = new ResultVO(cstmt.getInt(4), cstmt.getString(5));
		} finally {
			dbConnection.dbClose(null, cstmt, conn);
		}
		
		
		return rVO;
	}
	
	public ResultVO deleteEmployee(int empno) throws SQLException {
		ResultVO rVO=null;
		
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			cstmt = conn.prepareCall("{call delete_employee(?,?,?) }");
			//in param
			cstmt.setInt(1, empno);
			//out param
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			
			cstmt.execute();
			rVO = new ResultVO(cstmt.getInt(2), cstmt.getString(3));
		} finally {
			dbConnection.dbClose(null, cstmt, conn);
		}
		
		
		return rVO;
	}
	
	
//	public static void main(String[] args) throws SQLException {
//		System.out.println(callableStatementDAO.getInstance().insertEmployee(new EmployeeVO(2121, "최수연", "개발", 3100, null)));
//	}//main
	
}
