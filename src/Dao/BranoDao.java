package Dao;

import java.util.ArrayList;
import java.util.List;

import Entity.Brano;
import Entity.Utente;

public interface BranoDao {
	public List<Brano> CercaElementiPerEmail(String EmailIN);
	
}
