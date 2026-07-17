package Control;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Boundary.ERROR;
import Boundary.GraficoVisualizzazioni;
import Boundary.Home;
import Boundary.Login;
import Dao.JDBCAccessoDao;
import Dao.JDBCUtenteDao;
import Entity.Accesso;
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
	ControllerPlaylist cPl;
	ControllerCerca cCerca;

 	public ControllerLogin(Connection conn1) {
		MyLogin=new Login(this);
		MyLogin.setVisible(true);
		Errore=new ERROR(this);
		conn=conn1;
		UtenteDAO=new JDBCUtenteDao(conn1);

 	}

	public void LoginHome() {
		MyLogin.dispose();
		Errore.dispose();
		MyHome.setVisible(true);
	}

	public void verificaCredenziali(String Email, String Password) {
		UtenteDAO=new JDBCUtenteDao(conn);
		if(UtenteDAO.VerificaUtente(Email,Password)){
		    MyUtente=this.getAutorePerEmail(Email);
			MyHome=new Home(this);
			MyGrafico=new GraficoVisualizzazioni(this,MyUtente);
			cCerca= new ControllerCerca(conn,MyHome,MyUtente);
            cPl= new ControllerPlaylist(conn, MyUtente, MyHome, this);
		    cEle= new ControllerElementi(conn, MyUtente, MyHome, this, cPl);
			cPl.setControllerElementi(cEle);
			cPl.setControllerCerca(cCerca);
			AccessoDao=cEle.getMyAccessoDao();
			MyHome.setControllerElementi(cEle);
			MyHome.setControllerPlaylist(cPl);
			MyHome.setControllerCerca(cCerca);
			cCerca.setcPlay(cPl);
			cCerca.setcEle(cEle);
			cCerca.setcLog(this);
			LoginHome();
		}else {
			JOptionPane.showMessageDialog(null, "Si Prega di inserire le credenziali corrette");
		}
	}

	public void Logout() {
		MyHome.dispose();
		MyLogin=new Login(this);
		MyLogin.setVisible(true);
		Errore=new ERROR(this);
		MyGrafico.dispose();
		MyUtente=null;
		cPl=null;
		cCerca=null;
		cEle=null;
		AccessoDao=null;

	}

	public void showError() {
		Errore.setVisible(true);
	}

	public void dismissError() {
		Errore.setVisible(false);
	}

	public void HomeToProfilo() {
		MyHome.setVisible(false);
        MyGrafico.creaGrafico(GetAccessiPerMese(new Date(System.currentTimeMillis())), "Giorni");
		MyGrafico.setVisible(true);

		lastFrame=MyGrafico;
	}

	public void ProfiloToHome() {
		MyGrafico.dispose();
		MyHome.setVisible(true);
		lastFrame=MyHome;
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

	public List<Utente> CercaAutorePerNome(String NomeIN) {
		List<Utente> listautenti=UtenteDAO.CercaAutorePerNome(NomeIN);
		return listautenti;

	}

	 public List<Accesso> GetAccessiPerMese(Date DataIN){
		return AccessoDao.GetAccessiPerMese(DataIN);
	}

	 public List<Accesso> GetAccessiPerAnno(Date DataIN ){
		 return AccessoDao.GetAccessiPerAnno(DataIN);
	 }

	 public List<Accesso> GetAllAccessi(){
		 return AccessoDao.GetAllAccessi();
	 }

}
