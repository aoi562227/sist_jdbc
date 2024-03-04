// TableDAO.java
package day0304.homework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    private static TableDAO tDAO;
    private DBConnection dbConnection;

    private TableDAO() {
        dbConnection = DBConnection.getInstance();
    }

    public static TableDAO getInstance() {
        if (tDAO == null) {
            tDAO = new TableDAO();
        }
        return tDAO;
    }

    public List<String> selectAllTab() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection("scott", "tiger");
            stmt = conn.createStatement();
            String sql = "SELECT table_name FROM user_tables";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tableNames.add(rs.getString("table_name"));
            }
        } finally {
            dbConnection.dbClose(rs, stmt, conn);
        }

        return tableNames;
    }
}
