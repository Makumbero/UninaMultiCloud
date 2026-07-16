package Dao;
import java.util.List;

import Entity.Utente;

public interface UtenteDao {
	public List<Utente> CercaAutorePerNome(String UsernameIN);
	
	public Utente ricercaPerEmail(String Email);
	
	public boolean VerificaUtente(String Email, String Password);
	
	public Utente getAutorePerEmail(String EmailIN);

}
