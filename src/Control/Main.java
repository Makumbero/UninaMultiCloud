package Control;



import java.awt.Color;

import javax.swing.UIManager;

import Dao.ConnessioneDB;

public class Main {
	public static void main(String[] args) {
		System.out.println("HOME AGGIORNATA - VERSIONE NUOVA");
		UIManager.put("Button.background", new Color(1,136,188));
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Panel.background", new Color(254,119,67));
		new Color(68, 217, 176);
		ConnessioneDB con= new ConnessioneDB();
		con.connettiti();
		
		ControllerLogin cLog= new ControllerLogin(con.getConnection());
	}
}
