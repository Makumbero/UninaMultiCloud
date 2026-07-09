package Dao;

import java.util.List;

import Entity.Accesso;

public interface AccessoDAO {
	public void AggiungiAccesso(int IdElementoIN, String EmailIN);
	
	public List<Accesso> GetAccessiPerElemento(int IdElementoIN);
	
	public List<Accesso> GetAccessiPerData(int DataIN);
}
