package Entity;
import java.util.Date;

public class Accesso {
		String Email;
		int	IdAccesso;
		Date Data;
		int IdElemento;
		

		public Accesso(String email, int idAccesso, Date data, int idElemento) {
			Email = email;
			IdAccesso = idAccesso;
			Data = data;
			IdElemento = idElemento;
		}

		public String getEmail() {
			return Email;
		}

		public void setEmail(String email) {
			Email = email;
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
