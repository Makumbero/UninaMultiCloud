package Control;

import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Boundary.CreaBrano;
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
	CreaBrano MyCreaElemento;
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
	public List<Brano> CercaElementiPerEmail(String AutoreIN) {
		return MyBranoDao.CercaElementiPerEmail(AutoreIN);
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
		int durata = 0;
		if(!(b.getTitolo().equals(Titolo))) {
			if(Titolo.trim().isEmpty() || Titolo.length()>40) {
				 JOptionPane.showMessageDialog(null, "Il titolo non può essere vuoto e deve contenere al massimo 40 caratteri. Non è stato modificato.");
			}
			else {
			MyBranoDao.SetTitolo(Titolo, b.getIdBrano());
		}}

		if(!(b.getFormato().equals(Formato))) {
			if(Formato.trim().isEmpty() || Formato.length()>10) {
				 JOptionPane.showMessageDialog(null, "Il formato non può essere vuoto e deve contenere al massimo 10 caratteri. Non è stato modificato.");
			}
			else {
			MyBranoDao.SetFormato(Formato, b.getIdBrano());
		}}

		if (!(b.getDescrizione().equals(Descrizione))) {
			if(Descrizione.length()>100) {
				JOptionPane.showMessageDialog(null,"La descrizione deve contenere al massimo 100 caratteri. Non è stata modificata.");
			}
			else {
			MyBranoDao.Setdescrizione(Descrizione, b.getIdBrano());
		}}

		try {
			 durata=this.StringaInSecondi(Durata);
		    if(durata!=b.getDurata()) {
				MyBranoDao.SetDurata(durata, b.getIdBrano());
			}
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "La durata deve essere inserita in formato (mm:ss) non è stata modificata.");
		}

		try {
		    double CatchDimensione = Double.parseDouble(Dimensione);
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
		MyCreaElemento=new CreaBrano(Precedente, this);
		MyCreaElemento.setVisible(true);
	}

	public void SalvaCreazione(String Titolo, String Formato, String Descrizione, String Durata, String Dimensione, String Canali, String Campionamento) {
		boolean verifica=true;
		int durata = 0;
		if(Titolo.trim().isEmpty() || Titolo.length()>40) {
			 JOptionPane.showMessageDialog(null, "Il titolo non può essere vuoto e deve contenere al massimo 40 caratteri.");
			 verifica=false;
		}

		if(Formato.trim().isEmpty() || Formato.length()>10) {
			 JOptionPane.showMessageDialog(null, "Il formato non può essere vuoto e deve contenere al massimo 10 caratteri.");
			 verifica=false;
		}
		
		if(Descrizione.length()>100) {
			JOptionPane.showMessageDialog(null,"La descrizione deve contenere al massimo 100 caratteri.");
			verifica=false;
		}

		try {
		     durata=this.StringaInSecondi(Durata);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "La durata deve essere inserita in formato (mm:ss)");
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
			MyBranoDao.AggiungiBrano(Titolo,Formato, durata, Descrizione, Integer.parseInt(Canali), Integer.parseInt(Campionamento), Double.parseDouble(Dimensione), "https", MyUtente.getEmail());
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
		b.setVisualizzazioni(b.getVisualizzazioni()+1);
		}
	}
	public  void AggiungiAccedePubblica(Playlist p) {
		if(!(p.getCreatore().getEmail().equals(MyUtente.getEmail()))) {
			MyAccedePubblicaDao.AggiungiAccedePubblica( p.getId(),MyUtente.getEmail());
			p.setVisualizzazioni(p.getVisualizzazioni()+1);
		}
	}
	public void ScegliPlaylist(JFrame Precedente,Brano b) {
		MycPl.ScegliPlaylist(Precedente, b);
	}
	public  int StringaInSecondi(String orarioIN) {
        String[] orario = orarioIN.trim().split(":");// separa la stringa in due con il carattere :
        int minuti = Integer.parseInt(orario[0]);
        int secondi = Integer.parseInt(orario[1]);
        if(minuti>60||secondi>60) {
          throw new IllegalArgumentException("sono stati inseriti valori maggiori dei minuti o dei secondi");
        }
        return minuti * 60 + secondi;
    }

    public String SecondiInStringa(int SecondiTotali) {
        return String.format("%d:%02d", SecondiTotali / 60, SecondiTotali % 60); //formatta da int a stringa, divide per sessanta per ottenere i minuti, e il resto della divisione diventano i secondi
    }

}


