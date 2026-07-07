package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCBranoDao implements BranoDao{
	Connection conn;
	public JDBCBranoDao(Connection conn) {
		this.conn = conn;
	}
	@Override
	public ArrayList CercaElementiPerEmail(String EmailIN) {
		 List listaBrani= new ArrayList();
		 String sql = "SELECT * FROM CercaElementiPerEmail(?)";

		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, EmailIN);

		        try (ResultSet rs = pstmt.executeQuery()) {
		            while(rs.next()) {
		            	
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return null;
	}

}
