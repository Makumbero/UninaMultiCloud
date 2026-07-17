package Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  abstract class  Playlist {
	private String Titolo;


	private int NumeroElementi;
	private Date DataCreazione;
	private String Descrizione;
	private Utente Creatore;
	private List<Brano> Brani = new ArrayList<>();

	public Playlist(String titolo,int numeroElementi, Date dataCreazione, String descrizione, Utente creatore) {
		Titolo=titolo;
		NumeroElementi = numeroElementi;
		DataCreazione = dataCreazione;
		Descrizione = descrizione;
		Creatore = creatore;
	}

	public String getTitolo() {
		return Titolo;
	}

	public int getVisualizzazioni() {
		return 0;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
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

	public Date getDataCreazione() {
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


	public int getId() {
		return 0;
	}
}

