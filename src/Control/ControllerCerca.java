package Control;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Boundary.Cerca;
import Boundary.Home;
import Boundary.MieiElementi;
import Boundary.RisultatiRicerca;
import Entity.Brano;
import Entity.Utente;

public class ControllerCerca {
	Connection conn;
	Home MyHome;
	Cerca MyCerca;
	RisultatiRicerca Risricerca;
	Utente MyUtente;
	JFrame Precedente;
	MieiElementi mieiElementi;
	ControllerElementi cEle;
	ControllerPlaylist cPlay;
	
	
	
	public ControllerCerca(Connection conn, Home MyHome,Utente MyUtente) {
		this.conn = conn;
		this.MyHome = MyHome;
		this.MyUtente=MyUtente;
		MyCerca=new Cerca(this);
		Risricerca=new RisultatiRicerca(this);
		
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



	public ControllerElementi getcEle() {
		return cEle;
	}



	public void setcEle(ControllerElementi cEle) {
		this.cEle = cEle;
	}



	public ControllerPlaylist getcPlay() {
		return cPlay;
	}



	public void setcPlay(ControllerPlaylist cPlay) {
		this.cPlay = cPlay;
	}



	public void VisualizzaElemento(Brano brano) {
		cEle.VisualizzaElemento(Risricerca, brano);
		
	}
	
	
}
