package Control;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Boundary.Cerca;
import Boundary.CercaUtente;
import Boundary.Home;
import Boundary.MieiElementi;
import Boundary.RisultatiRicerca;
import Entity.Brano;
import Entity.Playlist;
import Entity.PlaylistCondivisa;
import Entity.PlaylistPubblica;
import Entity.Utente;

public class ControllerCerca {
	Connection conn;
	Home MyHome;
	Cerca MyCerca;
	RisultatiRicerca Risricerca;
	Utente MyUtente;
	JFrame Precedente;
	MieiElementi mieiElementi;
	CercaUtente MyCercaUtente;
	ControllerElementi cEle;
	ControllerPlaylist cPlay;
	ControllerLogin cLog;
	
	
	
	public ControllerCerca(Connection conn, Home MyHome,Utente MyUtente) {
		this.conn = conn;
		this.MyHome = MyHome;
		this.MyUtente=MyUtente;
		
	}

	public List<Utente> rimuoviUtentiIndesiderati(List<Utente> listaUtenti,Playlist playlist){
		List<Utente>utentiIndesiderati=new ArrayList<>();
		for(Utente u:listaUtenti) {
			if(u.getEmail().equals(MyUtente.getEmail())) {
				utentiIndesiderati.add(u);
			}
			List<Utente> listaCondivisioni= cPlay.CercaUtentiCondivisi(playlist.getId());
			for(Utente x: listaCondivisioni) {
				if(u.getEmail().equals(x.getEmail())){
					utentiIndesiderati.add(u);
				}
			}
		}
		listaUtenti.removeAll(utentiIndesiderati);
		return listaUtenti;
	}


	public void HomeToCerca() {
		MyHome.setVisible(false);
		MyCerca=new Cerca(this, MyHome);
		MyCerca.setVisible(true);
		Precedente=MyHome;
		
	}
	
	public void CercaToRisultatiRicerca(int indiceTipoRicerca, String Ricerca, JFrame Precedente) {
		Precedente.setVisible(false);
		Risricerca=new RisultatiRicerca(this,Precedente);
		Risricerca.setVisible(true);
		if (indiceTipoRicerca==0){
			Risricerca.MostraElementi(cEle.CercaElementiPerAutore(Ricerca), Ricerca);
		}else if(indiceTipoRicerca==1) {
			Risricerca.MostraElementi(cEle.CercaElementiPerTitolo(Ricerca), Ricerca);
		}else {
			List <Playlist> ListaPlaylist= new ArrayList<>();
			ListaPlaylist.addAll(cPlay.CercaPlaylistPubblicaPerTitolo(Ricerca));
			Risricerca.MostraPlaylist(ListaPlaylist, Ricerca);
		};
		Precedente=MyCerca;
		
	}
	
	public void CollaboratoriToCondividi(JFrame Precedente,Playlist playlist) {
		Precedente.setVisible(false);
		MyCercaUtente=new CercaUtente(this,Precedente, playlist);
		MyCercaUtente.setVisible(true);
	}
	
	public void CondividiToRisultati(String Ricerca, JFrame Precedente, Playlist playlist) {
		Precedente.setVisible(false);
		Risricerca=new RisultatiRicerca(this, Precedente);
		Risricerca.setVisible(true);
		Risricerca.MostraUser(cLog.CercaAutorePerNome(Ricerca), Ricerca,playlist);
	}
	
	public void ToPrecedente(JFrame Attuale, JFrame Precedente) {
		Attuale.dispose();
		Precedente.setVisible(true);
	}



	public Utente getMyUtente() {
		return MyUtente;
	}



	public void setMyUtente(Utente myUtente) {
		MyUtente = myUtente;
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


	public ControllerLogin getcLog() {
		return cLog;
	}


	public void setcLog(ControllerLogin cLog) {
		this.cLog = cLog;
	}


	public void VisualizzaElemento(Brano brano) {
		cEle.VisualizzaElemento(Risricerca, brano);
		
	}
	public void VisualizzaPlaylist(JFrame Precedente, Playlist p) {
		cPlay.VisualizzaPlaylist(Precedente,p);
	}
	
	public  void AggiungiAccesso(Brano brano) {
		cEle.AggiungiAccesso(brano);
	}
	
	public  void AggiungiAccedePubblica(Playlist p) {
		cEle.AggiungiAccedePubblica(p);
	}
}
