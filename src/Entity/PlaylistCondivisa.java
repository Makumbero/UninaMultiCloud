package Entity;
import java.util.ArrayList;
import java.util.Date;

public class PlaylistCondivisa extends Playlist {
	private int IdCondivisa;
	private ArrayList<Utente> CondivisaCon= new ArrayList<>();
	
	public PlaylistCondivisa(String titolo,int numeroElementi, Date dataCreazione, String descrizione, Utente creatore, int idCondivisa) {
		super(titolo,numeroElementi, dataCreazione, descrizione, creatore);
		IdCondivisa=idCondivisa;
	}
	
	public void CondividiCon(Utente b) {
		CondivisaCon.add(b);
	}

	public ArrayList<Utente> getCondivisaCon() {
		return CondivisaCon;
	}

	public void setCondivisaCon(ArrayList<Utente> condivisaCon) {
		CondivisaCon = condivisaCon;
	}

	public int getIdCondivisa() {
		return IdCondivisa;
	}
	
}
