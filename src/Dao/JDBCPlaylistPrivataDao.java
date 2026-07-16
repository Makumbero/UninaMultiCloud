package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Control.ControllerPlaylist;
import Entity.Brano;
import Entity.PlaylistPrivata;
import Entity.Utente;

public class JDBCPlaylistPrivataDao implements PlaylistPrivataDao {
	Connection conn;
	ControllerPlaylist cPlaylist;
	public JDBCPlaylistPrivataDao(Connection conn, ControllerPlaylist cPlaylist) {
		this.conn = conn;
		this.cPlaylist=cPlaylist;
	}
	
	@Override
	public List<PlaylistPrivata> CercaPlaylistPrivataPerEmail(String EmailIN){
		List <PlaylistPrivata>listaPlaylist= new ArrayList<>();
		 String sql = "SELECT * FROM CercaPlaylistPrivataPerEmail(?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, EmailIN);
	
		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaPlaylist.add(new PlaylistPrivata(rs.getString("Titolo"), rs.getInt("NumeroElementi"), rs.getDate("DataCreazione"), rs.getString("Descrizione"), cPlaylist.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdPrivata")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaPlaylist;
	}
	
	@Override
	public List<Brano> GetContiene(int IdPrivataIN){
		List <Brano>listaBrani= new ArrayList<>();
		 String sql = "SELECT * FROM GetContienePerPlaylistPrivata(?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setInt(1, IdPrivataIN);
	
		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(cPlaylist.getElementoPerID(rs.getInt("IdElemento")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaBrani;
	 }
	
	
	@Override
	public void AggiungiPlaylistPrivata(String TitoloIN, Utente CreatoreIN,String DescrizioneIN){
		 String sql = "CALL AggiungiPlaylistPrivata(?,?,?)";
	
		    try (PreparedStatement PstmtAggiungiPlaylist = conn.prepareStatement(sql)) {
		        PstmtAggiungiPlaylist.setString(1, TitoloIN);
		        PstmtAggiungiPlaylist.setString(2, CreatoreIN.getEmail());
		        PstmtAggiungiPlaylist.setString(3, DescrizioneIN);
		        PstmtAggiungiPlaylist.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void EliminaPlaylist(int IdPrivataIN){
		 String sql = "CALL EliminaPlaylistPrivata(?)";
		    try (PreparedStatement pstmEliminaPlaylist = conn.prepareStatement(sql)) {
		        pstmEliminaPlaylist.setInt(1, IdPrivataIN);
		        pstmEliminaPlaylist.execute();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void AggiungiaPrivata(int IdPrivataIN, int IdElementoIN){
		 String sql = "CALL AggiungiaPrivata(?,?)";
	
		    try (PreparedStatement PstmtAggiungiElementi = conn.prepareStatement(sql)) {
		        PstmtAggiungiElementi.setInt(1, IdPrivataIN);
		        PstmtAggiungiElementi.setInt(2, IdElementoIN);
		        PstmtAggiungiElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void RimuoviElemento( int IdElementoIN,int IdPrivataIN){
		 String sql = "CALL RimuoviElementoPrivata(?,?)";
	
		    try (PreparedStatement pstmtRimuoviElemento = conn.prepareStatement(sql)) {
		        pstmtRimuoviElemento.setInt(1, IdElementoIN);
		        pstmtRimuoviElemento.setInt(2, IdPrivataIN);
		        pstmtRimuoviElemento.execute();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public  void SetTitolo(String TitoloIN, int IdPrivataIN) {
		String sql = "CALL SetTitoloPrivata(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, TitoloIN);
	        pstmt.setInt(2, IdPrivataIN);
	        pstmt.execute();        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
	
	@Override
	public  void SetDescrizione(String DescrizioneIN, int IdPrivataIN) {
		String sql = "CALL SetDescrizionePrivata(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, DescrizioneIN);
	        pstmt.setInt(2, IdPrivataIN);
	        pstmt.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
}

