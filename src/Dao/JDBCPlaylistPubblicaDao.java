package Dao;

import java.sql.Connection;

import Control.ControllerLogin;

public class JDBCPlaylistPubblicaDao implements PlaylistPubblicaDao{
	Connection conn;
	ControllerLogin cLogin;
	public JDBCPlaylistPubblicaDao(Connection conn, ControllerLogin cLogin) {
		this.conn = conn;
		this.cLogin=cLogin;
	}
}
