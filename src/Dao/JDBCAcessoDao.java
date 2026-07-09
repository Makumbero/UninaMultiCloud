package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Control.ControllerElementi;
import Entity.Accesso;
import Entity.PlaylistPrivata;

public class JDBCAcessoDao implements AccessoDAO{
	Connection conn;
	ControllerElementi cElementi;

	public JDBCAcessoDao(Connection conn,ControllerElementi cElementi) {
		this.conn = conn;
		this.cElementi=cElementi;
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
	
	@Override
	public List<Accesso> GetAccessiPerElemento(int IdElementoIN){
		List <Accesso>listaAccessi= new ArrayList<>();
		 String sql = "SELECT * FROM  GetAccessiPerElemento(?)";
	
		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, IdElementoIN);
	
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while(rs.next()) {
		            	listaAccessi.add(new Accesso(cElementi.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdAccesso"),rs.getDate("Data"),rs.getInt("IdElemento")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaAccessi;
	}
	
	@Override
	public List<Accesso>  GetAccessiPerData(int DataIN){
		List <Accesso>listaAccessi= new ArrayList<>();
		 String sql = "SELECT * FROM   GetAccessiPerData(?)";
	
		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, DataIN);
	
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while(rs.next()) {
		            	listaAccessi.add(new Accesso(cElementi.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdAccesso"),rs.getDate("Data"),rs.getInt("IdElemento")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaAccessi;
	}
			
}
