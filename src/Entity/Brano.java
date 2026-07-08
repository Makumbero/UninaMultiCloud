package Entity;

import java.time.LocalDate;
import java.util.Date;

public class Brano {
	private String Titolo;
	private String Formato;
	private int Durata;
	private String Descrizione;
	private Date DataCreazione;
	private Double Dimensione;
	private String ImmagineCopertina;
	private int Visualizzazioni;
	private int Canali;
	private int Campionamento;
	private int IdBrano;
	private Utente Creatore;
	

	public Brano(String titolo, String formato, int durata, Date dataCreazione, Double dimensione,
			String immagineCopertina, int visualizzazioni, int canali, int campionamento, int idBrano, Utente creatore) {
		Titolo = titolo;
		Formato = formato;
		Durata = durata;
		DataCreazione = dataCreazione;
		Dimensione = dimensione;
		ImmagineCopertina = immagineCopertina;
		Visualizzazioni = visualizzazioni;
		Canali = canali;
		Campionamento = campionamento;
		IdBrano = idBrano;
		Creatore= creatore;
	}

	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public String getFormato() {
		return Formato;
	}

	public void setFormato(String formato) {
		Formato = formato;
	}

	public int getDurata() {
		return Durata;
	}

	public void setDurata(int durata) {
		Durata = durata;
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

	public Double getDimensione() {
		return Dimensione;
	}

	public void setDimensione(Double dimensione) {
		Dimensione = dimensione;
	}

	public String getImmagineCopertina() {
		return ImmagineCopertina;
	}

	public void setImmagineCopertina(String immagineCopertina) {
		ImmagineCopertina = immagineCopertina;
	}

	public int getVisualizzazioni() {
		return Visualizzazioni;
	}

	public void setVisualizzazioni(int visualizzazioni) {
		Visualizzazioni = visualizzazioni;
	}

	public int getCanali() {
		return Canali;
	}

	public void setCanali(int canali) {
		Canali = canali;
	}

	public int getCampionamento() {
		return Campionamento;
	}

	public void setCampionamento(int campionamento) {
		Campionamento = campionamento;
	}

	public int getIdBrano() {
		return IdBrano;
	}

	public Utente getCreatore() {
		return Creatore;
	}
	
	
}

	
	
