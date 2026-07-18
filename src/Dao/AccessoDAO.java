package Dao;

import java.sql.Date;
import java.util.List;

import Entity.Accesso;

public interface AccessoDAO {
	public void AggiungiAccesso(int IdElementoIN, String EmailIN);

	public List<Accesso> GetAccessiPerElemento(int IdElementoIN);

	public List<Accesso> GetAccessiPerData(Date DataIN);

	public List<Accesso> GetAccessiPerMese(Date DataIN);

	public List<Accesso> GetAccessiPerAnno(Date DataIN);

	public List<Accesso> GetAllAccessi();
}
