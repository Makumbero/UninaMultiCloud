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
 		Login frameLogin= new Login(this);
		MyLogin=frameLogin;

		ERROR frameError=new ERROR(this);
		Errore=frameError;
		conn=conn1;
		Profilo frameProfilo=new Profilo(this);
		MyProfilo=frameProfilo;
		
		
		
		frameLogin.setVisible(true);
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
			ControllerElementi cEle= new ControllerElementi(conn, MyUtente, MyHome, this);
			MyHome.setControllerElementi(cEle);
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
