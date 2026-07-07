package Entity;
import java.time.LocalDate;

public class Accesso {
		String Email;
		int	IdAccesso;
		LocalDate Data;
		int IdElemento;
		
		public Accesso(String email, int idAccesso, LocalDate data, int idElemento) {
			super();
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

		public LocalDate getData() {
			return Data;
		}

		public void setData(LocalDate data) {
			this.Data = data;
		}

		public int getIdElemento() {
			return IdElemento;
		}

		public void setIdElemento(int idElemento) {
			IdElemento = idElemento;
		}
}
