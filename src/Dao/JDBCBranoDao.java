package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Control.ControllerLogin;
import Entity.Brano;
import Entity.Utente;

public class JDBCBranoDao implements BranoDao{
	Connection conn;
	ControllerLogin cLogin;
	public JDBCBranoDao(Connection conn, ControllerLogin cLogin) {
		this.conn = conn;
		this.cLogin=cLogin;
	}
	@Override
	public List<Brano> CercaElementiPerEmail(String EmailIN) {
		 List <Brano>listaBrani= new ArrayList<>();
		 String sql = "SELECT * FROM CercaElementiPerEmail(?)";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, EmailIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
			rs.getString("ImmagineCopertina"), rs.getInt("Visualizzazioni"), rs.getInt("Canali"), rs.getInt("Campionamento"), rs.getInt("IdElemento"), cLogin.getAutorePerEmail(EmailIN)));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaBrani;
	}
	
	@Override
	public List<Brano> CercaElementiPerTitolo(String TitoloIN) {
		 List <Brano>listaBrani= new ArrayList<>();
		 String sql = "SELECT * FROM CercaElementiPerTitolo(?)";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, TitoloIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
			rs.getString("ImmagineCopertina"), rs.getInt("Visualizzazioni"), rs.getInt("Canali"), rs.getInt("Campionamento"), rs.getInt("IdElemento"), cLogin.getAutorePerEmail(rs.getString("Email"))));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaBrani;
	}
	@Override
	public List<Brano> CercaElementiPerAutore(String AutoreIN) {
		 List <Brano>listaBrani= new ArrayList<>();
		 String sql = "SELECT * FROM CercaElementiPerAutore(?)";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, AutoreIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
			rs.getString("ImmagineCopertina"), rs.getInt("Visualizzazioni"), rs.getInt("Canali"), rs.getInt("Campionamento"), rs.getInt("IdElemento"), cLogin.getAutorePerEmail(rs.getString("Email"))));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaBrani;
	}
	
	@Override
	public void AggiungiBrano(
			String TitoloIN ,
			String FormatoIN,
			int DurataIN  ,
			String DescrizioneIN ,
			int CanaliIN,
			int CampionamentoIN,
			double DimensioniIN,
			String ImmagineCopertinaIN,
			String EmailIN ) {
		 String sql = " CALL AggiungiAudio(?,?,?,?,?,?,?,?,?)";

		    try (PreparedStatement PstmtAggiungiAudio = conn.prepareStatement(sql)) {
		        PstmtAggiungiAudio.setString(1, TitoloIN);
		        PstmtAggiungiAudio.setString(2, FormatoIN);
		        PstmtAggiungiAudio.setInt(3, DurataIN);
		        PstmtAggiungiAudio.setString(4, DescrizioneIN);
		        PstmtAggiungiAudio.setInt(5, CanaliIN);
		        PstmtAggiungiAudio.setInt(6, CampionamentoIN);
		        PstmtAggiungiAudio.setDouble(7, DimensioniIN);
		        PstmtAggiungiAudio.setString(8, ImmagineCopertinaIN);
		        PstmtAggiungiAudio.setString(9, EmailIN);
		        PstmtAggiungiAudio.execute();
		        
		            
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	

}
