package Control;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Boundary.AggiungiCollaboratore;
import Boundary.Home;
import Boundary.MieiElementi;
import Boundary.ModificaElemento;
import Boundary.ScegliPlaylist;
import Boundary.VisualizzaElemento;
import Dao.JDBCAccedePubblicaDao;
import Dao.JDBCAccessoDao;
import Dao.JDBCBranoDao;
import Entity.Brano;
import Entity.Playlist;
import Entity.Utente;

public class ControllerElementi {
	Utente MyUtente;
	Connection conn;
	Home MyHome;
	MieiElementi MyMieiElementi;
	ControllerLogin MycLog;
	JDBCBranoDao MyBranoDao;
	JDBCAccessoDao MyAccessoDao;
	JDBCAccedePubblicaDao MyAccedePubblicaDao;
	VisualizzaElemento MyVisualizzaElemento;
	ModificaElemento MyModificaElemento;
	AggiungiCollaboratore MyCreaElemento;
	ScegliPlaylist MyScegliPlaylist;
	ControllerPlaylist MycPl;

	public ControllerElementi(Connection conn1, Utente u, Home h, ControllerLogin mycLog, ControllerPlaylist mycPl) {
		conn=conn1;
		MyUtente=u;
		MyHome=h;
		MycLog=mycLog;
		MycPl=mycPl;
		MyMieiElementi = new MieiElementi(this, MyHome);
        MyBranoDao = new JDBCBranoDao(conn, MycLog);
        MyAccessoDao=new JDBCAccessoDao(conn,this);
        MyAccedePubblicaDao=new JDBCAccedePubblicaDao(conn,MycPl);
	}

	public List<Brano> CercaElementiPerTitolo(String TitoloIN) {
		return MyBranoDao.CercaElementiPerTitolo(TitoloIN);
	}

	public List<Brano> CercaElementiPerAutore(String AutoreIN) {
		return MyBranoDao.CercaElementiPerAutore(AutoreIN);
	}
	public void HomeToMieiElementi() {
		MyHome.setVisible(false);
		MyMieiElementi.setVisible(true);
		MyMieiElementi.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
	}

	public Utente getAutorePerEmail(String EmailIN) {
		Utente u=MycLog.getAutorePerEmail(EmailIN);
		return u;
	}

	public Brano getElementoPerID(int IdElementoIN) {
		Brano b=MyBranoDao.GetElementoPerId(IdElementoIN);
		return b;
	}

	public JDBCAccessoDao getMyAccessoDao() {
		return MyAccessoDao;
	}

	public void setMyAccessoDao(JDBCAccessoDao myAccessoDao) {
		MyAccessoDao = myAccessoDao;
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
		AggiungiAccesso(b);
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
			MyBranoDao.SetTitolo(Titolo, b.getIdBrano());
		}}

		if(!(b.getFormato().equals(Formato))) {
			if(Formato.trim().isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Il formato non deve essere vuoto! Non è stato modificato.");
			}
			else {
			MyBranoDao.SetFormato(Formato, b.getIdBrano());
		}}

		if ((b.getDescrizione() == null && !Descrizione.isEmpty()) || (b.getDescrizione() != null && !b.getDescrizione().equals(Descrizione))) {
			MyBranoDao.Setdescrizione(Descrizione, b.getIdBrano());
		}

		try {
		    int CatchDurata = Integer.parseInt(Durata);
		    if(CatchDurata!=b.getDurata()) {
				MyBranoDao.SetDurata(CatchDurata, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "La durata deve essere un numero intero, non è stata modificata.");
		}

		try {
		    double CatchDimensione = Double.parseDouble(Durata);
		    if(CatchDimensione!=b.getDimensione()) {
				MyBranoDao.SetDimensioni(CatchDimensione, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "La dimensione deve essere un numero decimale, non è stata modificata.");
		}

		try {
		    int CatchCanali = Integer.parseInt(Canali);
		    if(CatchCanali!=b.getCanali()) {
				MyBranoDao.SetCanali(CatchCanali, b.getIdBrano());
			}
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Il numero di canali deve essere un numero intero, non è stata modificato.");
		}

		try {
		    int CatchCampionamento = Integer.parseInt(Campionamento);
		    if(CatchCampionamento!=b.getCampionamento()) {
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
		MyCreaElemento=new AggiungiCollaboratore(Precedente, this);
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

		if(verifica) {
			MyBranoDao.AggiungiBrano(Titolo,Formato, Integer.parseInt(Durata), Descrizione, Integer.parseInt(Canali), Integer.parseInt(Campionamento), Double.parseDouble(Dimensione), "https", MyUtente.getEmail());
			MyCreaElemento.dispose();
			MyMieiElementi.dispose();
			MyMieiElementi=new MieiElementi(this, MyHome);
			MyMieiElementi.setVisible(true);
			MyMieiElementi.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
		}
	}

	public void EliminaElemento(Brano b, MieiElementi Attuale) {
		MyBranoDao.EliminaBrano(b.getIdBrano());
		Attuale.dispose();
		Attuale=new MieiElementi(this, MyHome);
		Attuale.setVisible(true);
		Attuale.MostraElementi(MyBranoDao.CercaElementiPerEmail(MyUtente.getEmail()));
	}

	public  void AggiungiAccesso(Brano b) {
		if(!(b.getCreatore().getEmail().equals(MyUtente.getEmail()))) {
		MyAccessoDao.AggiungiAccesso(b.getIdBrano(),MyUtente.getEmail());
		}
	}
	public  void AggiungiAccedePubblica(Playlist p) {
		if(!(p.getCreatore().getEmail().equals(MyUtente.getEmail()))) {
			MyAccedePubblicaDao.AggiungiAccedePubblica( p.getId(),MyUtente.getEmail());
		}
	}
	public void ScegliPlaylist(JFrame Precedente,Brano b) {
		MycPl.ScegliPlaylist(Precedente, b);
	}

}


