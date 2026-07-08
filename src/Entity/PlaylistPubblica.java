package Entity;
import java.util.Date;

public class PlaylistPubblica extends Playlist {
	int IdPubblica;
	int Visualizzazioni;
	
	public PlaylistPubblica(int numeroElementi, Date dataCreazione, String descrizione, Utente creatore, int idPubblica, int visualizzazioni) {
		super(numeroElementi, dataCreazione, descrizione, creatore);
		IdPubblica=idPubblica;
		Visualizzazioni=visualizzazioni;
	}

	public int getVisualizzazioni() {
		return Visualizzazioni;
	}
	
	public void setVisualizzazioni(int visualizzazioni) {
		Visualizzazioni = visualizzazioni;
	}
	
	public int getIdPubblica() {
		return IdPubblica;
	}
	
}
