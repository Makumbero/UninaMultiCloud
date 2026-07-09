package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Control.ControllerPlaylist;
import Entity.PlaylistCondivisa;
import Entity.Utente;

public class JDBCPlaylistCondivisaDao implements PlaylistCondivisaDao{
	Connection conn;
	ControllerPlaylist cPlaylist;
	public JDBCPlaylistCondivisaDao(Connection conn, ControllerPlaylist cPlaylist) {
		this.conn = conn;
		this.cPlaylist=cPlaylist;
	}
	@Override
	public List<PlaylistCondivisa> CercaPlaylistCondivisaPerEmail(String EmailIN){
		List <PlaylistCondivisa>listaPlaylist= new ArrayList<>();
		 String sql = "SELECT * FROM CercaPlaylistCondivisaPerEmail(?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, EmailIN);
	
		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaPlaylist.add(new PlaylistCondivisa(rs.getString("Titolo"), rs.getInt("NumeroElementi"), rs.getDate("DataCreazione"), rs.getString("Descrizione"), cPlaylist.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdCondivisa")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaPlaylist;
	}
	
	@Override
	public List<PlaylistCondivisa>  CercaPlaylistCondiviseAte(String EmailIN){
		List <PlaylistCondivisa>listaPlaylist= new ArrayList<>();
		 String sql = "SELECT * FROM  CercaPlaylistCondiviseAte(?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, EmailIN);
	
		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaPlaylist.add(new PlaylistCondivisa(rs.getString("Titolo"), rs.getInt("NumeroElementi"), rs.getDate("DataCreazione"), rs.getString("Descrizione"), cPlaylist.getAutorePerEmail(rs.getString("Email")),rs.getInt("IdCondivisa")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listaPlaylist;
	}
	
	@Override
	public void  CondividiPlaylist(int IdCondivisaIN, Utente UtenteIN){
		 String sql = "CALL  CondividiPlaylist(?,?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setInt(1, IdCondivisaIN);
		        PstmtCercaElementi.setString(2, UtenteIN.getEmail());
		        PstmtCercaElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void  RimuoviCondivisionePlaylist(int IdCondivisaIN, Utente UtenteIN){
		 String sql = "CALL  RimuoviCondivisionePlaylist(?,?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setInt(1, IdCondivisaIN);
		        PstmtCercaElementi.setString(2, UtenteIN.getEmail());
		        PstmtCercaElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void AggiungiPlaylistCondivisa(String TitoloIN, Utente CreatoreIN){
		 String sql = "CALL AggiungiPlaylistCondivisa(?,?)";
	
		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, TitoloIN);
		        PstmtCercaElementi.setString(2, CreatoreIN.getEmail());
		        PstmtCercaElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void EliminaPlaylist(int IdcondivisaIN){
		 String sql = "CALL EliminaPlaylistCondivisa(?)";
		    try (PreparedStatement pstmEliminaPlaylist = conn.prepareStatement(sql)) {
		        pstmEliminaPlaylist.setInt(1, IdcondivisaIN);
		        pstmEliminaPlaylist.execute();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void AggiungiaCondivisa(int IdCondivisaIN, int IdElementoIN){
		 String sql = "CALL AggiungiaCondivisa(?,?)";
	
		    try (PreparedStatement PstmtAggiungiElementi = conn.prepareStatement(sql)) {
		        PstmtAggiungiElementi.setInt(1, IdCondivisaIN);
		        PstmtAggiungiElementi.setInt(2, IdElementoIN);
		        PstmtAggiungiElementi.execute();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public void RimuoviElemento( int IdElementoIN,int IdCondivisaIN){
		 String sql = "CALL RimuoviElementocondivisa(?,?)";
	
		    try (PreparedStatement pstmtRimuoviElemento = conn.prepareStatement(sql)) {
		        pstmtRimuoviElemento.setInt(1, IdElementoIN);
		        pstmtRimuoviElemento.setInt(2, IdCondivisaIN);
		        pstmtRimuoviElemento.execute();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	@Override
	public  void SetTitolo(String TitoloIN, int IdCondivisaIN) {
		String sql = "CALL SetTitoloCondivisa(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, TitoloIN);
	        pstmt.setInt(2, IdCondivisaIN);
	        pstmt.execute();        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
	
	@Override
	public  void SetDescrizione(String DescrizioneIN, int IdCondivisaIN) {
		String sql = "CALL SetDescrizioneCondivisa(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, DescrizioneIN);
	        pstmt.setInt(2, IdCondivisaIN);
	        pstmt.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	 
	}
}

