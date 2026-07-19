package Control;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Boundary.Collaboratori;
import Boundary.CreaPlaylist;
import Boundary.Home;
import Boundary.ModificaPlaylist;
import Boundary.Raccolta;
import Boundary.ScegliPlaylist;
import Boundary.VisualizzaPlaylist;
import Dao.JDBCPlaylistCondivisaDao;
import Dao.JDBCPlaylistPrivataDao;
import Dao.JDBCPlaylistPubblicaDao;
import Dao.JDBCUtenteDao;
import Entity.Brano;
import Entity.Playlist;
import Entity.PlaylistCondivisa;
import Entity.PlaylistPrivata;
import Entity.PlaylistPubblica;
import Entity.Utente;

public class ControllerPlaylist {
	JDBCUtenteDao MyUtenteDao;
	Connection conn;
	ScegliPlaylist MyScegliPlaylist;
	Utente MyUtente;
	Home MyHome;
	ControllerLogin cLog;
	JDBCPlaylistCondivisaDao MyCondivisaDao;
	JDBCPlaylistPubblicaDao MyPubblicaDao;
	JDBCPlaylistPrivataDao MyPrivataDao;
	Raccolta MyRaccolta;
	CreaPlaylist MyCreaPlaylist;
	ControllerElementi MycEle;
	ControllerCerca MycCerca;
	VisualizzaPlaylist MyVisualizzaPlaylist;
	ModificaPlaylist MyModificaPlaylist;
	Collaboratori MyCollaboratori;

	public ControllerPlaylist(Connection conn1, Utente u, Home h, ControllerLogin cLog) {
		this.conn=conn1;
		MyUtente=u;
		MyHome=h;
		this.cLog=cLog;
		MyUtenteDao= new JDBCUtenteDao(conn);
		MyCondivisaDao= new JDBCPlaylistCondivisaDao(conn, this);
		MyPubblicaDao= new JDBCPlaylistPubblicaDao(conn, this);
		MyPrivataDao= new JDBCPlaylistPrivataDao(conn, this);
	}


	public Collaboratori getMyCollaboratori(){
		return MyCollaboratori;
	}

	public JDBCPlaylistCondivisaDao getMyCondivisaDao() {
		return MyCondivisaDao;
	}

	public Brano getElementoPerID(int IdElementoIN) {
		Brano b=MycEle.getElementoPerID(IdElementoIN);
		return b;

	}

	public Utente getAutorePerEmail(String EmailIN) {
		Utente u;

		u=MyUtenteDao.getAutorePerEmail(EmailIN);
		return u;

	}

	public void setControllerElementi(ControllerElementi cEle) {
		MycEle=cEle;
	}

