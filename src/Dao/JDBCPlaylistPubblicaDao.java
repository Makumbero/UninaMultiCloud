package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Control.ControllerPlaylist;
import Entity.Brano;
import Entity.PlaylistPubblica;
import Entity.Utente;

public class JDBCPlaylistPubblicaDao implements PlaylistPubblicaDao{
	Connection conn;
	ControllerPlaylist cPlaylist;
	public JDBCPlaylistPubblicaDao(Connection conn, ControllerPlaylist cPlaylist) {
		this.conn = conn;
		this.cPlaylist=cPlaylist;
	}
	@Override
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerTitolo(String TitoloIN){
		List <PlaylistPubblica>listaPlaylist= new ArrayList<>();
		 String sql = "SELECT * FROM CercaPlaylistPubblicaPerTitolo(?)";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, TitoloIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaPlaylist.add(new PlaylistPubblica(rs.getString("Titolo"), rs.getInt("NumeroElementi"), rs.getDate("DataCreazione"), rs.getString("Descrizione"), cPlaylist.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdPubblica"),rs.getInt("Visualizzazioni")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaPlaylist;
	}
	@Override
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerAutore(String AutoreIN){
		List <PlaylistPubblica>listaPlaylist= new ArrayList<>();
		 String sql = "SELECT * FROM CercaPlaylistPubblicaPerAutore(?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, AutoreIN);
	
		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaPlaylist.add(new PlaylistPubblica(rs.getString("Titolo"), rs.getInt("NumeroElementi"), rs.getDate("DataCreazione"), rs.getString("Descrizione"), cPlaylist.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdPubblica"),rs.getInt("Visualizzazioni")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaPlaylist;
	}
	@Override
	public List<PlaylistPubblica> CercaPlaylistPubblicaPerEmail(String EmailIN){
		List <PlaylistPubblica>listaPlaylist= new ArrayList<>();
		 String sql = "SELECT * FROM CercaPlaylistPubblicaPerEmail(?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, EmailIN);
	
		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaPlaylist.add(new PlaylistPubblica(rs.getString("Titolo"), rs.getInt("NumeroElementi"), rs.getDate("DataCreazione"), rs.getString("Descrizione"), cPlaylist.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdPubblica"),rs.getInt("Visualizzazioni")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaPlaylist;
	}
	@Override
	public void AggiungiPlaylistPubblica(String TitoloIN, Utente CreatoreIN){
		 String sql = "CALL AggiungiPlaylistPubblica(?,?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, TitoloIN);
		        PstmtCercaElementi.setString(2, CreatoreIN.getEmail());
		        PstmtCercaElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void EliminaPlaylist(int IdPubblicaIN){
		 String sql = "CALL EliminaPlaylistPubblica(?)";
		    try (PreparedStatement pstmEliminaPlaylist = conn.prepareStatement(sql)) {
		        pstmEliminaPlaylist.setInt(1, IdPubblicaIN);
		        pstmEliminaPlaylist.execute();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void AggiungiaPubblica(int IdPubblicaIN, int IdElementoIN){
		 String sql = "CALL AggiungiaPubblica(?,?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setInt(1, IdPubblicaIN);
		        PstmtCercaElementi.setInt(2, IdElementoIN);
		        PstmtCercaElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void RimuoviElemento( int IdElementoIN,int IdPubblicaIN){
		 String sql = "CALL RimuoviElementoPubblica(?,?)";
	
		    try (PreparedStatement pstmtRimuoviElemento = conn.prepareStatement(sql)) {
		        pstmtRimuoviElemento.setInt(1, IdElementoIN);
		        pstmtRimuoviElemento.setInt(2, IdPubblicaIN);
		        pstmtRimuoviElemento.execute();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public  void SetTitolo(String TitoloIN, int IdPubblicaIN) {
		String sql = "CALL SetTitoloPubblica(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, TitoloIN);
	        pstmt.setInt(2, IdPubblicaIN);
	        pstmt.execute();        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
	
	@Override
	public  void SetDescrizione(String DescrizioneIN, int IdPubblicaIN) {
		String sql = "CALL SetDescrizione(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, DescrizioneIN);
	        pstmt.setInt(2, IdPubblicaIN);
	        pstmt.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
}
