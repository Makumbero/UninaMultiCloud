package Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlaylistCondivisa extends Playlist {
	private int IdCondivisa;
	private ArrayList<Utente> CondivisaCon= new ArrayList<>();
	
	public PlaylistCondivisa(int numeroElementi, LocalDate dataCreazione, String descrizione, Utente creatore) {
		super(numeroElementi, dataCreazione, descrizione, creatore);
	}
	
}
