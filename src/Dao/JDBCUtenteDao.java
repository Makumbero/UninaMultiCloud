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

	public boolean getUtente(String Email, String Password) {
		try (Statement stmt = conn.createStatement()){ 
			
			try(ResultSet rs = stmt.executeQuery("SELECT * FROM utente WHERE email = '" + Email + "' AND password='"+ Password +"'" )){
				if(rs.next()) {
					Utente u1=new Utente(rs.getString("username"),Email,rs.getString("password"));
					return true;
				}
			}
			
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
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
		
}

