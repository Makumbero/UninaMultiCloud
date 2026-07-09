package Control;

import java.sql.Connection;

import javax.swing.JFrame;

import Boundary.*;
import Dao.*;
import Entity.*;

public class ControllerElementi {
	Utente MyUtente;
	Connection conn;
	Home MyHome;
	MieiElementi MyMieiElementi;
	ControllerLogin cLog;
	JDBCBranoDao MyBranoDao;
	VisualizzaElemento MyVisualizzaElemento;
	ModificaElemento MyModificaElemento;
	public ControllerElementi(Connection conn1, Utente u, Home h, ControllerLogin cLog) {
		conn=conn1;
		MyUtente=u;
		MyHome=h;
		this.cLog=cLog;
		MyMieiElementi = new MieiElementi(this, MyHome);
        MyBranoDao = new JDBCBranoDao(conn, cLog);
	}
	public void HomeToMieiElementi(Utente u) {
		MyHome.setVisible(false);
		MyMieiElementi.setVisible(true);
		MyMieiElementi.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
	}
	
	public Utente getAutorePerEmail(String EmailIN) {
		Utente u=cLog.getAutorePerEmail(EmailIN);
		return u;
	}
	public Utente getMyUtente() {
		return MyUtente;
	}
	public void setMyUtente(Utente myUtente) {
		MyUtente = myUtente;
	}
	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.setVisible(false);
		Precedente.setVisible(true);
	}
	public void VisualizzaElemento(JFrame Precedente, Brano b) {
		Precedente.setVisible(false);
		MyVisualizzaElemento=new VisualizzaElemento(Precedente, this);
		MyVisualizzaElemento.setVisible(true);
		MyVisualizzaElemento.MostraElemento(b);
	}
	
	public void ModificaElemento(JFrame Precedente, Brano b) {
		Precedente.setVisible(false);
		MyModificaElemento=new ModificaElemento(Precedente, this, b);
		MyModificaElemento.setVisible(true);
	}
	
	public void SalvaModifiche(Brano b, String Titolo, String Formato, String Descrizione, int Durata, double Dimensione, int Canali, int Campionamento) {
		if(!(b.getTitolo().equals(Titolo))) {
			b.setTitolo(Titolo);
			MyBranoDao.SetTitolo(Titolo, b.getIdBrano());
		}
		if(!(b.getFormato().equals(Formato))) {
			b.setFormato(Formato);
			MyBranoDao.SetFormato(Formato, b.getIdBrano());
			
		if ((b.getDescrizione() == null && !Descrizione.isEmpty()) || (b.getDescrizione() != null && !b.getDescrizione().equals(Descrizione))) {
		    b.setDescrizione(Descrizione);
			MyBranoDao.Setdescrizione(Descrizione, b.getIdBrano());
				}
		if(b.getDurata()!=Durata) {
			b.setTitolo(Titolo);
			MyBranoDao.SetDurata(Durata, b.getIdBrano());
		}
		if(b.getDimensione()!=Dimensione) {
			b.setDimensione(Dimensione);
			MyBranoDao.SetDimensioni(Dimensione, b.getIdBrano());
		}
		if(b.getCanali()!=Canali) {
			b.setCanali(Canali);
			MyBranoDao.SetCanali(Canali, b.getIdBrano());
		}
		if(b.getCampionamento()!=Campionamento) {
			b.setCampionamento(Campionamento);
			MyBranoDao.SetCampionamento(Campionamento, b.getIdBrano());
		}
	}
}}
	

