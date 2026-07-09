package Dao;

import java.util.List;

import Entity.Brano;
import Entity.PlaylistPubblica;
import Entity.Utente;

public interface PlaylistPubblicaDao {
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerTitolo(String TitoloIN);
	
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerAutore(String AutoreIN);
	
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerEmail(String EmailIN);
	
	public void AggiungiPlaylistPubblica(String TitoloIN, Utente CreatoreIN);
	
	public void EliminaPlaylist(int IdPubblicaIN);
	
	public List<Brano> GetContiene(int IdPubblicaIN);
	
	public void AggiungiaPubblica(int IdPubblicaIN, int IdElementoIN);
	
	public void RimuoviElemento( int IdElementoIN,int IdPubblicaIN);
	
	public  void SetTitolo(String TitoloIN, int IdPubblicaIN);
	
	public  void SetDescrizione(String DescrizioneIN, int IdPubblicaIN);
}
