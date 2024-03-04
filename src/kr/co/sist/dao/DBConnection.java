package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static DBConnection dbCon;
	
	private DBConnection() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static DBConnection getInstance() {
		if (dbCon == null) {
			dbCon = new DBConnection();
		}
		return dbCon;
	}
	
	public Connection getConnection(String id, String pass) throws SQLException {
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			conn = DriverManager.getConnection(url,id,pass);
			return conn;
	}
	
	public void dbClose(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
		if (rs!=null) {
			rs.close();
		}
		if (stmt!=null) {
			stmt.close();
		}
		if (conn!=null) {
			conn.close();
		}
	}
}
