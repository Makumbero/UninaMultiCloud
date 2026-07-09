package Entity;
import java.util.Date;

public class Accesso {
		Utente MyUtente;
		int	IdAccesso;
		Date Data;
		Brano MyBrano;
		

		public Accesso(Utente myUtente, int idAccesso, Date data, Brano myBrano) {
			MyUtente=myUtente;
			IdAccesso = idAccesso;
			Data = data;
			MyBrano=myBrano;
		}

		public Utente getMyUtente() {
			return MyUtente;
		}

		public void setUtente(Utente u) {
			MyUtente=u;
		}

		public int getIdAccesso() {
			return IdAccesso;
		}

		public void setIdAccesso(int idAccesso) {
			IdAccesso = idAccesso;
		}

		public Date getData() {
			return Data;
		}

		public void setData(Date data) {
			this.Data = data;
		}

		public Brano getMyBrano() {
			return MyBrano;
		}

		public void setMyBrano(Brano myBrano) {
			MyBrano = myBrano;
		}


}
