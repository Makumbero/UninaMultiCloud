package Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Control.*;
import Entity.*;

import javax.swing.JLabel;

public class VisualizzaPlaylist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PannelloBrani;
	private ControllerPlaylist MycPl;
	private ControllerElementi MycEle;
	private JFrame Precedente;
	/**
	 * Create the frame.
	 */
	public VisualizzaPlaylist(Playlist Playlist, List<Brano> Brani, ControllerPlaylist mycPl, ControllerElementi mycEle, JFrame precedente) {
		MycPl=mycPl;
		MycEle=mycEle;
		Precedente=precedente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titolo = new JLabel("Titolo Playlist: "+Playlist.getTitolo());
		Titolo.setBounds(60, 6, 276, 16);
		contentPane.add(Titolo);
		
		JLabel Creatore = new JLabel("Creatore: "+Playlist.getCreatore().getUsername());
		Creatore.setBounds(348, 6, 276, 16);
		contentPane.add(Creatore);
		
		JLabel Data = new JLabel("Data Creazione: "+Playlist.getDataCreazione().toString());
		Data.setBounds(60, 34, 276, 16);
		contentPane.add(Data);
		
		JLabel Tipo = new JLabel();
		if(Playlist instanceof PlaylistPrivata) {
			Tipo.setText("Tipo: Privata");
		}else if(Playlist instanceof PlaylistPubblica) {
			Tipo.setText("Tipo: Pubblica");
		}else {
			Tipo.setText("Tipo: Condivisa");
		}
		Tipo.setBounds(348, 34, 276, 16);
		contentPane.add(Tipo);
		
		JLabel Descrizione = new JLabel("Descrizione: "+Playlist.getDescrizione());
		Descrizione.setBounds(60, 62, 564, 16);
		contentPane.add(Descrizione);
		
		JScrollPane Scorrimento = new JScrollPane();
		Scorrimento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scorrimento.setBounds(70, 92, 467, 271);
		contentPane.add(Scorrimento);
		
        PannelloBrani = new JPanel();
		Scorrimento.setViewportView(PannelloBrani);
		
		   PannelloBrani.removeAll();

		    PannelloBrani.setLayout(new BoxLayout(PannelloBrani, BoxLayout.Y_AXIS));
		    
		    JButton Indietro = new JButton("Indietro");
		    Indietro.setBounds(266, 375, 138, 43);
		    contentPane.add(Indietro);
			Indietro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MycPl.ToPrecedente(VisualizzaPlaylist.this, Precedente);
				}
			});
		    
		    
		    for (Brano brano : Brani) {

		        JPanel riga = new JPanel(new BorderLayout());

		        JButton titolo = new JButton(brano.getTitolo());
				titolo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MycEle.VisualizzaElemento(VisualizzaPlaylist.this, brano);
					}
				});
		        
		        riga.add(titolo, BorderLayout.CENTER);

		        PannelloBrani.add(riga);
		    }

		    PannelloBrani.revalidate();
		    PannelloBrani.repaint();

	}
}
