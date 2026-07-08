package Dao;
import Entity.Utente;

public interface UtenteDao {
	public Utente ricercaPerEmail(String Email);
	
	public Utente getAutorePerEmail(String EmailIN);
}
