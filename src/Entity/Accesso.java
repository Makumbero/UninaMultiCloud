package Entity;
import java.util.Date;

public class Accesso {
		Utente MyUtente;
		int	IdAccesso;
		Date Data;
		int IdElemento;
		

		public Accesso(Utente myUtente, int idAccesso, Date data, int idElemento) {
			MyUtente=myUtente;
			IdAccesso = idAccesso;
			Data = data;
			IdElemento = idElemento;
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

		public int getIdElemento() {
			return IdElemento;
		}

		public void setIdElemento(int idElemento) {
			IdElemento = idElemento;
		}
}
