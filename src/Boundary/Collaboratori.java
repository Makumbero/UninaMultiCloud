package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerCerca;
import Control.ControllerPlaylist;
import Entity.Brano;
import Entity.Playlist;
import Entity.Utente;

public class Collaboratori extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame Precedente;
	private JPanel PannelloUtenti;
	ControllerCerca MycCerca;
	ControllerPlaylist MycPl;
	/**
	 * Create the frame.
	 */
	public Collaboratori(JFrame precedente, ControllerCerca mycCerca, ControllerPlaylist mycPl) {
		Precedente=precedente;
		MycCerca=mycCerca;
		MycPl=mycPl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Indietro = new JButton("Indietro");
		Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Indietro.setBounds(143, 498, 190, 80);
		contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycCerca.ToPrecedente(Collaboratori.this, Precedente);
			}
		});
		
		JButton Condividi = new JButton("Condividi");
		Condividi.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		Condividi.setBounds(574, 498, 190, 80);
		contentPane.add(Condividi);
        Condividi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycCerca.CollaboratoriToCondividi(Collaboratori.this);
			}
		});
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(143, 103, 624, 370);
		contentPane.add(Scorrimento);
		
        PannelloUtenti = new JPanel();
		Scorrimento.setViewportView(PannelloUtenti);
	}
	
	public void MostraCollaboratori(List <Utente> Utenti, Playlist playlist) {
		 PannelloUtenti.removeAll();

		    PannelloUtenti.setLayout(new BoxLayout(PannelloUtenti, BoxLayout.Y_AXIS));

		    for (Utente utente : Utenti) {

		        JPanel riga = new JPanel(new BorderLayout());
		        riga.setMaximumSize(new Dimension(PannelloUtenti.getWidth(), 50));

		        JLabel username = new JLabel(utente.getUsername());
		        username.setFont(new Font("Lucida Grande", Font.PLAIN, 20));


		        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		        
		        JButton Rimuovi = new JButton("Rimuovi");
		        Rimuovi.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		        
		        pulsanti.add(Rimuovi);
		        Rimuovi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MycPl.RimuoviCollaboratore(Collaboratori.this, Precedente, playlist, utente);
					}
				});
		        
		        riga.add(username, BorderLayout.CENTER);
		        riga.add(pulsanti, BorderLayout.EAST);

		        PannelloUtenti.add(riga);
	}

}}
