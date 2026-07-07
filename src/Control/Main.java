package Control;
import java.awt.EventQueue;

import Dao.ConnessioneDB;
import Entity.Utente;

public class Main {
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); */
		ConnessioneDB con= new ConnessioneDB();
		con.connettiti();
		Utente u1= new Utente("lor","lor","lor");
		ControllerLogin theController= new ControllerLogin(con.getConnection());
		//GraficoVisualizzazioni v= new GraficoVisualizzazioni();
		//v.setVisible(true);
	
	}
}
