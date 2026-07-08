package Dao;
import Entity.Utente;

public interface UtenteDao {
	public Utente ricercaPerEmail(String Email);
	
	public boolean VerificaUtente(String Email, String Password);
	
	public Utente getAutorePerEmail(String EmailIN);

	void AggiungiAccesso(int IdElementoIN, String EmailIN);
}
