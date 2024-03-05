package day0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListCellRenderer;

import day0304.homework.DBConnection;
import kr.co.sist.statement.vo.TestClobVO;

public class UseClob {

	public List<TestClobVO> selectClob() throws SQLException {
		List<TestClobVO> list = new ArrayList<TestClobVO>();

		DBConnection dbConnection = DBConnection.getInstance();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";

			conn = dbConnection.getConnection(id, pass);

			String selectClob = "select num,title,content,writer,input_date from test_clob";
			pstmt = conn.prepareStatement(selectClob);

			rs = pstmt.executeQuery();

			TestClobVO tcVO = null;

			BufferedReader br = null;
			StringBuilder content = new StringBuilder();
			String temp = "";

			while (rs.next()) {
				tcVO = new TestClobVO();
				tcVO.setNum(rs.getInt("num"));
				tcVO.setTitle(rs.getString("title"));
				tcVO.setWriter(rs.getString("writer"));
				tcVO.setInput_date(rs.getDate("input_date"));
//				tcVO.setContent(rs.getString("content"));
//				System.out.println(rs.getString("content") + " <<");

				br = new BufferedReader(rs.getClob("content").getCharacterStream());
				try {
					while ((temp = br.readLine()) != null) {
						content.append(temp).append("\n");
					}
					br.close();
				} catch (IOException e) {
					content.append("N/A");
					e.printStackTrace();
				}
				
				tcVO.setContent(content.toString());
				content.delete(0, content.length());
				
				list.add(tcVO);
			}

		} finally {
			dbConnection.dbClose(rs, pstmt, conn);
		}
		return list;

	}

	public void useSelectClob(List<TestClobVO> list) {
		Iterator<TestClobVO> iterator = list.iterator();
		
		if (list.isEmpty()) {
			System.out.println("작성된 기사가 없음");
		}
		
		TestClobVO tcVO = null;
		while (iterator.hasNext()) {
			tcVO = iterator.next();
			System.out.println("번호 : " + tcVO.getNum() + ", 제목 : " + tcVO.getTitle() + "\n내용 : " + tcVO.getContent()+ tcVO.getWriter() + "\n\n");
		}
	}

	public static void main(String[] args) {
		UseClob uc = new UseClob();
		try {
			List<TestClobVO> list = uc.selectClob();
			uc.useSelectClob(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// main
}
