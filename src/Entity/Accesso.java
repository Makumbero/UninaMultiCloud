package Entity;
import java.time.LocalDate;

public class Accesso {
		String Email;
		int	IdAccesso;
		LocalDate data;
		int IdElemento;
		
		public Accesso(String email, int idAccesso, int idElemento) {
			super();
			Email = email;
			IdAccesso = idAccesso;
			this.data = LocalDate.now();
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
			return data;
		}

		public void setData(LocalDate data) {
			this.data = data;
		}

		public int getIdElemento() {
			return IdElemento;
		}

		public void setIdElemento(int idElemento) {
			IdElemento = idElemento;
		}
}
