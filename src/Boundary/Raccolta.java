package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.ControllerElementi;
import Control.ControllerPlaylist;
import Entity.Brano;
import Entity.Playlist;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;

public class Raccolta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloPlaylist;
	private ControllerPlaylist MycPl;
	private JFrame Precedente;

	/**
	 * Create the frame.
	 */
	public Raccolta(ControllerPlaylist mycPl, JFrame precedente) {
		MycPl=mycPl;
		Precedente=precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titolo = new JLabel("Raccolta:");
		Titolo.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		Titolo.setBounds(143, 55, 621, 36);
		Titolo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(Titolo);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(143, 103, 624, 370);
		contentPane.add(Scorrimento);
		
        PannelloPlaylist = new JPanel();
        Scorrimento.setViewportView(PannelloPlaylist);
        
        JButton Indietro = new JButton("Indietro");
        Indietro.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        Indietro.setBounds(143, 498, 190, 80);
        contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.ToPrecedente(Raccolta.this, Precedente);
			}
		});
        
        JButton CreaPlaylist = new JButton("CreaPlaylist");
        CreaPlaylist.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        CreaPlaylist.setBounds(574, 498, 190, 80);
        contentPane.add(CreaPlaylist);
		CreaPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.RaccoltaToAggiungiPlaylist();
			}
		});
        
	}
	public void MostraPlaylist(List <Playlist> ListaPlaylist) {
	    PannelloPlaylist.removeAll();

	    PannelloPlaylist.setLayout(new BoxLayout(PannelloPlaylist, BoxLayout.Y_AXIS));

	    for (Playlist playlist : ListaPlaylist) {

	        JPanel riga = new JPanel(new BorderLayout());
	        riga.setMaximumSize(new Dimension(PannelloPlaylist.getWidth(), 50));

	        JButton titolo = new JButton(playlist.getTitolo());
	        titolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				MycPl.VisualizzaPlaylist(Raccolta.this,playlist);
				}
			});

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	        JButton Modifica = new JButton("Modifica");
	        Modifica.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

	        JButton Elimina = new JButton("Elimina");
	        Elimina.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
	        
	        pulsanti.add(Modifica);
	        Modifica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Playlist modificata: "+playlist.getTitolo());
					//MycPl.ModificaPlaylist(Raccolta.this, playlist);
				}
			});
	        
	        pulsanti.add(Elimina);
	        Elimina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Playlist eliminata: "+playlist.getTitolo());
					//MycPl.EliminaPlaylist(Raccolta.this, playlist);
				}
			});
	        
	        riga.add(titolo, BorderLayout.CENTER);
	        riga.add(pulsanti, BorderLayout.EAST);

	        PannelloPlaylist.add(riga);
	    }

	    PannelloPlaylist.revalidate();
	    PannelloPlaylist.repaint();
	}
}
