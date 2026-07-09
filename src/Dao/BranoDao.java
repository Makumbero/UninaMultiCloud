package Dao;

import java.util.ArrayList;
import java.util.List;

import Entity.Brano;
import Entity.Utente;

public interface BranoDao {
	public List<Brano> CercaElementiPerEmail(String EmailIN);
	
	public List<Brano> CercaElementiPerTitolo(String Titolo);
	
	public List<Brano> CercaElementiPerAutore(String AutoreIN);
	
	public void AggiungiBrano(
			String TitoloIN ,
			String FormatoIN,
			int DurataIN  ,
			String DescrizioneIN ,
			int CanaliIN,
			int CampionamentoIN,
			double DimensioniIN,
			String ImmagineCopertinaIN,
			String EmailIN );
	
	public  void EliminaBrano(int IdElementoIN);
	
	public  void SetTitolo(String TitoloIN, int IdElementoIN);
	
	public  void SetFormato(String FormatoIN, int IdElementoIN);
	
	public  void SetDurata(int DurataIN, int IdElementoIN);
	
	public  void Setdescrizione(String DescrizioneIN, int IdElementoIN);
	
	public  void SetCanali(int CanaliIN, int IdElementoIN);
	
	public  void SetCampionamento(int CampionamentoIN, int IdElementoIN);
	
	public  void SetDimensioni(double DimensioniIN, int IdElementoIN);
	
	public  void SetImmaginecopertina(String ImmaginecopertinaIN, int IdElementoIN);
}

