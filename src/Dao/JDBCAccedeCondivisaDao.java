package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Control.ControllerPlaylist;

public class JDBCAccedeCondivisaDao implements AccedeCondivisaDao{
	Connection conn;
	ControllerPlaylist cPlaylist;
	public JDBCAccedeCondivisaDao(Connection conn, ControllerPlaylist cPlaylist) {
		this.conn = conn;
		this.cPlaylist=cPlaylist;
	}
	
	
	@Override
	public  void AggiungiAccedeCondivisa(int IdCondivisaIN, String EmailIN) {
		String sql = "CALL AggiungiAccedeCondivisa(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, IdCondivisaIN);
	        pstmt.setString(2, EmailIN);
	        pstmt.execute();
	  
	             
	            
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
}
