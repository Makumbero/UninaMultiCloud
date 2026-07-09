package Entity;

import java.util.Date;

public class AccedeCondivisa {
	Utente MyUtente;
	PlaylistCondivisa MyPlaylist;
	Date Data;
	int IdAccessoCondivisa;
	
	public AccedeCondivisa(Utente MyUtente, PlaylistCondivisa MyPlaylist, Date Data, int IdAccessoCondivisa) {
		this.MyUtente=MyUtente;
		this.MyPlaylist=MyPlaylist;
		this.Data=Data;
		this.IdAccessoCondivisa=IdAccessoCondivisa;
	}

	public Utente getMyUtente() {
		return MyUtente;
	}

	public void setMyUtente(Utente myUtente) {
		MyUtente = myUtente;
	}

	public PlaylistCondivisa getMyPlaylist() {
		return MyPlaylist;
	}

	public void setMyPlaylist(PlaylistCondivisa myPlaylist) {
		MyPlaylist = myPlaylist;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public int getIdAccessoCondivisa() {
		return IdAccessoCondivisa;
	}

	public void setIdAccessoCondivisa(int idAccessoCondivisa) {
		IdAccessoCondivisa = idAccessoCondivisa;
	}
	
	
}
