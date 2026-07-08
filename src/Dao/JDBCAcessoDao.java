package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Accesso;

public class JDBCAcessoDao implements AccessoDAO{
	Connection conn;

	public JDBCAcessoDao(Connection conn) {
		this.conn = conn;
	}
	@Override
	public  void AggiungiAccesso(int IdElementoIN, String EmailIN) {
		String sql = "CALL AggiungiAccesso(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, IdElementoIN);
	        pstmt.setString(2, EmailIN);
	        pstmt.execute();
	  
	             
	            
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
			
}
