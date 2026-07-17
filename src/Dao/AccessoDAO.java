package Dao;

import java.sql.Date;
import java.util.List;

import Entity.Accesso;

public interface AccessoDAO {
	public void AggiungiAccesso(int IdElementoIN, String EmailIN);

	public List<Accesso> GetAccessiPerElemento(int IdElementoIN);

	public List<Accesso> GetAccessiPerData(Date DataIN);
}
