package day0305;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import day0304.homework.DBConnection;

public class UseResultSetMetaData {
	private JTextArea jta;
	private JScrollPane jsp;
	
	public UseResultSetMetaData() throws SQLException{
		DBConnection dbConnection = DBConnection.getInstance();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbConnection.getConnection(id, pass);
			
			String tname = "emp";
			String selectEmp = "select * from " + tname;
			pstmt = conn.prepareStatement(selectEmp);
			
//			pstmt.setString(1, tname);
			
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
//			System.out.println("number of column : " + rsmd.getColumnCount());
//			System.out.println("name of column : " + rsmd.getColumnName(1));
//			System.out.println("datatype of column : " + rsmd.getColumnTypeName(1));
//			System.out.println("size of column : " + rsmd.getPrecision(1));
//			System.out.println("null 허용 : " + rsmd.isNullable(1));
			
			// 메타데이터 정보를 JTextArea에 추가
            StringBuilder output = new StringBuilder();
            output.append(tname).append("테이블의 정보\n");
            output.append("컬럼명\tNull허용\t데이터형\n");
            int size = 0;
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
            	size = rsmd.getPrecision(i);
				output.append(rsmd.getColumnName(i)).append("\t")
						.append(rsmd.isNullable(i)).append("\t")
						.append(rsmd.getColumnTypeName(i));
				if (size!=0) {
					output.append("(").append(size).append(")");
				}
				output.append("\n");
			}
            jta = new JTextArea(output.toString(), 10, 50);
            jsp = new JScrollPane(jta);
            JOptionPane.showMessageDialog(null, jsp);
			
		} finally {
			dbConnection.dbClose(rs, pstmt, conn);

		}
		
	}
	
	public static void main(String[] args) {
		try {
			new UseResultSetMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//main
}
