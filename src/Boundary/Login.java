package Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ControllerLogin;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(44, 50, 104, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(44, 100, 44, 12);
		contentPane.add(lblNewLabel_1);
		
		EmailTF = new JTextField();
		EmailTF.setBounds(158, 47, 96, 18);
		contentPane.add(EmailTF);
		EmailTF.setColumns(10);
		
		PasswordTF = new JTextField();
		PasswordTF.setBounds(158, 97, 96, 18);
		contentPane.add(PasswordTF);
		PasswordTF.setColumns(10);
		
		JButton LoginBT = new JButton("Login");
		LoginBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.verificaCredenziali(EmailTF.getText(),PasswordTF.getText());
			}
		});
		LoginBT.setBounds(170, 196, 84, 20);
		contentPane.add(LoginBT);

	}
}
