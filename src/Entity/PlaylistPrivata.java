package Entity;

import java.util.Date;

public class PlaylistPrivata extends Playlist {
	int IdPrivata;

	public PlaylistPrivata(String titolo,int numeroElementi, Date dataCreazione, String descrizione, Utente creatore, int idPrivata) {
		super(titolo,numeroElementi, dataCreazione, descrizione, creatore);
		IdPrivata=idPrivata;
	}
	
	public int getId() {
		return IdPrivata;
	}
	
}
