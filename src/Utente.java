
public class Utente {
	private String Username;
	private String Email;
	private String Password;
	
	public Utente(String Username, String Email, String Password) {
		this.Username=Username;
		this.setEmail(Email);
		this.Password=Password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
}
