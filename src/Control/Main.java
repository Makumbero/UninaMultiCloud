package Control;



import java.awt.Color;

import javax.swing.UIManager;

import Dao.ConnessioneDB;

public class Main {
	public static void main(String[] args) {
		UIManager.put("Button.background", new Color(103,162,197));
		UIManager.put("Panel.background", new Color(207,235,255));
		new Color(68, 217, 176);
		ConnessioneDB con= new ConnessioneDB();
		con.connettiti();
		
		ControllerLogin cLog= new ControllerLogin(con.getConnection());
	}
}
