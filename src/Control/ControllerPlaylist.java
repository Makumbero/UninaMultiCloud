package Control;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Boundary.Home;
import Boundary.ScegliPlaylist;
import Dao.*;
import Dao.JDBCUtenteDao;
import Entity.Brano;
import Entity.Playlist;
import Entity.Utente;

public class ControllerPlaylist {
	JDBCUtenteDao UtenteDAO;
	Connection conn;
	ScegliPlaylist MyScegliPlaylist;
	Utente MyUtente;
	Home MyHome;
	ControllerLogin cLog;
	JDBCPlaylistCondivisaDao MyCondivisaDao;
	JDBCPlaylistPubblicaDao MyPubblicaDao;
	
	public ControllerPlaylist(Connection conn1, Utente u, Home h, ControllerLogin cLog) {
		this.conn=conn1;
		MyUtente=u;
		MyHome=h;
		this.cLog=cLog;
		MyCondivisaDao=new JDBCPlaylistCondivisaDao(conn, this);
		MyPubblicaDao= new JDBCPlaylistPubblicaDao(conn, this);
	}
	
	public Utente getAutorePerEmail(String EmailIN) {
		Utente u;
		u=UtenteDAO.getAutorePerEmail(EmailIN);
		return u;
		
	}
	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.dispose();
		Precedente.setVisible(true);
	}
	
	public void ScegliPlaylist(JFrame Precedente, Brano b) {
		Precedente.setVisible(false);
		MyScegliPlaylist=new ScegliPlaylist(this, Precedente);
		MyScegliPlaylist.setVisible(true);
		List <Playlist> ListaPlaylist= new ArrayList<>();
		ListaPlaylist.addAll(MyCondivisaDao.CercaPlaylistCondiviseAte(MyUtente.getEmail()));
		ListaPlaylist.addAll(MyPubblicaDao.CercaPlaylistPubblicaPerEmail(MyUtente.getEmail()));
		MyScegliPlaylist.MostraPlaylist(ListaPlaylist);
	}
}
	

