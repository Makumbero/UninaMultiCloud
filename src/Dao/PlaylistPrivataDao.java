package Dao;

import java.util.List;

import Entity.Brano;
import Entity.PlaylistPrivata;
import Entity.Utente;

public interface PlaylistPrivataDao {
	public List<PlaylistPrivata> CercaPlaylistPrivataPerEmail(String EmailIN);
	
	public void AggiungiPlaylistPrivata(String TitoloIN, Utente CreatoreIN);
	
	public void EliminaPlaylist(int IdPrivataIN);
	
	public List<Brano> GetContiene(String IdPrivataIN);
	
	public void AggiungiaPrivata(int IdPrivataIN, int IdElementoIN);
	
	public void RimuoviElemento( int IdElementoIN,int IdPrivataIN);
	
	public  void SetTitolo(String TitoloIN, int IdPrivataIN);
	
	public  void SetDescrizione(String DescrizioneIN, int IdPrivataIN);

}
