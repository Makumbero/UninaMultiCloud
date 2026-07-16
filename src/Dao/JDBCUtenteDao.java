package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Utente;

import Dao.UtenteDao;

public class JDBCUtenteDao implements UtenteDao {
	Connection conn;
	public JDBCUtenteDao(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public List<Utente> CercaAutorePerNome(String UsernameIN) {
		List<Utente> listautenti=new ArrayList<>();
		String sql = "SELECT * FROM  CercaAutorePerNome(?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, UsernameIN);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                listautenti.add(new Utente(rs.getString("Username"),rs.getString("Email"),rs.getString("Password")));
	                
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listautenti;
	 
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
	
	
	//setter e getter
	
	
		
}

