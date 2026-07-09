package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Control.ControllerPlaylist;

public class JDBCAccedePubblicaDao implements AccedePubblicaDao {
	Connection conn;
	ControllerPlaylist cPlaylist;
	public JDBCAccedePubblicaDao(Connection conn, ControllerPlaylist cPlaylist) {
		this.conn = conn;
		this.cPlaylist=cPlaylist;
	}
	
	@Override
	public  void AggiungiAccedePubblica(int IdPubblicaIN, String EmailIN) {
		String sql = "CALL AggiungiAccedePubblica(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, IdPubblicaIN);
	        pstmt.setString(2, EmailIN);
	        pstmt.execute();
	  
	             
	            
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
}
