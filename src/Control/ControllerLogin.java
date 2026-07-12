package Control;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.JFrame;

import Boundary.ERROR;
import Boundary.GraficoVisualizzazioni;
import Boundary.Home;
import Boundary.Login;
import Boundary.Profilo;
import Dao.JDBCAccessoDao;
import Dao.JDBCUtenteDao;
import Entity.Utente;

public class ControllerLogin {
	Home MyHome;
 	Login MyLogin;
 	ERROR Errore;
 	Connection conn;
 	JFrame lastFrame;
 	GraficoVisualizzazioni MyGrafico;
 	JDBCUtenteDao UtenteDAO;
 	JDBCAccessoDao AccessoDao;
 	ControllerElementi cEle;
 	Utente MyUtente;
 	public ControllerLogin(Connection conn1) {
		MyLogin=new Login(this);
		MyLogin.setVisible(true);
		Errore=new ERROR(this);
		conn=conn1;
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
			MyGrafico=new GraficoVisualizzazioni(this,MyUtente);
			ControllerCerca cCerca= new ControllerCerca(conn,MyHome,MyUtente);
			ControllerPlaylist cPl= new ControllerPlaylist(conn, MyUtente, MyHome, this);
		    cEle= new ControllerElementi(conn, MyUtente, MyHome, this, cPl);
			cPl.setControllerElementi(cEle);
			AccessoDao=cEle.getMyAccessoDao();
			MyHome.setControllerElementi(cEle);
			MyHome.setControllerPlaylist(cPl);
			MyHome.setControllerCerca(cCerca);
			cCerca.setcPlay(cPl);
			cCerca.setcEle(cEle);
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
		Date oggi=new Date(System.currentTimeMillis()); //ritorna la data di oggi in millisecondi
		MyGrafico.creaGrafico(AccessoDao.GetAccessiPerMese(oggi));
		MyGrafico.setVisible(true);
		
		lastFrame=MyGrafico;
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
