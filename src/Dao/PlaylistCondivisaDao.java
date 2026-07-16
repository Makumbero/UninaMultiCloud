package Dao;

import java.util.List;

import Entity.Brano;
import Entity.PlaylistCondivisa;
import Entity.Utente;

public interface PlaylistCondivisaDao {
	
	public List<PlaylistCondivisa> CercaPlaylistCondivisaPerEmail(String EmailIN);
	
	public List<PlaylistCondivisa>  CercaPlaylistCondiviseAte(String EmailIN);
	
	public void  CondividiPlaylist(int IdCondivisaIN, Utente UtenteIN);
	
	public void  RimuoviCondivisionePlaylist(int IdCondivisaIN, Utente UtenteIN);
	
	public List<Brano> GetContiene(int IdCondivisaIN);
	
	public void AggiungiPlaylistCondivisa(String TitoloIN, Utente CreatoreIN, String DescrizioneIN);
	
	public void EliminaPlaylist(int IdcondivisaIN);
	
	public void AggiungiaCondivisa(int IdCondivisaIN, int IdElementoIN);
	
	public void RimuoviElemento( int IdElementoIN,int IdCondivisaIN);
	
	public  void SetTitolo(String TitoloIN, int IdCondivisaIN);
	
	public  void SetDescrizione(String DescrizioneIN, int IdCondivisaIN);

	List<Utente> CercaUtentiCondivisi(int IdCondivisaIN);

}
