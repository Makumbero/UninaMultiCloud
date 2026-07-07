package Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Accesso;

public class JDBCAcessoDao {
	Connection conn;

	public JDBCAcessoDao(Connection conn) {
		this.conn = conn;
	}
	public void inserisciAccesso(Accesso a) {
		try (Statement stmt = conn.createStatement()){ 
			
			try(ResultSet rs = stmt.executeQuery("INSERT INTO accesso (data) VALUES (?)")){
				if(rs.next()) {
					
				}
			}
			
			
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
