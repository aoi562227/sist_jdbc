package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0304.homework.DBConnection;
import kr.co.sist.vo.ZipcodeVO;

public class ZipcodeDAO {
	private static ZipcodeDAO zDAO;
	
	public ZipcodeDAO() {
		
	}
	
	public static ZipcodeDAO getInstance() {
		if (zDAO  == null) {
			zDAO = new ZipcodeDAO();
		}
		
		return zDAO;
	}
	
	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		DBConnection dbCon = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			conn = dbCon.getConnection(id, pass);
			
			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode
			.append("	select	zipcode,sido,gugun,dong,nvl(bunji, ' ') bunji	")
			.append("	from	zipcode	")
			.append("	where	dong	like ?||'%'	");
			
			pstmt = conn.prepareStatement(selectZipcode.toString());
			
			pstmt.setString(1, dong);
			
			rs = pstmt.executeQuery();
			
			ZipcodeVO zVO = null;
			while (rs.next()) {
				zVO = new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"), rs.getString("gugun"), rs.getString("dong"), rs.getString("bunji"));
				list.add(zVO);
			}
			
			
		} finally {
			dbCon.dbClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		ZipcodeDAO zD = ZipcodeDAO.getInstance();
		try {
			System.out.println(zD.selectZipcode("역삼동"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//main
}
