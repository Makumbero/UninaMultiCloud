package Control;
import java.sql.Connection;

import javax.swing.JFrame;

import Boundary.ERROR;
import Boundary.Home;
import Boundary.Login;
import Boundary.Profilo;

import Dao.JDBCUtenteDao;

public class ControllerLogin {
	Home MyHome;
 	Login MyLogin;
 	ERROR e;
 	Connection conn;
 	JFrame lastFrame;
 	Profilo p;
 
 	public ControllerLogin(Connection conn1) {
 		Login frameLogin= new Login(this);
		MyLogin=frameLogin;
		Home frameHome=new Home(this);
		MyHome=frameHome;
		ERROR frameError=new ERROR(this);
		e=frameError;
		conn=conn1;
		Profilo frameProfilo=new Profilo(this);
		p=frameProfilo;
		
		
		frameLogin.setVisible(true);
 	}
	
	public void LoginHome() {
		MyLogin.setVisible(false);
		MyHome.setVisible(true);
		
	}
	public void verificaCredenziali(String Email, String Password) {
		JDBCUtenteDao UtenteDAO=new JDBCUtenteDao(conn);
		if(UtenteDAO.VerificaUtente(Email,Password)){
			LoginHome();
		}else {
			showError();
		}
		
		
	}
	public void showError() {
		e.setVisible(true);
	}
	public void dismissError() {
		e.setVisible(false);
	}
	public void HomeToProfilo() {
		MyHome.setVisible(false);
		p.setVisible(true);
		
		lastFrame=p;
	}
	public void ReturnHome() {
		lastFrame.setVisible(false);
		MyHome.setVisible(true);
	}
	
}
