package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
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
		setBounds(100, 100, 707, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(133, 10, 464, 319);
		contentPane.add(Scorrimento);
		
        PannelloPlaylist = new JPanel();
        Scorrimento.setViewportView(PannelloPlaylist);
        
        JButton Indietro = new JButton("Indietro");
        Indietro.setBounds(133, 353, 144, 42);
        contentPane.add(Indietro);
		Indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MycPl.ToPrecedente(Raccolta.this, Precedente);
			}
		});
        
        JButton CreaPlaylist = new JButton("CreaPlaylist");
        CreaPlaylist.setBounds(453, 353, 144, 42);
        contentPane.add(CreaPlaylist);
		CreaPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Playlist creata, non è vero...");
			}
		});
        
	}
	public void MostraElementi(List <Playlist> ListaPlaylist) {
	    PannelloPlaylist.removeAll();

	    PannelloPlaylist.setLayout(new BoxLayout(PannelloPlaylist, BoxLayout.Y_AXIS));

	    for (Playlist playlist : ListaPlaylist) {

	        JPanel riga = new JPanel(new BorderLayout());

	        JButton titolo = new JButton(playlist.getTitolo());
			titolo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//MycPl.VisualizzaPlaylist(Raccolta.this, playlist);
				}
			});

	        JPanel pulsanti = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	        JButton Modifica = new JButton("Modifica");
	        
	        JButton Elimina = new JButton("Elimina");

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
