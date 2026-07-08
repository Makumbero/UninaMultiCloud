package Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public  abstract class  Playlist {
	private int NumeroElementi;
	private LocalDate DataCreazione;
	private String Descrizione;
	private Utente Creatore;
	private ArrayList<Brano> Brani = new ArrayList<>();
	
	public Playlist(int numeroElementi, LocalDate dataCreazione, String descrizione, Utente creatore) {
		super();
		NumeroElementi = numeroElementi;
		DataCreazione = dataCreazione;
		Descrizione = descrizione;
		Creatore = creatore;
	}

	public int getNumeroElementi() {
		return NumeroElementi;
	}

	public void setNumeroElementi(int numeroElementi) {
		NumeroElementi = numeroElementi;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public LocalDate getDataCreazione() {
		return DataCreazione;
	}

	public Utente getCreatore() {
		return Creatore;
	}
	
	public void aggiungiBrano(Brano b) {
		Brani.add(b);
	}
	
	public void rimuoviBrano(Brano b) {
		Brani.remove(b);
	}
}

