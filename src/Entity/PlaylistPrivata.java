package Entity;

import java.util.Date;

public class PlaylistPrivata extends Playlist {
	int IdPrivata;

	public PlaylistPrivata(int numeroElementi, Date dataCreazione, String descrizione, Utente creatore, int idPrivata) {
		super(numeroElementi, dataCreazione, descrizione, creatore);
		IdPrivata=idPrivata;
	}
	
	
}
