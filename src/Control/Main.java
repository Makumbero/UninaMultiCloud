package Control;



import Dao.ConnessioneDB;

public class Main {
	public static void main(String[] args) {

		ConnessioneDB con= new ConnessioneDB();
		con.connettiti();
		
		ControllerLogin theController= new ControllerLogin(con.getConnection());
		//GraficoVisualizzazioni v= new GraficoVisualizzazioni();
		//v.setVisible(true);
	
	}
}
