package Boundary;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerLogin;


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EmailTF;
	private JTextField PasswordTF;
	ControllerLogin c;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Login(ControllerLogin c) {
		this.c=c;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Email = new JLabel("Email:");
		Email.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Email.setBounds(241, 246, 112, 25);
		contentPane.add(Email);

		JLabel Password = new JLabel("Password:");
		Password.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Password.setBounds(241, 323, 112, 25);
		contentPane.add(Password);

		EmailTF = new JTextField();
		EmailTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		EmailTF.setBounds(365, 246, 276, 25);
		contentPane.add(EmailTF);
		EmailTF.setColumns(10);

		PasswordTF = new JTextField();
		PasswordTF.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		PasswordTF.setBounds(365, 323, 276, 25);
		contentPane.add(PasswordTF);
		PasswordTF.setColumns(10);

		JButton LoginBT = new JButton("Login");
		LoginBT.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		LoginBT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.verificaCredenziali(EmailTF.getText(),PasswordTF.getText());
			}
		});
		LoginBT.setBounds(356, 451, 190, 80);
		contentPane.add(LoginBT);

		JLabel Titolo = new JLabel("UninaMultiCloud");
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 45));
		Titolo.setBounds(289, 86, 400, 71);
		contentPane.add(Titolo);

	}
}
