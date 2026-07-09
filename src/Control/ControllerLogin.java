package Control;
import java.sql.Connection;

import javax.swing.JFrame;

import Boundary.ERROR;
import Boundary.Home;
import Boundary.Login;
import Boundary.Profilo;

import Dao.JDBCUtenteDao;
import Entity.Utente;

public class ControllerLogin {
	Home MyHome;
 	Login MyLogin;
 	ERROR Errore;
 	Connection conn;
 	JFrame lastFrame;
 	Profilo MyProfilo;
 	JDBCUtenteDao UtenteDAO;
 	Utente MyUtente;
 
 	public ControllerLogin(Connection conn1) {
		MyLogin=new Login(this);
		MyLogin.setVisible(true);
		Errore=new ERROR(this);
		conn=conn1;
		MyProfilo=new Profilo(this);
		UtenteDAO=new JDBCUtenteDao(conn1);
		
 	}
	
	public void LoginHome() {
		MyLogin.setVisible(false);
		MyHome.setVisible(true);
		
	}
	public void verificaCredenziali(String Email, String Password) {
		UtenteDAO=new JDBCUtenteDao(conn);
		if(UtenteDAO.VerificaUtente(Email,Password)){
			MyUtente=this.getAutorePerEmail(Email);
			MyHome=new Home(this);
			ControllerCerca cCerca= new ControllerCerca(conn,MyHome,MyUtente);
			ControllerPlaylist cPl= new ControllerPlaylist(conn, MyUtente, MyHome, this);
			ControllerElementi cEle= new ControllerElementi(conn, MyUtente, MyHome, this, cPl);
			MyHome.setControllerElementi(cEle);
			MyHome.setControllerCerca(cCerca);
			LoginHome();
		}else {
			showError();
		}
		
		
	}
	public void showError() {
		Errore.setVisible(true);
	}
	public void dismissError() {
		Errore.setVisible(false);
	}
	public void HomeToProfilo() {
		MyHome.setVisible(false);
		MyProfilo.setVisible(true);
		
		lastFrame=MyProfilo;
	}
	public void ReturnHome() {
		lastFrame.setVisible(false);
		MyHome.setVisible(true);
	}
	public Utente getAutorePerEmail(String EmailIN) {
		Utente u;
		u=UtenteDAO.getAutorePerEmail(EmailIN);
		return u;
		
	}
	
}
