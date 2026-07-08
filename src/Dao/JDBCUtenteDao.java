package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Utente;

import Dao.UtenteDao;

public class JDBCUtenteDao implements UtenteDao {
	Connection conn;
	public JDBCUtenteDao(Connection conn) {
		this.conn=conn;
	}
	@Override
	public Utente ricercaPerEmail(String Email) {
		Utente u=null;
		
			try (Statement stmt = conn.createStatement()){ 
				
				try(ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE email = '" + Email + "'")){
					if(rs.next()) {
						Utente u1=new Utente(rs.getString("username"),Email,rs.getString("password"));
						u1=u;
					}
				}
				
				
				

				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return u;
	}


	@Override
	public boolean VerificaUtente(String Email, String Password) {
	    String sql = "SELECT verificaUtente(?, ?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, Email);
	        pstmt.setString(2, Password);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getBoolean(1); // legge il valore boolean restituito dalla funzione
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;

	}
	
	@Override
	public Utente getAutorePerEmail(String EmailIN) {
		Utente user;
		String sql = "SELECT * FROM  GetAutorePerEmail(?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, EmailIN);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                user=new Utente(rs.getString("Username"),rs.getString("Email"),rs.getString("Password"));
	                return user;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	 
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
	//setter e getter
	public  void SetTitolo(String TitoloIN, int IdElementoIN) {
		String sql = "CALL SetTitolo(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, TitoloIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();
	  
	             
	            
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
	
		
}

