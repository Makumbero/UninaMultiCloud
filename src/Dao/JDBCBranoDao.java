package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Control.ControllerLogin;
import Entity.Brano;

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
		 String sql = "SELECT * FROM CercaElementiPerEmail(?) c WHERE c.Tipo='Audio'";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, EmailIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"),rs.getString("Descrizione"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
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
		 String sql = "SELECT * FROM CercaElementiPerTitolo(?)  c WHERE c.Tipo='Audio'";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, TitoloIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"),rs.getString("Descrizione"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
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
		 String sql = "SELECT * FROM CercaElementiPerAutore(?)  c WHERE c.Tipo='Audio'";

		    try (PreparedStatement PstmtCercaElementi = conn.prepareStatement(sql)) {
		        PstmtCercaElementi.setString(1, AutoreIN);

		        try (ResultSet rs = PstmtCercaElementi.executeQuery()) {
		            while(rs.next()) {
		            	listaBrani.add(new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"), rs.getString("Descrizione"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
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

	@Override
	public  void EliminaBrano(int IdElementoIN) {
		String sql = "CALL EliminaElemento(?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	//Getter e setter

	public Brano GetElementoPerId(int IdElementoIN) {
		Brano b=null;
		 String sql = "SELECT * FROM GetElementoPerId(?)";

		    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
		        pstm.setInt(1, IdElementoIN);

		        try (ResultSet rs = pstm.executeQuery()) {
		            while(rs.next()) {
		            	b=new Brano(rs.getString("Titolo"), rs.getString("Formato"), rs.getInt("Durata"), rs.getString("Descrizione"), rs.getDate("Datacreazione"), rs.getDouble("Dimensioni"),
			rs.getString("ImmagineCopertina"), rs.getInt("Visualizzazioni"), rs.getInt("Canali"), rs.getInt("Campionamento"), rs.getInt("IdElemento"), cLogin.getAutorePerEmail(rs.getString("Email")));
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return b;
	}
	@Override
	public  void SetTitolo(String TitoloIN, int IdElementoIN) {
		String sql = "CALL SetTitolo(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, TitoloIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void SetFormato(String FormatoIN, int IdElementoIN) {
		String sql = "CALL SetFormato(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, FormatoIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void SetDurata(int DurataIN, int IdElementoIN) {
		String sql = "CALL SetDurata(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, DurataIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void Setdescrizione(String DescrizioneIN, int IdElementoIN) {
		String sql = "CALL SetDescrizione(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, DescrizioneIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void SetCanali(int CanaliIN, int IdElementoIN) {
		String sql = "CALL SetCanali(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, CanaliIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void SetCampionamento(int CampionamentoIN, int IdElementoIN) {
		String sql = "CALL SetCampionamento(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, CampionamentoIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void SetDimensioni(double DimensioniIN, int IdElementoIN) {
		String sql = "CALL SetDimensioni(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setDouble(1, DimensioniIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}

	@Override
	public  void SetImmaginecopertina(String ImmaginecopertinaIN, int IdElementoIN) {
		String sql = "CALL SetImmaginecopertina(?,?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, ImmaginecopertinaIN);
	        pstmt.setInt(2, IdElementoIN);
	        pstmt.execute();




	    } catch (SQLException e) {
	        e.printStackTrace();
	    }


	}
}
