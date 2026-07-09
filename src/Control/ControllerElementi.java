package Control;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	CreaElemento MyCreaElemento;
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
	public Utente getMyUtente() {
		return MyUtente;
	}
	public void setMyUtente(Utente myUtente) {
		MyUtente = myUtente;
	}
	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.dispose();
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
	
	public void SalvaModifiche(Brano b, String Titolo, String Formato, String Descrizione, String Durata, String Dimensione, String Canali, String Campionamento) {
		if(!(b.getTitolo().equals(Titolo))) {
			if(Titolo.trim().isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Il titolo non deve essere vuoto! Non è stato modificato.");
			}
			else {
			b.setTitolo(Titolo);
			MyBranoDao.SetTitolo(Titolo, b.getIdBrano());
		}}
		
		if(!(b.getFormato().equals(Formato))) {
			if(Formato.trim().isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Il formato non deve essere vuoto! Non è stato modificato.");
			}
			else {
			b.setFormato(Formato);
			MyBranoDao.SetFormato(Formato, b.getIdBrano());
		}}
		
		if ((b.getDescrizione() == null && !Descrizione.isEmpty()) || (b.getDescrizione() != null && !b.getDescrizione().equals(Descrizione))) {
		    b.setDescrizione(Descrizione);
			MyBranoDao.Setdescrizione(Descrizione, b.getIdBrano());
		}
		
		try {
		    int CatchDurata = Integer.parseInt(Durata);
		    if(CatchDurata!=b.getDurata()) {
				b.setDurata(CatchDurata);
				MyBranoDao.SetDurata(CatchDurata, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "La durata deve essere un numero intero, non è stata modificata.");
		}
		
		try {
		    double CatchDimensione = Double.parseDouble(Durata);
		    if(CatchDimensione!=b.getDimensione()) {
				b.setDimensione(CatchDimensione);
				MyBranoDao.SetDimensioni(CatchDimensione, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "La dimensione deve essere un numero decimale, non è stata modificata.");
		}
		
		try {
		    int CatchCanali = Integer.parseInt(Canali);
		    if(CatchCanali!=b.getCanali()) {
				b.setCanali(CatchCanali);
				MyBranoDao.SetCanali(CatchCanali, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Il numero di canali deve essere un numero intero, non è stata modificato.");
		}
		
		try {
		    int CatchCampionamento = Integer.parseInt(Campionamento);
		    if(CatchCampionamento!=b.getCampionamento()) {
				b.setCampionamento(CatchCampionamento);
				MyBranoDao.SetCampionamento(CatchCampionamento, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Il campionamento deve essere un numero intero, non è stata modificato.");
		}
		
		MyModificaElemento.dispose();
		MyMieiElementi.dispose();
		MyMieiElementi=new MieiElementi(this, MyHome);
		MyMieiElementi.setVisible(true);
		MyMieiElementi.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
	}
	public void CreaElemento(JFrame Precedente) {
		Precedente.setVisible(false);
		MyCreaElemento=new CreaElemento(Precedente, this);
		MyCreaElemento.setVisible(true);
	}
	public void SalvaCreazione(String Titolo, String Formato, String Descrizione, String Durata, String Dimensione, String Canali, String Campionamento) {
		boolean verifica=true;
		if(Titolo.trim().isEmpty()) {
			 JOptionPane.showMessageDialog(null, "Il titolo non deve essere vuoto!");
			 verifica=false;
		}
		
		if(Formato.trim().isEmpty()) {
			 JOptionPane.showMessageDialog(null, "Il formato non deve essere vuoto!");
			 verifica=false;
		}
		
		try {
		    int CatchDurata = Integer.parseInt(Durata);

		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "La durata deve essere un numero intero!");
		    verifica=false;
		}
		
		try {
		    double CatchDimensione = Double.parseDouble(Dimensione);
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "La dimensione deve essere un numero decimale!");
		    verifica=false;
		}
		
		try {
		    int CatchCanali = Integer.parseInt(Canali);
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Il numero di canali deve essere un numero intero!");
		    verifica=false;
		}
		
		try {
		    int CatchCampionamento = Integer.parseInt(Campionamento);
        } catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Il campionamento deve essere un numero intero!");
		    verifica=false;
		}
		
		if(verifica==true) {
			MyBranoDao.AggiungiBrano(Titolo,Formato, Integer.parseInt(Durata), Descrizione, Integer.parseInt(Canali), Integer.parseInt(Campionamento), Double.parseDouble(Dimensione), "https", MyUtente.getEmail());
			MyCreaElemento.dispose();
			MyMieiElementi.dispose();
			MyMieiElementi=new MieiElementi(this, MyHome);
			MyMieiElementi.setVisible(true);
			MyMieiElementi.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
		}
	}
}
	

