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
	
}
