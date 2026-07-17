package Entity;
import java.util.Date;

public class PlaylistPubblica extends Playlist {
	int IdPubblica;
	int Visualizzazioni;

	public PlaylistPubblica(String titolo,int numeroElementi, Date dataCreazione, String descrizione, Utente creatore, int idPubblica, int visualizzazioni) {
		super(titolo,numeroElementi, dataCreazione, descrizione, creatore);
		IdPubblica=idPubblica;
		Visualizzazioni=visualizzazioni;
	}

	@Override
	public int getVisualizzazioni() {
		return Visualizzazioni;
	}

	public void setVisualizzazioni(int visualizzazioni) {
		Visualizzazioni = visualizzazioni;
	}

	@Override
	public int getId() {
		return IdPubblica;
	}

}
