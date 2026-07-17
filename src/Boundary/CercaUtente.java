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

import Control.ControllerCerca;
import Entity.Playlist;

public class CercaUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textRicerca;
	ControllerCerca cCerca;
	JFrame Precedente;
	Playlist Playlist;
	public CercaUtente(ControllerCerca cCerca,JFrame Precedente,Playlist Playlist) {
		this.cCerca=cCerca;
		this.Precedente=Precedente;
		this.Playlist=Playlist;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);



		JButton btnIndietro = new JButton("Indietro");
        btnIndietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        btnIndietro.setBounds(143, 498, 190, 80);
		btnIndietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCerca.ToPrecedente(CercaUtente.this,Precedente);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnIndietro);

		textRicerca = new JTextField();
		textRicerca.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textRicerca.setBounds(236, 253, 424, 35);
		contentPane.add(textRicerca);
		textRicerca.setColumns(10);

		JButton btnCerca = new JButton("Cerca");
        btnCerca.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        btnCerca.setBounds(579, 498, 190, 80);
		btnCerca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cCerca.CondividiToRisultati(textRicerca.getText(), CercaUtente.this,Playlist);
			}
		});
		contentPane.add(btnCerca);

		JLabel InserirePersona = new JLabel("Si prega di inserire il nome utente");
		InserirePersona.setFont(new Font("Tahoma", Font.PLAIN, 23));
		InserirePersona.setBounds(259, 197, 403, 35);
		contentPane.add(InserirePersona);

	}
}
