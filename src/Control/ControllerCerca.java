package Control;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Boundary.Cerca;
import Boundary.Home;
import Entity.Utente;

public class ControllerCerca {
	Connection conn;
	Home MyHome;
	Cerca MyCerca;
	Utente MyUtente;
	JFrame Precedente;
	
	
	
	public ControllerCerca(Connection conn, Home MyHome,Utente MyUtente) {
		this.conn = conn;
		this.MyHome = MyHome;
		this.MyUtente=MyUtente;
		MyCerca=new Cerca(this);
		
	}



	public void HomeToCerca() {
		MyHome.setVisible(false);
		MyCerca.setVisible(true);
		Precedente=MyHome;
		
	}
	
	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.dispose();
		Precedente.setVisible(true);
	}



	public JFrame getPrecedente() {
		return Precedente;
	}



	public void setPrecedente(JFrame precedente) {
		Precedente = precedente;
	}
	
}
