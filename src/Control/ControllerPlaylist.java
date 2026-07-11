package Control;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import Boundary.*;
import Boundary.ScegliPlaylist;
import Dao.*;
import Dao.JDBCUtenteDao;
import Entity.*;

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
	ControllerElementi MycEle;
	VisualizzaPlaylist MyVisualizzaPlaylist;
	
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
				ListaPlaylist.get(i).aggiungiBrano(b);
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
}
	

