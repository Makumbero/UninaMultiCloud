package Control;

import java.sql.Connection;

import Dao.JDBCUtenteDao;
import Entity.Utente;

public class ControllerPlaylist {
	JDBCUtenteDao UtenteDAO;
	Connection conn;
	
	
	public ControllerPlaylist(JDBCUtenteDao UtenteDAO, Connection conn) {
		this.UtenteDAO=UtenteDAO;
		this.conn=conn;
		
	}
	
	public Utente getAutorePerEmail(String EmailIN) {
		Utente u;
		u=UtenteDAO.getAutorePerEmail(EmailIN);
		return u;
		
	}
	
}
