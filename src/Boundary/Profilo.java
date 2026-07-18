package Boundary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerLogin;

public class Profilo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ControllerLogin c;


	public Profilo(ControllerLogin c) {
		this.c=c;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Visual = new JLabel("Visual:");
		Visual.setBounds(83, 55, 45, 13);
		contentPane.add(Visual);

		JLabel NVisual = new JLabel("0");
		NVisual.setBounds(119, 55, 44, 12);
		contentPane.add(NVisual);

		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.ReturnHome();
			}
		});
		btnNewButton.setBounds(60, 206, 84, 20);
		contentPane.add(btnNewButton);

		JLabel NomeProfilo = new JLabel("NomeProfilo");
		NomeProfilo.setBounds(10, 10, 186, 20);
		contentPane.add(NomeProfilo);

	}
}