	public void setControllerCerca(ControllerCerca cCerca) {
		MycCerca=cCerca;
	}

	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.dispose();
		Precedente.setVisible(true);
	}

	public void ScegliPlaylist(JFrame Precedente, Brano b) {
		Precedente.setVisible(false);
		MyScegliPlaylist=new ScegliPlaylist(this, Precedente, b);
		MyScegliPlaylist.setVisible(true);
		List <Playlist> ListaPlaylist= new ArrayList<>();
		ListaPlaylist.addAll(MyCondivisaDao.CercaPlaylistCondiviseAte(MyUtente.getEmail()));
		ListaPlaylist.addAll(MyCondivisaDao.CercaPlaylistCondivisaPerEmail(MyUtente.getEmail()));
		ListaPlaylist.addAll(MyPubblicaDao.CercaPlaylistPubblicaPerEmail(MyUtente.getEmail()));
		ListaPlaylist.addAll(MyPrivataDao.CercaPlaylistPrivataPerEmail(MyUtente.getEmail()));
		MyScegliPlaylist.MostraPlaylist(ListaPlaylist);
	}
	public void SalvaScegliPlaylist(JFrame ScegliPlaylist, JFrame Precedente, List<Playlist> ListaPlaylist, List<JCheckBox> ListaCheckBox, Brano b) {
		for(int i=0; i<ListaPlaylist.size(); i++) {
			if(ListaCheckBox.get(i).isSelected()) {
				if (ListaPlaylist.get(i) instanceof PlaylistPrivata) {
					MyPrivataDao.AggiungiaPrivata(ListaPlaylist.get(i).getId(), b.getIdBrano());
				} else if (ListaPlaylist.get(i) instanceof PlaylistPubblica) {
					MyPubblicaDao.AggiungiaPubblica(ListaPlaylist.get(i).getId(), b.getIdBrano());
				} else if (ListaPlaylist.get(i) instanceof PlaylistCondivisa) {
					MyCondivisaDao.AggiungiaCondivisa(ListaPlaylist.get(i).getId(), b.getIdBrano());
				}
			}
		}
		ScegliPlaylist.dispose();
		Precedente.setVisible(true);
	}
	public void HomeToRaccolta() {
	MyHome.setVisible(false);
	MyRaccolta=new Raccolta(this, MyHome);
	MyRaccolta.setVisible(true);
	List <Playlist> ListaPlaylist= new ArrayList<>();
	ListaPlaylist.addAll(MyCondivisaDao.CercaPlaylistCondiviseAte(MyUtente.getEmail()));
	ListaPlaylist.addAll(MyCondivisaDao.CercaPlaylistCondivisaPerEmail(MyUtente.getEmail()));
	ListaPlaylist.addAll(MyPubblicaDao.CercaPlaylistPubblicaPerEmail(MyUtente.getEmail()));
	ListaPlaylist.addAll(MyPrivataDao.CercaPlaylistPrivataPerEmail(MyUtente.getEmail()));
	MyRaccolta.MostraPlaylist(ListaPlaylist);
}
	public void RaccoltaToAggiungiPlaylist(){
		MyRaccolta.setVisible(false);
		MyCreaPlaylist=new CreaPlaylist(this,MyRaccolta);
		MyCreaPlaylist.setVisible(true);
	}

	public void VisualizzaPlaylist(JFrame Precedente, Playlist p) {
		List <Brano> Brani= new ArrayList<>();
		if(p instanceof PlaylistPubblica) {
			Brani.addAll(MyPubblicaDao.GetContiene(p.getId()));
			MycEle.AggiungiAccedePubblica(p);
		}
		else if(p instanceof PlaylistPrivata) {
			Brani.addAll(MyPrivataDao.GetContiene(p.getId()));
		}
		else  {
			Brani.addAll(MyCondivisaDao.GetContiene(p.getId()));
		}
		Precedente.setVisible(false);
		MyVisualizzaPlaylist= new VisualizzaPlaylist(p, Brani, this, MycEle, Precedente);
		MyVisualizzaPlaylist.setVisible(true);

	}
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerTitolo(String TitoloIN){
		return MyPubblicaDao.CercaPlaylistPubblicaPerTitolo(TitoloIN);
	}

	public void CreaPlaylist(int indiceTipoPlaylist,String TitoloIN, String DescrizioneIN) {
		boolean verifica=true;
		if(TitoloIN.trim().isEmpty() || TitoloIN.length()>40) {
			 JOptionPane.showMessageDialog(null, "Il titolo non può essere vuoto e deve contenere al massimo 40 caratteri.");
			 verifica=false;
		} 
		if(DescrizioneIN.length()>100) {
			JOptionPane.showMessageDialog(null, "La descrizione deve contenere al massimo 100 caratteri.");
			verifica=false;
		}
		if (verifica==true) {
			if(indiceTipoPlaylist==0) {
				MyPubblicaDao.AggiungiPlaylistPubblica(TitoloIN, MyUtente, DescrizioneIN);
				JOptionPane.showMessageDialog(null, "La Playlist è ora disponibile nella raccolta");
			}else if(indiceTipoPlaylist==1) {
				MyPrivataDao.AggiungiPlaylistPrivata(TitoloIN, MyUtente, DescrizioneIN);
				JOptionPane.showMessageDialog(null, "La Playlist è ora disponibile nella raccolta");
			}else {
				MyCondivisaDao.AggiungiPlaylistCondivisa(TitoloIN, MyUtente, DescrizioneIN);
				JOptionPane.showMessageDialog(null, "La Playlist è ora disponibile nella raccolta");
			}
			MyRaccolta.dispose();
			MyCreaPlaylist.dispose();
			this.HomeToRaccolta();
		}
	}
	public void EliminaPlaylist(JFrame Attuale, Playlist p) {
		if(p instanceof PlaylistPubblica) {
			MyPubblicaDao.EliminaPlaylist(p.getId());
		}
		else if(p instanceof PlaylistCondivisa) {
			if(p.getCreatore().getEmail().equals(MyUtente.getEmail())) {
				MyCondivisaDao.EliminaPlaylist(p.getId());
			}
			else {
				 JOptionPane.showMessageDialog(null, "Non sei il creatore di questa Playlist.");
			}
		}
		else {
			MyPrivataDao.EliminaPlaylist(p.getId());
		}

		Attuale.dispose();
		this.HomeToRaccolta();
	}

	public void ModificaPlaylist(JFrame Precedente, Playlist p) {
		Precedente.setVisible(false);
		List <Brano> Brani= new ArrayList<>();
		if(p instanceof PlaylistPubblica) {
			Brani.addAll(MyPubblicaDao.GetContiene(p.getId()));
		}
		else if(p instanceof PlaylistPrivata) {
			Brani.addAll(MyPrivataDao.GetContiene(p.getId()));
		}
		else  {
			Brani.addAll(MyCondivisaDao.GetContiene(p.getId()));
		}
		Precedente.setVisible(false);
		MyModificaPlaylist= new ModificaPlaylist(p, Brani, this, MycEle, Precedente);
		MyModificaPlaylist.setVisible(true);
	}

	public void RimuoviElemento(JFrame Attuale, JFrame Precedente, Playlist p, Brano b) {
		if(p instanceof PlaylistPubblica) {
			MyPubblicaDao.RimuoviElemento(b.getIdBrano(), p.getId());
		}
		else if(p instanceof PlaylistCondivisa) {
			MyCondivisaDao.RimuoviElemento(b.getIdBrano(), p.getId());
		}
		else {
			MyPrivataDao.RimuoviElemento(b.getIdBrano(), p.getId());
		}
	b=null;
	Attuale.dispose();
	this.ModificaPlaylist(Precedente, p);
	}

	public void SalvaModifiche(Playlist p, String Titolo, String Descrizione) {
		if(!(p.getTitolo().equals(Titolo))) {
			if(Titolo.trim().isEmpty() || Titolo.length()>40) {
				 JOptionPane.showMessageDialog(null, "Il titolo non può essere vuoto e deve contenere al massimo 40 caratteri. Non è stato modificato.");
			}
			else {
			p.setTitolo(Titolo);
			if(p instanceof PlaylistPubblica) {
				MyPubblicaDao.SetTitolo(Titolo, p.getId());
			}
			else if(p instanceof PlaylistCondivisa) {
				MyCondivisaDao.SetTitolo(Titolo, p.getId());
			}
			else {
				MyPrivataDao.SetTitolo(Titolo, p.getId());
			}
		}}

		if(!(p.getDescrizione().equals(Descrizione))) {
			if(Descrizione.length()>100) {
				JOptionPane.showMessageDialog(null, "La descrizione deve contenere al massimo 100 caratteri. Non è stata modificata.");
			}else {
				p.setDescrizione(Descrizione);
				if(p instanceof PlaylistPubblica) {
					MyPubblicaDao.SetDescrizione(Descrizione, p.getId());
				}
				else if(p instanceof PlaylistCondivisa) {
					MyCondivisaDao.SetDescrizione(Descrizione, p.getId());
				}
				else {
					MyPrivataDao.SetDescrizione(Descrizione, p.getId());
				}
			}
		}

		MyModificaPlaylist.dispose();
		MyRaccolta.dispose();
		this.HomeToRaccolta();
	}

	public List<PlaylistPubblica>getPlaylistPubblicaByAutore(String EmailIN) {
		return MyPubblicaDao.CercaPlaylistPubblicaPerEmail(EmailIN);
	}
	
	public List<PlaylistCondivisa>getPlaylistCondivisaByAutore(String EmailIN) {
		return MyCondivisaDao.CercaPlaylistCondivisaPerEmail(EmailIN);
	}
	
	public List<PlaylistPrivata>getPlaylistPrivataByAutore(String EmailIN) {
		return MyPrivataDao.CercaPlaylistPrivataPerEmail(EmailIN);
	}
	
	public List<Utente> CercaUtentiCondivisi(int IdCondivisaIN){
		return MyCondivisaDao.CercaUtentiCondivisi(IdCondivisaIN);
}
	public void Collaboratori(JFrame Precedente, Playlist p) {
		Precedente.setVisible(false);
		MyCollaboratori= new Collaboratori(Precedente, MycCerca, this);
		MyCollaboratori.setVisible(true);
		MyCollaboratori.MostraCollaboratori(MyCondivisaDao.CercaUtentiCondivisi(p.getId()),p);
	}

	public void RimuoviCollaboratore(JFrame Attuale, JFrame Precedente, Playlist p, Utente u) {
		MyCondivisaDao.RimuoviCondivisionePlaylist(p.getId(), u);
		Attuale.dispose();
		this.Collaboratori(Precedente, p);
	}
	public void AggiungiCollaboratore(int IdPubblicaIN, Utente u) {
		MyCondivisaDao.CondividiPlaylist(IdPubblicaIN,u);
	}
}


