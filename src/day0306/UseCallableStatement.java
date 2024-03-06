package day0306;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import day0304.homework.DBConnection;

public class UseCallableStatement {
	public UseCallableStatement(int num, int num2)throws SQLException {
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbConnection.getConnection(id, pass);
			
			cstmt = con.prepareCall("{call plus_proc(?,?,?)}");
			
			//inparam
			cstmt.setInt(1, num);
			cstmt.setInt(2, num2);
			
			//outparam
			cstmt.registerOutParameter(3, Types.NUMERIC);
			
			cstmt.execute();
			int result = cstmt.getInt(3);
			System.out.println(num + " + " + num2 + " = " + result);
		} finally {
			dbConnection.dbClose(null, cstmt, con);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		try {
			for (int i = 0; i < 30; i++) {
				new UseCallableStatement(3, 6);
				Thread.sleep(1000);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//main
}
