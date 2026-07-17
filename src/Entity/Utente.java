package Entity;

public class Utente {
	private String Username;
	private String Email;
	private String Password;

	public Utente(String username, String email, String password) {
		Username=username;
		Email=email;
		Password=password;
	}

	public String getEmail() {
		return Email;
	}

	public String getUsername() {
		return Username;
	}
	public String getPassword() {
		return Password;
	}

}
